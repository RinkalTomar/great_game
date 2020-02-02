package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.ContractBean;
import com.ongraph.greatsgames.entities.Contracts;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractsConverter extends AbstractAuditableConverter {


    public List<ContractBean> getBeansFromEntities (List<Contracts> entityList) {

        List<ContractBean> beans = new ArrayList<>();
        for (Contracts entity : entityList) {
            beans.add(getBeanFromEntity(entity));
        }
        return beans;
    }

    public ContractBean getBeanFromEntity (Contracts entity) {

        if (null != entity) {
            ContractBean bean = new ContractBean();

            populateAuditableBeanFromEntity(bean, entity);
            bean.setContractNumber(entity.getContractNumber());
            bean.setIsBracketsPaid(entity.getIsBracketsPaid());
            bean.setIsDeliveryPaid(entity.getIsDeliveryPaid());
            bean.setIsRent(entity.getIsRent());
            bean.setTotalNumberOfMachines(entity.getTotalNumberOfMachines());
            bean.setCasinoId(entity.getCasinoId());
            bean.setStatus(entity.getStatus());
            return bean;
        } else
            return null;
    }

    public List<Contracts> getEntitiesFromBeans (List<ContractBean> beanList) {

        List<Contracts> entities = new ArrayList<>();
        for (ContractBean bean : beanList) {
            entities.add(getEntityfromBean(bean));
        }
        return entities;
    }

    public Contracts getEntityfromBean (ContractBean bean) {

        if (null != bean) {

            Contracts entity = new Contracts();

            populateAuditableEntityFromBean(bean, entity);

            entity.setContractNumber(bean.getContractNumber());
            entity.setIsBracketsPaid(bean.getIsBracketsPaid());
            entity.setIsDeliveryPaid(bean.getIsDeliveryPaid());
            entity.setIsRent(bean.getIsRent());
            entity.setTotalNumberOfMachines(bean.getTotalNumberOfMachines());
            entity.setCasinoId(bean.getCasinoId());
            entity.setStatus(bean.getStatus());

            return entity;
        } else {
            return null;
        }
    }
}
