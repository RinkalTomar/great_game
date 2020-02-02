package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.SlotGroupBean;
import com.ongraph.greatsgames.entities.SlotGroup;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlotGroupConverter extends AbstractAuditableConverter {

    @Autowired
    SlotMachineConverter _slotMachineConverter;

    public List<SlotGroup>getEntitiesFromBeans(List<SlotGroupBean> slotGroupBeanList){
        List<SlotGroup> entities= new ArrayList<>();

        for(SlotGroupBean bean:slotGroupBeanList) {
            entities.add(getEntityFromBean(bean));
        }
        return entities;
        }

        public SlotGroup getEntityFromBean(SlotGroupBean bean){
            SlotGroup entity=new SlotGroup();
            populateAuditableEntityFromBean(bean,entity);
            entity.setName(bean.getName());
            entity.setDescription(bean.getDescription());
            entity.setNumberOfMachines(bean.getNumberOfMachines());
            return entity;
        }

    public List<SlotGroupBean> getBeansFromEntities(List<SlotGroup> slotGroupList){
        List<SlotGroupBean> beans =new ArrayList<>();
        for(SlotGroup entity: slotGroupList){
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

    public SlotGroupBean getBeanFromEntity(SlotGroup entity){
        SlotGroupBean bean= new SlotGroupBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        bean.setDescription(entity.getDescription());
        bean.setNumberOfMachines(entity.getNumberOfMachines());
        return bean;
    }
    public SlotGroupBean getBeanFromEntitywithSlotMachine(SlotGroup entity, Enumeration.ResultType type){
        SlotGroupBean bean= new SlotGroupBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        bean.setDescription(entity.getDescription());
        bean.setNumberOfMachines(entity.getNumberOfMachines());
        if(entity.getSlotMachines()!=null) {
            try {
                if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                    bean.setSlotMachines(_slotMachineConverter.getBeansFromEntities(entity.getSlotMachines()));
                }
            }catch (Exception e) {e.printStackTrace();}
        }
        return bean;

    }

    }


