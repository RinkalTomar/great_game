package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.CpuBean;
import com.ongraph.greatsgames.beans.dto.search.CpuSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface ICpuService {

    Long saveNewCpu(CpuBean body)throws Exception;

    void getAllCpu(AbstractResponseBean responseBean, CpuSearchCriteria searchCriteria)throws Exception;

    CpuBean getCpuById(Long id)throws Exception;
}
