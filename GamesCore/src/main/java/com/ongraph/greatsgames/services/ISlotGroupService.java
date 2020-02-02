package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.SlotGroupBean;
import com.ongraph.greatsgames.beans.dto.search.SlotGroupSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface ISlotGroupService {

    Long saveSlotGroup(SlotGroupBean body)throws Exception;

    void getAllSlotGroups(AbstractResponseBean responseBean, SlotGroupSearchCriteria slotGroupSearchCriteria)throws Exception;

    SlotGroupBean getSlotGroupsById(Long id)throws Exception;
}
