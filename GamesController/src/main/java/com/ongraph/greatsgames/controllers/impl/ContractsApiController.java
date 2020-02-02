package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.ContractBean;
import com.ongraph.greatsgames.beans.dto.search.ContractsSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.ContractsApi;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.services.IContractsService;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.List;
import java.util.Map;

@RestController
public class ContractsApiController extends AbstractController implements ContractsApi {

    private static final Logger _log = LogManager.getLogger(ContractsApiController.class);

    @Autowired
    IContractsService _contractService;

    @Override
    @PreAuthorize("hasAuthority('CONTRACT_READ')")
    public ResponseEntity<AbstractResponseBean> getAllContracts (@RequestParam(required = false) Map<String, String> searchParams)throws Exception {
        AbstractResponseBean<Long,ContractBean> responseBean = new AbstractResponseBean<>();
        ContractsSearchCriteria searchCriteria = new ContractsSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _log.info("Get all contracts ");
        _contractService.getallContracts(responseBean,searchCriteria);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('CONTRACT_WRITE')")
    public ResponseEntity<AbstractResponseBean> saveOrUpdateContract (@RequestBody @Valid ContractBean body) throws Exception{
        _log.info("Create a new Contract");
        AbstractResponseBean<Long,ContractBean> responseBean = new AbstractResponseBean<>();
        Long contractId = _contractService.saveContract(body);
        responseBean.setObjectId(contractId);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('CONTRACT_READ')")
    public ResponseEntity<AbstractResponseBean> getContractDetails (@ApiParam(value = "Unique id of the resource type.", required = true) @PathVariable("id") Long id) throws Exception {
        _log.info("Get contract details by contract id {}", id);
        AbstractResponseBean<Long,ContractBean> responseBean = new AbstractResponseBean<>();
        ContractBean contractsBean = _contractService.getContractById(id);
        responseBean.setObjectId(contractsBean.getId());
        responseBean.setObject(contractsBean);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }

}
