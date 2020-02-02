package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.SlotModelBean;
import com.ongraph.greatsgames.beans.dto.search.SlotModelSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.SlotModelConverter;
import com.ongraph.greatsgames.dao.SlotModelRepository;
import com.ongraph.greatsgames.dao.hibernate.SlotModelDao;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.ISlotModelService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class SlotModelServiceImpl extends AbstractService implements ISlotModelService {

    @Autowired
    SlotModelConverter _slotMdelConverter;

    @Autowired
    SlotModelRepository _slotModelRepository;

    @Autowired
    SlotModelDao _slotModelDao;

    @Override
    public Long saveOrUpdateSlotModel(SlotModelBean body)throws Exception{

        return _slotModelRepository.save(_slotMdelConverter.getEntityFromBean(body)).getId();
    }

    @Override
    public void getAllSlotModels(AbstractResponseBean responseBean, SlotModelSearchCriteria slotModelSearchCriteria)throws Exception{
        int count = _slotModelDao.count(slotModelSearchCriteria);
        List<SlotModelBean> slotModelBeans = _slotMdelConverter.getBeansFromEntities(_slotModelDao.search(slotModelSearchCriteria,false));
        responseBean.setObjects(slotModelBeans);
        responseBean.setTotalResultsCount(count);
        responseBean.setCurrentPageIndex(slotModelSearchCriteria.getPageNumber());
        responseBean.setResultCountPerPage(slotModelBeans.size());
    }

    @Override
    public SlotModelBean getSlotModelById(Long id)throws Exception{
        SlotModelSearchCriteria searchCriteria = new SlotModelSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_slotModelDao.count(searchCriteria) > 0) {
            return  _slotMdelConverter.getBeanFromEntity(_slotModelDao.getById(id), Enumeration.ResultType.SELECTION);

        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(SlotModelSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("MODEL_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
