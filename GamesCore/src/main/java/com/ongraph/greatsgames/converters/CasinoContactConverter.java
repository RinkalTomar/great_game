package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.entities.CasinoContacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CasinoContactConverter extends BaseConverter{

    @Autowired
    CasinoConverter _casinoConverter;

    @Autowired
    ContactConverter _contactConverter;

    public CasinoContacts getEntityFromBean(CasinoContactBean bean)
    {
        CasinoContacts entity = new CasinoContacts();

        populateBaseEntityFromBean(bean, entity);

        entity.setType(bean.getType());
        entity.setCasinoId(bean.getCasinoId());
        entity.setContactId(bean.getContactId());

        try{entity.setContact(_contactConverter.getEntityFromBean(bean.getContact()));}
        catch (Exception e){}

        try{entity.setCasino(_casinoConverter.getEntityFromBean(bean.getCasino()));}
        catch (Exception e){}

        return entity;
    }

    public List<CasinoContacts> getEntitiesFromBeans(List<CasinoContactBean> beans)
    {
        List<CasinoContacts> entites = new ArrayList<CasinoContacts>();

        for(CasinoContactBean bean : beans)
            entites.add(getEntityFromBean(bean));

        return entites;
    }

    public List<CasinoContactBean> getBeansFromEntitiesForCasino (List<CasinoContacts> entities) {

        List<CasinoContactBean> beans = new ArrayList<>();

        for (CasinoContacts entity : entities) {
            beans.add(getBeanFromEntityForCasino(entity));
        }
        return beans;
    }
    public CasinoContactBean getBeanFromEntityForCasino (CasinoContacts entity) {

        if (null != entity) {
            CasinoContactBean bean = new CasinoContactBean();

            populateBaseBeanFromEntity(bean, entity);
            bean.setContactId(entity.getContactId());
            bean.setType(entity.getType());

            try{bean.setCasino(_casinoConverter.getBeanFromEntityForCasinoContact(entity.getCasino()));}
            catch (Exception e){}
            return bean;
        } else
            return null;
    }
    public List<CasinoContactBean> getBeansFromEntitiesForContact (List<CasinoContacts> entities) {

        List<CasinoContactBean> beans = new ArrayList<>();

        for (CasinoContacts entity : entities) {
            beans.add(getBeanFromEntityForContact(entity));
        }
        return beans;
    }

    public CasinoContactBean getBeanFromEntityForContact (CasinoContacts entity) {

        if (null != entity) {
            CasinoContactBean bean = new CasinoContactBean();

            populateBaseBeanFromEntity(bean, entity);
            bean.setContactId(entity.getContactId());
            bean.setType(entity.getType());

            try{bean.setContact(_contactConverter.getBeanFromEntityForCasino(entity.getContact()));}
            catch (Exception e){}

            return bean;
        } else
            return null;
    }
}
