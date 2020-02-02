package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.AddressBean;
import com.ongraph.greatsgames.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter extends BaseConverter {

    public Address getEntityFromBean(AddressBean bean)
    {
        Address entity = new Address();

        populateBaseEntityFromBean(bean, entity);

        entity.setCity(bean.getCity());
        entity.setInnerValue(bean.getInnerValue());
        entity.setOuterValue(bean.getOuterValue());
        entity.setState(bean.getState());
        entity.setStreet(bean.getStreet());
        entity.setZipcode(bean.getZipcode());

        return entity;
    }

    public AddressBean getBeanFromEntity(Address entity)
    {
        AddressBean bean = new AddressBean();

        populateBaseBeanFromEntity(bean, entity);

        bean.setCity(entity.getCity());
        bean.setInnerValue(entity.getInnerValue());
        bean.setOuterValue(entity.getOuterValue());
        bean.setState(entity.getState());
        bean.setStreet(entity.getStreet());
        bean.setZipcode(entity.getZipcode());

        return bean;
    }
}
