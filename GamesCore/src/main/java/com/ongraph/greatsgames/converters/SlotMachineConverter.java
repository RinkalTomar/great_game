package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.SlotMachineBean;
import com.ongraph.greatsgames.entities.SlotMachine;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlotMachineConverter extends AbstractAuditableConverter {
    @Autowired
    SlotModelConverter _slotModelConverter;
    @Autowired
    SlotGroupConverter _slotGroupConverter;
    @Autowired
    CasinoConverter _casinoConverter;
    @Autowired
    CpuConverter _cpuConverter;

   public  SlotMachine getEntityFromBean(SlotMachineBean bean){
           SlotMachine entity = new SlotMachine();

        populateAuditableEntityFromBean(bean,entity);
        entity.setButtonPanelId(bean.getButtonPanelId());
        entity.setCabinetColour(bean.getCabinetColour());
        entity.setCasinoId(bean.getCasinoId());
        entity.setConfigurationId(bean.getConfigurationId());
        entity.setCpuId(bean.getCpuId());
        entity.setInstalledGame(bean.getInstalledGame());
        entity.setInvoiceId(bean.getInvoiceId());
        entity.setManufacturingDate(bean.getManufacturingDate());
        entity.setModelId(bean.getModelId());
        entity.setNomId(bean.getNomId());
        entity.setNotes(bean.getNotes());
        entity.setPhoto(bean.getPhoto());
        entity.setSerialNo(bean.getSerialNo());
        entity.setSlotGroupId(bean.getSlotGroupId());
        entity.setSlotInfo(bean.getSlotInfo());
        entity.setWarehouseId(bean.getWarehouseId());
        entity.setTopper(bean.getTopper());
        entity.setWarehouseCountId(bean.getWarehouseCountId());

        return entity;
    }

    public List<SlotMachineBean>getBeansFromEntities(List<SlotMachine> entityList){
       List<SlotMachineBean>beans=new ArrayList<>();
       for(SlotMachine entity:entityList){
           beans.add(getBeanFromEntity(entity));
       }
       return beans;
    }
    public SlotMachineBean getBeanFromEntity(SlotMachine entity){
        SlotMachineBean bean=new SlotMachineBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setButtonPanelId(entity.getButtonPanelId());
        bean.setCabinetColour(entity.getCabinetColour());
        bean.setCasinoId(entity.getCasinoId());
        bean.setConfigurationId(entity.getConfigurationId());
        bean.setCpuId(entity.getCpuId());
        bean.setInstalledGame(entity.getInstalledGame());
        bean.setInvoiceId(entity.getInvoiceId());
        bean.setManufacturingDate(entity.getManufacturingDate());
        bean.setModelId(entity.getModelId());
        bean.setNomId(entity.getNomId());
        bean.setNotes(entity.getNotes());
        bean.setPhoto(entity.getPhoto());
        bean.setSerialNo(entity.getSerialNo());
        bean.setSlotGroupId(entity.getSlotGroupId());
        bean.setSlotInfo(entity.getSlotInfo());
        bean.setWarehouseId(entity.getWarehouseId());
        bean.setTopper(entity.getTopper());
        bean.setWarehouseCountId(entity.getWarehouseCountId());
        return bean;
    }

    public SlotMachineBean getBeanFromEntityWithAllData(SlotMachine entity,Enumeration.ResultType type){
        SlotMachineBean bean=new SlotMachineBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setButtonPanelId(entity.getButtonPanelId());
        bean.setCabinetColour(entity.getCabinetColour());
        bean.setCasinoId(entity.getCasinoId());
        bean.setConfigurationId(entity.getConfigurationId());
        bean.setCpuId(entity.getCpuId());
        bean.setInstalledGame(entity.getInstalledGame());
        bean.setInvoiceId(entity.getInvoiceId());
        bean.setManufacturingDate(entity.getManufacturingDate());
        bean.setModelId(entity.getModelId());
        bean.setNomId(entity.getNomId());
        bean.setNotes(entity.getNotes());
        bean.setPhoto(entity.getPhoto());
        bean.setSerialNo(entity.getSerialNo());
        bean.setSlotGroupId(entity.getSlotGroupId());
        bean.setSlotInfo(entity.getSlotInfo());
        bean.setWarehouseId(entity.getWarehouseId());
        bean.setTopper(entity.getTopper());
        bean.setWarehouseCountId(entity.getWarehouseCountId());

        try {
            bean = getBeanFromEntity(entity);
            if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                if(entity.getSlotModel()!=null)
                    bean.setSlotModel(_slotModelConverter.getBeanFromEntityWithModel(entity.getSlotModel()));
                if(entity.getSlotGroup()!=null)
                    bean.setSlotGroup(_slotGroupConverter.getBeanFromEntity(entity.getSlotGroup()));
                if(entity.getCasino()!=null)
                    bean.setCasino(_casinoConverter.getBeanFromEntityForSlotMachine(entity.getCasino()));
                if(entity.getCpu()!=null)
                    bean.setCpu(_cpuConverter.getBeanFromEntity(entity.getCpu()));
            }
        }catch (Exception e) {e.printStackTrace();}


        return bean;
    }
}
