package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.ContractBean;
import com.ongraph.greatsgames.beans.dto.search.ContractsSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface IContractsService {

    void getallContracts(AbstractResponseBean responseBean, ContractsSearchCriteria searchCriteria) throws Exception;

    ContractBean getContractById(Long id) throws Exception;

    Long saveContract (ContractBean contractsBean) throws Exception;

}
