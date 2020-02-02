package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.AbstractAuditableBean;
import com.ongraph.greatsgames.beans.dto.SlotModelBean;
import com.ongraph.greatsgames.beans.dto.search.SlotModelSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface ISlotModelService {

    Long saveOrUpdateSlotModel(SlotModelBean body)throws Exception;

    void getAllSlotModels(AbstractResponseBean responseBean, SlotModelSearchCriteria slotModelSearchCriteria)throws Exception;

    SlotModelBean getSlotModelById(Long id)throws Exception;
}
