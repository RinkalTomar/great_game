package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CpuBean;
import com.ongraph.greatsgames.beans.dto.search.ContactSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.CpuSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.CpuConverter;
import com.ongraph.greatsgames.dao.CpuRepository;
import com.ongraph.greatsgames.dao.hibernate.CpuDao;
import com.ongraph.greatsgames.entities.Cpu;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.ICpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CpuServiceImpl extends AbstractService implements ICpuService {

    @Autowired
    CpuRepository _cpuRepository;

    @Autowired
    CpuConverter _cpuConverter;

    @Autowired
    CpuDao _cpuDao;

    @Override
    public Long saveNewCpu(CpuBean body)throws Exception{

        return _cpuRepository.save(_cpuConverter.getEntityFromBean(body)).getId();
    }

    @Override
    public void getAllCpu(AbstractResponseBean responseBean, CpuSearchCriteria searchCriteria)throws Exception{
        int count = _cpuDao.count(searchCriteria);
        List<CpuBean> beans = _cpuConverter.getBeansfromEntities(_cpuDao.search(searchCriteria,false));
        responseBean.setTotalResultsCount(count);
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setObjects(beans);

    }

    @Override
    public CpuBean getCpuById(Long id)throws Exception{
        CpuSearchCriteria searchCriteria = new CpuSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_cpuDao.count(searchCriteria) > 0) {
            return _cpuConverter.getBeanFromEntityWithSlotMachine(_cpuDao.getById(id),Enumeration.ResultType.SELECTION);
        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(ContactSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("CONTACT_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
