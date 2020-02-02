package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.BrandBean;
import com.ongraph.greatsgames.beans.dto.search.BrandSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBrandService {
    @Transactional(propagation = Propagation.REQUIRED)
    Long saveBrand(BrandBean body) throws Exception;

    void getAllBrands(AbstractResponseBean bean, BrandSearchCriteria searchCriteria)throws Exception;

    BrandBean getBrandsById(Long id)throws Exception;


}
