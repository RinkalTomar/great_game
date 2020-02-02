package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.CasinoBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.dto.search.CasinoSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.CasinoApi;
import com.ongraph.greatsgames.entities.Casino;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.services.ICasinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class CasinoController extends AbstractController implements CasinoApi {

    @Autowired
    ICasinoService _casinoService;

    @Override
    @PreAuthorize("hasAuthority('CASINO_WRITE')")
    public ResponseEntity<AbstractResponseBean> saveOrUpdateCasino(@RequestBody @Valid CasinoBean body) throws Exception {
        AbstractResponseBean responseBean = new AbstractResponseBean();
        Long casinoId = _casinoService.saveOrUpdateCasino(body);
        responseBean.setObjectId(casinoId);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean, HttpStatus.CREATED);

    }

    @Override
    @PreAuthorize("hasAuthority('CASINO_READ')")
    public ResponseEntity<AbstractResponseBean> getAllCasinos(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {
        AbstractResponseBean<Long,Casino> responseBean = new AbstractResponseBean<>();
        CasinoSearchCriteria searchCriteria = new CasinoSearchCriteria();
        parseSearchParams(searchCriteria,searchParams);
         _casinoService.getallCasino(responseBean,searchCriteria);

        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CASINO_READ')")
    public ResponseEntity<AbstractResponseBean> getCasinoDetails(@PathVariable Long id) throws Exception {
        AbstractResponseBean responseBean = new AbstractResponseBean();
        CasinoBean casinoBean = _casinoService.getCasinoById(id);
        responseBean.setObjectId(casinoBean.getId());
        responseBean.setObject(casinoBean);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }


    private void parseSearchParams(CasinoSearchCriteria searchCriteria, Map<String, String> searchParams)
    {
        parseCommonSearchParams(searchCriteria, searchParams);

        if(searchParams.get("status") != null){
            searchCriteria.setStatus(Enumeration.CasinoStatus.valueOf(searchParams.get("status")));
        }
    }

}
