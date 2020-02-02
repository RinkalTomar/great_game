package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.AbstractAuditableBean;
import com.ongraph.greatsgames.beans.dto.BrandBean;
import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.beans.dto.search.BrandSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.BrandConverter;
import com.ongraph.greatsgames.dao.BrandRepository;
import com.ongraph.greatsgames.dao.hibernate.BrandDao;
import com.ongraph.greatsgames.entities.Brand;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl extends AbstractService implements IBrandService {

    @Autowired
    BrandConverter _brandConverter;

    @Autowired
    BrandRepository _brandRepository;

    @Autowired
    BrandDao _brandDao;
    @Override
    public Long saveBrand(BrandBean body)throws Exception{

        return _brandRepository.save(_brandConverter.getEntityFromBean(body)).getId();
    }

    @Override
    public void getAllBrands(AbstractResponseBean responseBean, BrandSearchCriteria searchCriteria) throws Exception {
        int count =  _brandDao.count(searchCriteria);

        List<Brand> brands = _brandDao.search(searchCriteria,false);
        List<BrandBean> contactBeans = _brandConverter.getBeansFromEntities(brands);
        responseBean.setTotalResultsCount(count);
        responseBean.setObjects(contactBeans);
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setResultCountPerPage(contactBeans.size());
    }

    @Override
    public BrandBean getBrandsById(Long id)throws Exception{
        BrandSearchCriteria searchCriteria = new BrandSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_brandDao.count(searchCriteria) > 0) {
            return _brandConverter.getBeanFromEntity(_brandDao.getById(id),Enumeration.ResultType.SELECTION);
        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(BrandSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("BRAND_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
