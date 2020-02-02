package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.UsergroupBean;
import com.ongraph.greatsgames.beans.dto.search.UserGroupSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.UserSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.UserGroupApi;
import com.ongraph.greatsgames.services.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserGroupApiController extends AbstractController implements UserGroupApi {

    @Resource
    UserGroupService _usergroupService;


    @Override
    @PreAuthorize("hasAnyAuthority('USER_SEARCH, USERGROUP_SEARCH, USER_CREATE')")
    public ResponseEntity<AbstractResponseBean> getAllUserGroups(Map<String, String> searchParams) throws Exception {
        UserGroupSearchCriteria searchCriteria = new UserGroupSearchCriteria();
        AbstractResponseBean<Long, UsergroupBean> responseBean = new AbstractResponseBean<>();
        responseBean.setSuccess(true);
        responseBean.setAuthenticated(_coreService.isAuthenticated());
        parseSearchParams(searchCriteria, searchParams);

        _usergroupService.getAllUsergroup(responseBean, searchCriteria);
        if(responseBean.getObjects().isEmpty())
            responseBean.setMessage(_message.getMessage("no.data.found"));

        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    private void parseSearchParams(UserGroupSearchCriteria searchCriteria, Map<String, String> searchParams)
    {
        parseCommonSearchParams(searchCriteria, searchParams);
        /*if(searchParams.get("status") != null)
            searchCriteria.setStatus(Enumeration.UserAccountStatus.valueOf(searchParams.get("status")));*/
    }
}
