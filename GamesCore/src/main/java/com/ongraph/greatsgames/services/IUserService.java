package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.PasswordResetBean;
import com.ongraph.greatsgames.beans.dto.UserBean;
import com.ongraph.greatsgames.beans.dto.http.request.UserAcountResetOrActivateRequest;
import com.ongraph.greatsgames.beans.dto.search.UserSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {

    Long saveOrUpdate(UserBean body) throws Exception;

    // Long getPasswordResetToken(String email) throws Exception;
    void doPasswordReset(PasswordResetBean body)throws Exception;

    void verifyEmail(String token) throws Exception;

    void getallUsers(AbstractResponseBean<Long, UserBean> responseBean, UserSearchCriteria searchCriteria)throws Exception;

    UserBean getUserById (Long id) throws Exception;

    //Long sendResetPasswordLink(@Valid String email) throws Exception;


    AbstractResponseBean<String,UserBean> initiatingPasswordResetForMultiUsers(List<UserAcountResetOrActivateRequest> userAcountResetOrActivateRequests);
    boolean initiatingPasswordReset(UserAcountResetOrActivateRequest initiatePasswordResetRequest)
            throws Exception;


    void deactivateUser (List<Long> id) throws Exception;

    void suspendUser (List<Long> id) throws Exception;

    void deleteUserById(Long id)throws Exception;
}
