package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.CpuBean;
import com.ongraph.greatsgames.entities.Cpu;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CpuConverter extends AbstractAuditableConverter {

    @Autowired
    SlotMachineConverter _slotMachineConverter;

    public List<CpuBean>getBeansfromEntities(List<Cpu> entityList ){
        List<CpuBean>beans= new ArrayList<>();
        for(Cpu entity:entityList ){
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

   public CpuBean getBeanFromEntity(Cpu entity){
        CpuBean bean= new CpuBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        bean.setVersion(entity.getVersion());
        return bean;
    }

    public CpuBean getBeanFromEntityWithSlotMachine(Cpu entity,Enumeration.ResultType type){
        CpuBean bean= new CpuBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        bean.setVersion(entity.getVersion());
        if(entity.getSlotMachine()!=null) {
            try {
                bean = getBeanFromEntity(entity);
                if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                    bean.setSlotMachine(_slotMachineConverter.getBeansFromEntities(entity.getSlotMachine()));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

  public Cpu getEntityFromBean(CpuBean bean){
        Cpu entity= new Cpu();
        populateAuditableEntityFromBean(bean,entity);
        entity.setName(bean.getName());
        entity.setVersion(bean.getVersion());
        return entity;
    }
}
