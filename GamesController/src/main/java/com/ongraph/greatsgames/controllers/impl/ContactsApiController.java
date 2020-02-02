package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.beans.dto.search.ContactSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.ContactApi;
import com.ongraph.greatsgames.services.IContactService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ContactsApiController extends AbstractController implements ContactApi {

    @Autowired
    IContactService _contactService;


    @Override
    @PreAuthorize("hasAuthority('CONTACT_WRITE')")
    public ResponseEntity<AbstractResponseBean> saveContact(@RequestBody ContactBean body) throws Exception {
        AbstractResponseBean<Long,ContactBean> responseBean = new AbstractResponseBean<>();
        Long contactBeansId = _contactService.saveOrUpdateContact(body);
        responseBean.setObjectId(contactBeansId);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CONTACT_READ')")
    public ResponseEntity<AbstractResponseBean> getAllContacts(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {
        AbstractResponseBean<Long,ContactBean> responseBean = new AbstractResponseBean<>();
        ContactSearchCriteria searchCriteria = new ContactSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _contactService.getAllContacts(responseBean,searchCriteria);
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CONTACT_READ')")
    public ResponseEntity<AbstractResponseBean> getContactDetails(@PathVariable Long id) throws Exception {
        AbstractResponseBean<Long,ContactBean> responseBean = new AbstractResponseBean<>();
        _contactService.getContactById(responseBean,id);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponseBean> deleteCasino(@RequestBody List<Long> id) throws Exception {
        AbstractResponseBean<Long,ContactBean> responseBean = new AbstractResponseBean<>();
        _contactService.deleteCasino(responseBean,id);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponseBean> updateContact(@RequestBody ContactBean body) throws Exception {
        AbstractResponseBean<Long,ContactBean> responseBean = new AbstractResponseBean<>();
        _contactService.update(responseBean,body);

        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponseBean> createCasinoContact(@RequestBody CasinoContactBean bean) throws Exception {
        AbstractResponseBean<Long,CasinoContactBean> responseBean = new AbstractResponseBean<>();
        _contactService.createCasinoContact(responseBean,bean);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }


}
