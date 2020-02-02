package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.SlotModelBean;
import com.ongraph.greatsgames.entities.SlotModel;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlotModelConverter extends AbstractAuditableConverter {
    @Autowired
    BrandConverter _brandConverter;
    @Autowired
    SlotMachineConverter _slotMachineConverter;

    public List<SlotModel>getEntitiesFromBeans(List<SlotModelBean> slotModelBeanList){
        List<SlotModel> entities=new ArrayList<>();

        for(SlotModelBean bean:slotModelBeanList){
            entities.add(getEntityFromBean(bean));
        }
        return entities;
    }

    public SlotModel getEntityFromBean(SlotModelBean bean){

        SlotModel entity= new SlotModel();
        populateAuditableEntityFromBean(bean,entity);
        entity.setModelName(bean.getModelName());
        entity.setModelNo(bean.getModelNo());
        entity.setBrandId(bean.getBrandId());

        return entity;
    }
    public List<SlotModelBean> getBeansFromEntities(List<SlotModel> slotModelList){
        List<SlotModelBean> beans =new ArrayList<>();
        for(SlotModel entity: slotModelList){
            beans.add(getBeanFromEntityWithoutBrand(entity));
        }
        return beans;
    }

    public SlotModelBean getBeanFromEntityWithoutBrand(SlotModel entity){
        SlotModelBean bean= new SlotModelBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setModelName(entity.getModelName());
        bean.setModelNo(entity.getModelNo());

        bean.setBrandId(entity.getBrandId());
        return bean;
    }

    public SlotModelBean getBeanFromEntity(SlotModel entity, Enumeration.ResultType type){
        SlotModelBean bean= new SlotModelBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setModelName(entity.getModelName());
        bean.setModelNo(entity.getModelNo());

        bean.setBrandId(entity.getBrandId());
        if(entity.getBrand()!=null) {
            try {
                if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                    bean.setBrand(_brandConverter.getBeanFromEntityOnlyForBrand(entity.getBrand()));
                    if (entity.getSlotMachines() != null)
                        bean.setSlotMachines(_slotMachineConverter.getBeansFromEntities(entity.getSlotMachines()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
    public SlotModelBean getBeanFromEntityWithModel(SlotModel entity){
        SlotModelBean bean= new SlotModelBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setModelName(entity.getModelName());
        bean.setModelNo(entity.getModelNo());
        return bean;

    }
}
