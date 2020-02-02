package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.SlotModelBean;
import com.ongraph.greatsgames.beans.dto.search.SlotModelSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.SlotModelApi;
import com.ongraph.greatsgames.entities.SlotModel;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.services.ISlotModelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SlotModelApiController extends AbstractController implements SlotModelApi {

    private static final Logger _log = LogManager.getLogger(SlotModelApiController.class);
    @Autowired
    ISlotModelService _slotModelService;

    @Override
    @PreAuthorize("hasAuthority('MODEL_WRITE')")
    public ResponseEntity<AbstractResponseBean> addOrUpdateSlotModel(@RequestBody SlotModelBean body) throws Exception {
        Long slotModelID = null;
        AbstractResponseBean<Long,SlotModelBean> responseBean = new AbstractResponseBean<>();
        boolean isUpdateRequest = false;

        if (null != body.getId())
            isUpdateRequest = true;

        if (!isUpdateRequest) {
                slotModelID = _slotModelService.saveOrUpdateSlotModel(body);
        }
        _log.info("Create new SlotModel");
        responseBean.setObjectId(slotModelID);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('MODEL_READ')")
    public ResponseEntity<AbstractResponseBean> getAllSlotModels(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {
        _log.info("Get all slotModels");
        AbstractResponseBean<Long,SlotModelBean> responseBean = new AbstractResponseBean<>();
        SlotModelSearchCriteria searchCriteria = new SlotModelSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _slotModelService.getAllSlotModels(responseBean,searchCriteria);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AbstractResponseBean> getSlotModelsById(@PathVariable Long id) throws Exception {
        _log.info("Get SlotModel by id");
        AbstractResponseBean<Long,SlotModelBean> responseBean = new AbstractResponseBean<>();
        SlotModelBean slotModelBean = _slotModelService.getSlotModelById(id);
        responseBean.setObject(slotModelBean);
        responseBean.setObjectId(slotModelBean.getId());
        return new ResponseEntity<AbstractResponseBean>(responseBean, HttpStatus.OK);
    }



}

