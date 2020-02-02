package com.ongraph.greatsgames.converters;


import com.ongraph.greatsgames.beans.dto.BrandBean;
import com.ongraph.greatsgames.entities.Brand;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BrandConverter extends AbstractAuditableConverter {
    @Autowired
    SlotModelConverter _slotModelConverter;


    public Brand getEntityFromBean(BrandBean bean){

        Brand entity= new Brand();
        populateAuditableEntityFromBean(bean,entity);

        entity.setName(bean.getName());
        return entity;
    }

    public List<BrandBean> getBeansFromEntities(List<Brand> brandList){
        List<BrandBean> beans =new ArrayList<>();
        for(Brand entity:brandList){
            beans.add(getBeanFromEntityOnlyForBrand(entity));
        }
        return beans;
    }


    public BrandBean getBeanFromEntity(Brand entity,Enumeration.ResultType type){
        BrandBean bean= new BrandBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        try {
            if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                bean.setSlotModelBean(_slotModelConverter.getBeansFromEntities(entity.getSlotModel()));

            }
        }catch (Exception e) {e.printStackTrace();}
        return bean;
    }
    public BrandBean getBeanFromEntityOnlyForBrand(Brand entity){
        BrandBean bean= new BrandBean();
        populateAuditableBeanFromEntity(bean,entity);
        bean.setName(entity.getName());
        bean.setNumberOfModel(entity.getSlotModel().size());
        return bean;
    }
}
