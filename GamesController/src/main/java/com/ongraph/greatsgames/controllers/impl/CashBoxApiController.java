package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.AbstractAuditableBean;
import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.CashBoxApi;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.ICashBoxService;
import com.ongraph.greatsgames.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class CashBoxApiController extends AbstractController implements CashBoxApi {

    @Resource
    ICashBoxService _cashboxService;

    @Autowired
    IUserService _userService;

    @Override
    @PreAuthorize("hasAuthority('CASHBOX_WRITE')")
    public ResponseEntity<AbstractResponse> addCashBox(@Valid @RequestBody CashBoxBean body) throws Exception {

        if(!_coreService.hasPermission("CASHBOX_SEARCH"))
            body.setUserId(_coreService.getCurrentUser().getId());
        else{
                if(_userService.getUserById(body.getUserId()) == null)
                    throw new BadRequestException(_message.getMessage("no.valid.user"));
        }
        return buildResponse(_cashboxService.createCashBox(body),HttpStatus.CREATED);

    }

    @Override
    @PreAuthorize("hasAuthority('CASHBOX_READ')")
    public ResponseEntity<AbstractResponseBean> getAllCashBoxes(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {

        AbstractResponseBean<Long, CashBoxBean> responseBean = new AbstractResponseBean<>();
        CashBoxSearchCriteria searchCriteria = new CashBoxSearchCriteria();
        parseSearchParams(searchCriteria, searchParams);
        _cashboxService.getAllCasBoxes(responseBean, searchCriteria);
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CASHBOX_READ')")
    public ResponseEntity<AbstractResponse> getCashBox(@PathVariable  Long id) throws Exception {

        AbstractResponseBean<Long, CashBoxBean> responseBean = new AbstractResponseBean<Long, CashBoxBean>();
        CashBoxBean bean = _cashboxService.getCashboxById(id);
        responseBean.setData(bean);
        responseBean.setObjectId(bean.getId());
        return buildResponse(bean, HttpStatus.OK);

    }

    @Override
    @PreAuthorize("hasAuthority('CASHBOX_DELETE')")
    public ResponseEntity<AbstractResponseBean<Long, CashBoxBean>> deleteCashbox(Long id) throws Exception {
        AbstractResponseBean<Long, CashBoxBean> responseBean = new AbstractResponseBean<Long, CashBoxBean>();
        _cashboxService.remove(id);
        responseBean.setObjectId(id);
        return new ResponseEntity(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CASHBOX_WRITE')")
    public ResponseEntity<AbstractResponseBean<Long, CashBoxBean>> Update(@Valid @RequestBody CashBoxBean body) throws Exception {
                AbstractResponseBean<Long, CashBoxBean> responseBean = new AbstractResponseBean<>();

                _cashboxService.update(body,responseBean);
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }



    private void parseSearchParams(CashBoxSearchCriteria searchCriteria, Map<String, String> searchParams) {

        parseCommonSearchParams(searchCriteria, searchParams);

        if(searchParams.get("user") != null)
            searchCriteria.getSelectedUserId().add(Long.parseLong(searchParams.get("user")));
    }



}
