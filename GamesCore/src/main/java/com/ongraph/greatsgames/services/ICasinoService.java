package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.CasinoBean;
import com.ongraph.greatsgames.beans.dto.search.CasinoSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface ICasinoService {
    Long saveOrUpdateCasino(CasinoBean body) throws Exception;

    void getallCasino(AbstractResponseBean responseBean, CasinoSearchCriteria searchCriteria)throws Exception;

    CasinoBean getCasinoById(Long id)throws Exception;
}
