package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.entities.Cashbox;
import com.ongraph.greatsgames.entities.Client;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientConverter extends AbstractAuditableConverter{

    @Autowired
    AddressConverter _addressConverter;

    public Client getEntityFromBean(ClientBean bean)
    {
        Client entity = new Client();

        try {
            entity.setAddress(_addressConverter.getEntityFromBean(bean.getFicalAddress()));
        }catch (Exception e)
        { }

        entity.setAddressId(bean.getAddressId());
        entity.setName(bean.getBussinessName());
        entity.setTaxId(bean.getTaxationId());

        return entity;
    }

    public List<ClientBean> getBeansFromEntities (List<Client> clients) {

        List<ClientBean> beans = new ArrayList<>();

        for (Client entity : clients) {
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

    public ClientBean getBeanFromEntity (Client entity) {

        if (null != entity) {
            ClientBean bean = new ClientBean();
            populateAuditableBeanFromEntity(bean, entity);
            bean.setAddressId(entity.getAddressId());
            bean.setBussinessName(entity.getName());
            bean.setTaxationId(entity.getTaxId());
            return bean;
        } else
            return null;

    }

    public ClientBean getBeanFromEntity(Client entity, Enumeration.ResultType type) {

        ClientBean bean = new ClientBean();
        try {
            bean = getBeanFromEntity(entity);
            if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {

            }
        }catch (Exception e) {e.printStackTrace();}
        return bean;
    }
}
