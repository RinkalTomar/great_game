package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.CpuBean;
import com.ongraph.greatsgames.beans.dto.search.CpuSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.CpuApi;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthenticationException;
import com.ongraph.greatsgames.services.ICpuService;
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
public class CpuApiController extends AbstractController implements CpuApi {
    private static final Logger _log=LogManager.getLogger(CpuApiController.class);

    @Autowired
    ICpuService _cpuService;

    @Override
    @PreAuthorize("hasAuthority('CPU_WRITE')")
    public ResponseEntity<AbstractResponseBean>addOrUpdateCpu(@RequestBody @Valid CpuBean body)throws Exception{
        Long cpuId=null;
        AbstractResponseBean<Long,CpuBean> responseBean = new AbstractResponseBean<>();
        boolean isUpdateReuest=false;

        if(null!=body.getId())
            isUpdateReuest=true;

        if(!isUpdateReuest)
                cpuId=_cpuService.saveNewCpu(body);

        _log.info("adding New Cpu");
        responseBean.setObjectId(cpuId);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CPU_READ')")
    public ResponseEntity<AbstractResponseBean>getAllCpu(@RequestParam(required = false) Map<String, String> searchParams)throws Exception{
        _log.info("getting list of cpu");
        AbstractResponseBean<Long,CpuBean> responseBean = new AbstractResponseBean<>();
        CpuSearchCriteria searchCriteria = new CpuSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _cpuService.getAllCpu(responseBean,searchCriteria);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CPU_READ')")
    public ResponseEntity<AbstractResponseBean>getCpuById(@PathVariable Long id)throws Exception{
        _log.info("getting cpu of given id");
        AbstractResponseBean<Long,CpuBean> responseBean = new AbstractResponseBean();
        CpuBean cpuBean = _cpuService.getCpuById(id);
        responseBean.setObjectId(cpuBean.getId());
        responseBean.setObject(cpuBean);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }
}
