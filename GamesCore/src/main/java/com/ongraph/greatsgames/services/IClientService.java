package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.dto.search.ClientSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClientService {


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Long saveOrUpdateClient(ClientBean body) throws Exception;

    void getAllClient(AbstractResponseBean response, ClientSearchCriteria searchCriteria)throws Exception;

    ClientBean getClientById(Long id)throws Exception;

}
