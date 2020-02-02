package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.SlotMachineBean;
import com.ongraph.greatsgames.beans.dto.search.SlotMachineSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface ISlotMachineService {

    Long saveOrUpdateSlotMachine(SlotMachineBean body)throws Exception;

    void getAllSlotMachines(AbstractResponseBean responseBean, SlotMachineSearchCriteria slotMachineSearchCriteria)throws Exception;

    SlotMachineBean getSlotMachinesById(Long id)throws Exception;
}
