package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.entities.Cashbox;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CashBoxConverter extends AbstractAuditableConverter {

    @Resource
    UserConverter _userConverter;

    @Resource
    TransactionsConverter _transactionsConverter;

    public Cashbox getEntityFromBean(CashBoxBean bean) {
        Cashbox entity = new Cashbox();
        try {
            populateAuditableEntityFromBean(bean,entity);
            entity.setName(bean.getName());
            entity.setNote(bean.getNote());
            entity.setBalance(bean.getBalance());
            entity.setUserId(bean.getUserId());
            /*entity.setUser(_userConverter.getEntityfromBean(bean.getUser()));*/
            entity.setTransactionsList(_transactionsConverter.getEntitiesFromBean(bean.getTransactions()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public CashBoxBean getBeanFromEntity(Cashbox entity) {
        CashBoxBean bean = new CashBoxBean();
        try {
            populateAuditableBeanFromEntity(bean, entity);
            bean.setName(entity.getName());
            bean.setNote(entity.getNote());
            bean.setBalance(entity.getBalance());
            bean.setUserId(entity.getUserId());
        } catch (Exception e) {
        }
        return bean;
    }

    public List<CashBoxBean> getBeansFromEntity(List<Cashbox> entities) {
        List<CashBoxBean> beans = new ArrayList<CashBoxBean>();

        for (Cashbox entity : entities) {
            beans.add(getBeanFromEntity(entity));
        }
        return beans;

    }

    public CashBoxBean getBeanFromEntity(Cashbox entity, Enumeration.ResultType type) {

        CashBoxBean bean = new CashBoxBean();
        try {
            bean = getBeanFromEntity(entity);
            if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {
                bean.setTransactions(_transactionsConverter.getBeansFromEntities(entity.getTransactionsList()));
                entity.setUserId(bean.getUserId());
            }
        }catch (Exception e) {e.printStackTrace();}
        return bean;
    }
}
