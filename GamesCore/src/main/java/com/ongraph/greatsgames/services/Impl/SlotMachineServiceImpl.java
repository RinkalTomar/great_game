package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.SlotMachineBean;
import com.ongraph.greatsgames.beans.dto.search.SlotMachineSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.SlotMachineConverter;
import com.ongraph.greatsgames.dao.SlotMachineRepository;
import com.ongraph.greatsgames.dao.hibernate.SlotMachineDao;
import com.ongraph.greatsgames.entities.SlotMachine;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthenticationException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.ISlotMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class SlotMachineServiceImpl extends AbstractService implements ISlotMachineService {

    @Autowired
    SlotMachineConverter _slotMachineConverter;

    @Autowired
    SlotMachineRepository _slotMachineRepository;

    @Autowired
    SlotMachineDao _slotMachineDao;

    @Override
    public Long saveOrUpdateSlotMachine(SlotMachineBean body)throws Exception{

    return _slotMachineRepository.save(_slotMachineConverter.getEntityFromBean(body)).getId();
    }

    @Override
    public void getAllSlotMachines(AbstractResponseBean responseBean, SlotMachineSearchCriteria slotMachineSearchCriteria)throws Exception{
        int count = _slotMachineDao.count(slotMachineSearchCriteria);
        List<SlotMachineBean> beans = _slotMachineConverter.getBeansFromEntities(_slotMachineDao.search(slotMachineSearchCriteria,false));
        responseBean.setTotalResultsCount(count);
        responseBean.setObjects(beans);
        responseBean.setCurrentPageIndex(slotMachineSearchCriteria.getPageNumber());
        responseBean.setResultCountPerPage(beans.size());
    }


    @Override
    public SlotMachineBean getSlotMachinesById(Long id)throws Exception{
        SlotMachineSearchCriteria searchCriteria = new SlotMachineSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_slotMachineDao.count(searchCriteria)>0){
            return _slotMachineConverter.getBeanFromEntityWithAllData(_slotMachineDao.getById(id), Enumeration.ResultType.SELECTION);
        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(SlotMachineSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("MACHINE_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
