package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.entities.Contact;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactConverter extends AbstractAuditableConverter{

    @Autowired
    CasinoContactConverter _casinoContactConverter;

    public Contact getEntityFromBean(ContactBean bean)
    {
        Contact entity = new Contact();

        populateAuditableEntityFromBean(bean, entity);
        entity.setName(bean.getName());
        entity.setPhoneNumber(bean.getContactNumber());
        entity.setEmail(bean.getEmail());

        return entity;
    }

    public List<ContactBean> getBeansFromEntities (List<Contact> entityList) {

        List<ContactBean> beans = new ArrayList<>();
        for (Contact entity : entityList) {
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

    public ContactBean getBeanFromEntity (Contact entity) {

        if (null != entity) {
            ContactBean bean = new ContactBean();
            populateAuditableBeanFromEntity(bean, entity);

            bean.setEmail(entity.getEmail());
            bean.setName(entity.getName());
            bean.setContactNumber(entity.getPhoneNumber());
            return bean;
        } else
            return null;
    }

    public ContactBean getBeanFromEntity(Contact entity, Enumeration.ResultType type) {

        ContactBean bean = new ContactBean();
        try {
            bean = getBeanFromEntity(entity);
            if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                bean.setCasinoContacts(_casinoContactConverter.getBeansFromEntitiesForCasino(entity.getCasinos()));

            }
        }catch (Exception e) {e.printStackTrace();}
        return bean;
    }
    public ContactBean getBeanFromEntityForCasino (Contact entity) {

        if (null != entity) {
            ContactBean bean = new ContactBean();
            populateAuditableBeanFromEntity(bean, entity);

            bean.setEmail(entity.getEmail());
            bean.setName(entity.getName());
            bean.setContactNumber(entity.getPhoneNumber());
            return bean;
        } else
            return null;
    }
}
