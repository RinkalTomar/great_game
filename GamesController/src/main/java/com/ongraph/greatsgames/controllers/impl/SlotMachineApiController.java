package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.SlotMachineBean;
import com.ongraph.greatsgames.beans.dto.search.SlotMachineSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.SlotMachineApi;
import com.ongraph.greatsgames.entities.SlotMachine;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthenticationException;
import com.ongraph.greatsgames.services.ISlotMachineService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class SlotMachineApiController extends AbstractController implements SlotMachineApi {
    private static final Logger _log=LogManager.getLogger(SlotMachineApiController.class);

    @Autowired
    ISlotMachineService _slotMachineService;

    @Override
    @PreAuthorize("hasAuthority('MACHINE_WRITE')")
    public ResponseEntity<AbstractResponseBean> addNewSlotMachine(@RequestBody @Valid SlotMachineBean body)throws Exception {
        _log.info("save new SlotMachine");
        AbstractResponseBean<Long,SlotMachineBean> responseBean = new AbstractResponseBean<>();
        Long slotMachineId = _slotMachineService.saveOrUpdateSlotMachine(body);
        responseBean.setObjectId(slotMachineId);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('MACHINE_READ')")
    public ResponseEntity<AbstractResponseBean>getAllSlotMachines(@RequestParam(required = false) Map<String, String> searchParams )throws Exception{
        _log.info("Get List Of all SlotMachines");
        AbstractResponseBean<Long,SlotMachineBean> responseBean = new AbstractResponseBean<>();
        SlotMachineSearchCriteria searchCriteria = new SlotMachineSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _slotMachineService.getAllSlotMachines(responseBean,searchCriteria);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('MACHINE_READ')")
    public ResponseEntity<AbstractResponseBean>getslotMachinesById(@PathVariable Long id)throws Exception {
        _log.info("get slotMachine By Id");
        AbstractResponseBean<Long,SlotMachineBean> responseBean = new AbstractResponseBean<>();
        SlotMachineBean slotMachineBean = _slotMachineService.getSlotMachinesById(id);
        responseBean.setObjectId(slotMachineBean.getId());
        responseBean.setObject(slotMachineBean);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }
}
