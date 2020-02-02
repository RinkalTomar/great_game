package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.SlotGroupBean;
import com.ongraph.greatsgames.beans.dto.search.SlotGroupSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.SlotGroupApi;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.ISlotGroupService;
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
import org.thymeleaf.standard.expression.GreaterLesserExpression;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class SlotGroupApiController extends AbstractController implements SlotGroupApi {
    private static final Logger _log = LogManager.getLogger(SlotGroupApiController.class);

    @Autowired
    ISlotGroupService _slotGroupService;
    @Autowired
    MessageProperties _message;

    @Override
    @PreAuthorize("hasAuthority('GROUP_WRITE')")
    public ResponseEntity<AbstractResponseBean> addNewSlotGroup(@Valid @RequestBody SlotGroupBean body) throws Exception {
        Long slotGtoupID = null;
        AbstractResponseBean<Long,SlotGroupBean> responseBean = new AbstractResponseBean<>();
        Boolean isUpdateRequest = false;

        if (null != body.getId())
            isUpdateRequest = true;

        if (!isUpdateRequest) {
                slotGtoupID = _slotGroupService.saveSlotGroup(body);
        }
        _log.info("Add new SlotGroup");
        responseBean.setObjectId(slotGtoupID);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('GROUP_READ')")
    public ResponseEntity<AbstractResponseBean> getAllSlotGroups(@RequestParam(required = false) Map<String, String> searchParams) throws Exception {
        _log.info("Get all slotGroups");
        AbstractResponseBean<Long,SlotGroupBean> responseBean = new AbstractResponseBean<>();
        SlotGroupSearchCriteria searchCriteria = new SlotGroupSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _slotGroupService.getAllSlotGroups(responseBean,searchCriteria);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('GROUP_READ')")
    public ResponseEntity<AbstractResponseBean> getslotGroupsById(@PathVariable Long id) throws Exception {
        _log.info("get slotGroup by id");
        AbstractResponseBean<Long,SlotGroupBean> responseBean = new AbstractResponseBean<>();
        SlotGroupBean slotGroupBean = _slotGroupService.getSlotGroupsById(id);
        responseBean.setObject(slotGroupBean);
        responseBean.setObjectId(slotGroupBean.getId());
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }


}
