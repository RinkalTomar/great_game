package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.PasswordResetBean;
import com.ongraph.greatsgames.beans.dto.UserBean;
import com.ongraph.greatsgames.beans.dto.UserTokenBean;
import com.ongraph.greatsgames.beans.dto.http.request.UserAcountResetOrActivateRequest;
import com.ongraph.greatsgames.beans.dto.search.UserSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.constants.Constants;
import com.ongraph.greatsgames.converters.UserConverter;
import com.ongraph.greatsgames.dao.hibernate.UserDao;
import com.ongraph.greatsgames.entities.Users;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.IEmailEventService;
import com.ongraph.greatsgames.services.IUserService;
import com.ongraph.greatsgames.services.IUserTokenService;
import com.ongraph.greatsgames.utils.AppUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ongraph.greatsgames.enums.Enumeration.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends AbstractService implements IUserService{

    private static final Logger _log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao _userRepository;

    @Autowired
    UserConverter _userConverter;

    @Autowired
    IUserTokenService _userTokenService;

    @Autowired
    IEmailEventService _emailEventService;

    @Autowired
    PasswordEncoder userPasswordEncoder;

    @Override
    @Transactional
    public Long saveOrUpdate(UserBean body) throws Exception {

        if (_userRepository.existsByUsername(body.getUsername()))
            throw new BadRequestException(_message.getMessage("username.exist"));
        else if (_userRepository.existsByEmail(body.getEmail()))
            throw new BadRequestException(_message.getMessage("email.exist"));

        Users newUser = _userConverter.getEntityfromBean(body);
        newUser.setPassword(_configService.getValue(ConfigKeys.TEMP_PASSWORD));
        newUser.setStatus(UserAccountStatus.VERIFICATION_PENDING);
        newUser.setEmailVerified(Boolean.FALSE);

        newUser.setEmailCommunicationToken(AppUtility.generateUUID());
        newUser.setTokenExpiryDate(LocalDateTime.now().plusDays(Constants.INVITE_ACTIVATION_TOKEN_EXPIRES_ON_DAYS));

        _userRepository.save(newUser);

        Long userId =newUser.getId();

        /*_log.info("User Saved with User Id {} \n Generation Email Verificaiton Token", userId);
        Long tokenId = _userTokenService.createEmailVerificationTokenRequest(userId);*/

        _log.info("Injecting Verification Email in Mailing Queue");
        Long eventId = _emailEventService.queueAccountVerificationEmail(
                newUser.getEmail(), newUser.getEmailCommunicationToken());

        _log.info("Email Queued For Processing, email event Id : {}", eventId);

        return userId;
    }

    @Override
    @Transactional(readOnly = false)
    public void doPasswordReset(PasswordResetBean passwordResetBean) throws Exception{

        UserTokenBean userTokenBean= _userTokenService.getUserTokenByToken(passwordResetBean.getToken());
        if(null==userTokenBean)
            throw new BadRequestException(_message.getMessage("invalid.token"));

        if(userTokenBean.getToken_expiry().isBefore(LocalDateTime.now()))
            throw new BadRequestException(_message.getMessage("expired.token"));

        Users requestedUser = _userRepository.getById(userTokenBean.getUserId());
        requestedUser.setPassword(userPasswordEncoder.encode(passwordResetBean.getPassword()));

        _userRepository.save(requestedUser);

    }

    @Override
    @Transactional
    public void verifyEmail(String token)throws Exception{
        UserTokenBean userTokenBean=_userTokenService.getUserTokenByToken(token);
        if(null==userTokenBean)
            throw new BadRequestException(_message.getMessage("invalid.token"));

        Users requestedUser = _userRepository.getById(userTokenBean.getUserId());
        if(requestedUser!=null){
            if(requestedUser.getEmailVerified()==Boolean.TRUE)
                throw new BadRequestException(_message.getMessage("email.already.verified"));
            else
                requestedUser.setEmailVerified(Boolean.TRUE);
            if(requestedUser.getStatus()==UserAccountStatus.ACTIVE)
                throw new BadRequestException(_message.getMessage("already.activated"));
            else
                requestedUser.setStatus(UserAccountStatus.ACTIVE);

            _userRepository.save(requestedUser);
        }
    }

    @Override
    public void getallUsers(AbstractResponseBean<Long, UserBean> responseBean, UserSearchCriteria searchCriteria) throws Exception {

        int count = _userRepository.count(searchCriteria);
        List<UserBean> beans = _userConverter.getBeansFromEntities(
                _userRepository.search(searchCriteria, false));

        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(beans);
    }

    @Override
    public UserBean getUserById(Long id) throws Exception {
        UserBean bean =  _userConverter.getBeanFromEntity(_userRepository.getById(id));
        if(bean == null){
            throw new BadRequestException(_message.getMessage("invalid.identifier"));
        }
        return bean;
    }

    @Override
    public AbstractResponseBean<String, UserBean> initiatingPasswordResetForMultiUsers(List<UserAcountResetOrActivateRequest> userAcountResetOrActivateRequests) {

        AbstractResponseBean<String,UserBean> response = new AbstractResponseBean<String,UserBean>();
        Map<String, String> errorMap = new HashMap<>();
        int successCounter = 0;
        for (UserAcountResetOrActivateRequest request : userAcountResetOrActivateRequests) {
            try {
                if (initiatingPasswordReset(request))
                    successCounter += 1;
            } catch (Exception e)
            {
                errorMap.put(request.getEmail(), e.getLocalizedMessage());
                _log.error(e);
            }
        }
        if (successCounter == userAcountResetOrActivateRequests.size())
            response.setSuccess(true);
        response.setError(errorMap);
        return response;
    }


    @Override
    @Transactional(readOnly = false)
    public boolean initiatingPasswordReset(UserAcountResetOrActivateRequest initiatePasswordResetRequest)
            throws Exception {

        if (!_userRepository.existsByEmail(initiatePasswordResetRequest.getEmail())) {
            //Return true to not show on UI that email exists or not
            return true;
        }

        Users user = _userRepository.getUsersByEmail(initiatePasswordResetRequest.getEmail());
        user.setEmailCommunicationToken(AppUtility.generateUUID());

        _userRepository.update(user);

        _log.info("Injecting Verification Email in Mailing Queue");
        Long eventId = _emailEventService.queueAccountVerificationEmail(
                user.getEmail(), user.getEmailCommunicationToken());

        _log.info("Email Queued For Processing, email event Id : {}", eventId);

        return true;

    }


    private void validateSearchCriteria(UserSearchCriteria searchCriteria) throws Exception
    {
        Long logg_in_user = _coreService.getCurrentUser().getId();
        if(!_coreService.hasPermission("USER_SEARCH"))
        {
                if(!searchCriteria.getSelectedIds().contains(logg_in_user))
                    throw new GreatGamesAuthorizationException();
                else{
                    //Remove additional Ids which are not accessible to the current user.
                    searchCriteria.getSelectedIds().removeIf(i -> i != logg_in_user);
                }

                if(searchCriteria.getSelectedIds().isEmpty())
                    throw new GreatGamesAuthorizationException();
        }
        else{

            //TODO: EXCLUDE SUPERADMIN USER.
        }

    }

    @Override
    public void deactivateUser (List<Long> id) throws Exception {
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        searchCriteria.getSelectedIds().addAll(id);
        List<Users> userss = _userRepository.search(searchCriteria,false);
        userss.forEach(p -> p.setStatus(UserAccountStatus.INACTIVE));
        _userRepository.update(userss);
    }

    @Override
    public void suspendUser(List<Long> id) throws Exception {
        Long logg_in_user = _coreService.getCurrentUser().getId();
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        searchCriteria.getSelectedIds().addAll(id);
        searchCriteria.getSelectedIds().removeIf(i -> i == logg_in_user);
        List<Users> userss = _userRepository.search(searchCriteria,false);
        userss.forEach(p -> {p.setIsDeleted(true); p.setStatus(UserAccountStatus.INACTIVE);});
        _userRepository.update(userss);
    }

    @Override
    public void deleteUserById(Long id) throws Exception {
        Users userss = _userRepository.getById(id);
        userss.setIsDeleted(true);
        userss.setStatus(UserAccountStatus.INACTIVE);
        _userRepository.update(userss);
    }


}
