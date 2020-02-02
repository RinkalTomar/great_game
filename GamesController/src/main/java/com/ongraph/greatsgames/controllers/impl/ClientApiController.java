package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.dto.search.ClientSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.ClientApi;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class ClientApiController extends AbstractController implements ClientApi {

    @Autowired
    IClientService _clientService;


    @Override
    public ResponseEntity<AbstractResponse> saveOrUpdateClient(@RequestBody @Valid ClientBean body) throws Exception {

        Long id;

        if(_coreService.hasPermission("CLIENT_WRITE") || _coreService.isSuperAdmin() )
        {
            id =  _clientService.saveOrUpdateClient(body);
        }
        else
            throw new AuthorizationServiceException(_message.getMessage("authorization.failed.message"));

        return buildResponse(id, HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasAuthority('CLIENT_READ')")
    public ResponseEntity<AbstractResponseBean> getAllClients(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {

        AbstractResponseBean<Long, ClientBean> response = new AbstractResponseBean<>();
        ClientSearchCriteria searchCriteria = new ClientSearchCriteria();
        parseSearchParams(searchCriteria, searchParams);
        _clientService.getAllClient(response, searchCriteria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CLIENT_READ')")
    public ResponseEntity<AbstractResponseBean> getClientDetails(@PathVariable Long id) throws Exception {

        AbstractResponseBean<Long, ClientBean> responseBean = new AbstractResponseBean<Long, ClientBean>();
        ClientBean bean = _clientService.getClientById(id);
        responseBean.setObject(bean);
        responseBean.setSuccess(true);
        responseBean.setAuthenticated(_coreService.isAuthenticated());
        responseBean.setObjectId(bean.getId());
        return new ResponseEntity(responseBean, HttpStatus.OK);

    }

    private void parseSearchParams(ClientSearchCriteria searchCriteria, Map<String, String> searchParams) {

        parseCommonSearchParams(searchCriteria, searchParams);

        if(searchParams.get("user") != null)
            searchCriteria.getSelectedUserId().add(Long.parseLong(searchParams.get("user")));
    }


}
