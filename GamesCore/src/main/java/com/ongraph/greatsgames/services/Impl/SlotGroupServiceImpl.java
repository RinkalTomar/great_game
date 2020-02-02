package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.SlotGroupBean;
import com.ongraph.greatsgames.beans.dto.search.SlotGroupSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.SlotGroupConverter;
import com.ongraph.greatsgames.dao.SlotGroupRepository;
import com.ongraph.greatsgames.dao.hibernate.SlotGroupDao;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.ISlotGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=false)
public class SlotGroupServiceImpl extends AbstractService implements ISlotGroupService {

    @Autowired
    SlotGroupConverter _slotGroupConverter;

    @Autowired
    SlotGroupRepository _slotGroupRepository;

    @Autowired
    SlotGroupDao _slotGroupDao;

    @Override
   public Long saveSlotGroup(SlotGroupBean body)throws Exception{

    return _slotGroupRepository.save(_slotGroupConverter.getEntityFromBean(body)).getId();
    }

    @Override
    public void getAllSlotGroups(AbstractResponseBean responseBean, SlotGroupSearchCriteria slotGroupSearchCriteria)throws Exception{
        int count = _slotGroupDao.count(slotGroupSearchCriteria);
        List<SlotGroupBean> beans = _slotGroupConverter.getBeansFromEntities(_slotGroupDao.search(slotGroupSearchCriteria,false));
        responseBean.setObjects(beans);
        responseBean.setTotalResultsCount(beans.size());
        responseBean.setTotalResultsCount(count);
        responseBean.setCurrentPageIndex(slotGroupSearchCriteria.getPageNumber());
    }

    @Override
    public SlotGroupBean getSlotGroupsById(Long id)throws Exception{
        SlotGroupSearchCriteria searchCriteria = new SlotGroupSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_slotGroupDao.count(searchCriteria)>0){
            return _slotGroupConverter.getBeanFromEntitywithSlotMachine(_slotGroupDao.getById(id), Enumeration.ResultType.SELECTION);
        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(SlotGroupSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("GROUP_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
