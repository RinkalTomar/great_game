package com.ongraph.greatsgames.services.Impl;


import com.ongraph.greatsgames.beans.dto.UserBean;
import com.ongraph.greatsgames.beans.dto.UserTokenBean;
import com.ongraph.greatsgames.constants.Constants;
import com.ongraph.greatsgames.converters.UserTokenConverter;
import com.ongraph.greatsgames.dao.UserTokenRepository;
import com.ongraph.greatsgames.entities.UserToken;
import com.ongraph.greatsgames.entities.Users;
import com.ongraph.greatsgames.enums.Enumeration.*;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.IEmailEventService;
import com.ongraph.greatsgames.services.IUserTokenService;
import com.ongraph.greatsgames.utils.AppUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Transactional(readOnly = true)
@Service
public class UserTokenServiceImpl extends AbstractService implements IUserTokenService{

    @Autowired
    UserTokenRepository _userTokenRepository;

    @Autowired
    UserTokenConverter _userTokenConverter;

    @Autowired
    UserServiceImpl _userService;

    @Autowired
    IEmailEventService _emailEventService;

    private static final Logger _log = LogManager.getLogger(UserTokenServiceImpl.class);


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long createEmailVerificationTokenRequest(Long user_id) throws Exception{

        String token = AppUtility.generateUUID();
        UserTokenBean tokenBean = new UserTokenBean(token, LocalDateTime.now().plusMonths(1L), UserTokenType.VERIFICATION, user_id);

       UserToken userToken = _userTokenConverter.getEntityFromBean(tokenBean);
       _userTokenRepository.save(userToken);

        _log.info("Email Verification Request Created with id : {}" , userToken.getId());

        _log.info("Injecting Verification Email in Mailing Queue");
        Long eventId = _emailEventService.queueAccountVerificationEmail(
                userToken.getUser().getEmail(), token);

        _log.info("Email Queued For Processing, email event Id : {}", eventId);

        return userToken.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Long createPasswordResetRequest(Long user_id, String token) throws Exception{

        UserTokenBean tokenBean = new UserTokenBean(token, LocalDateTime.now().plusMonths(24L), UserTokenType.PASSWORD_RESET,
                user_id);


        return _userTokenRepository.save(_userTokenConverter.getEntityFromBean(tokenBean)).getId();
    }
    @Override
    public UserTokenBean getUserTokenByToken(String token) throws Exception {


        UserToken userToken = _userTokenRepository.getUserTokenByToken(token);
        return userToken!=null?_userTokenConverter.getBeanFromEntity(userToken):null;
    }

    @Override
    public void verifyRequest(String token) throws Exception
    {
        UserTokenBean userToken = getUserTokenByToken(token);
        if (null == userToken) {
            throw new BadRequestException(_message.getMessage("invalid.token"));
        }
        if (userToken.getToken_expiry().isBefore(LocalDateTime.now()) || userToken.getIsExpired())
            throw new BadRequestException(_message.getMessage("expired.token"));
    }

}
