package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.entities.Transactions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionsConverter extends AbstractAuditableConverter {

    @Resource
    CashBoxConverter cashBoxConverter;

    public Transactions getEntityFromBean(TransactionsBean bean) {
        Transactions entity = new Transactions();
        try {
            populateAuditableEntityFromBean(bean, entity);
            entity.setAmount(bean.getAmount());
            entity.setCashboxId(bean.getCashboxId());
            entity.setNote(bean.getTransactionNote());
            entity.setType(bean.getType());
            entity.setTransactionId(bean.getTransactionId());
            entity.setUpdatedBalance(bean.getUpdatedBalance());
            entity.setCategoryId(bean.getCategoryId());
        } catch (Exception e) {
        }
        return entity;

    }

    public List<Transactions> getEntitiesFromBean(List<TransactionsBean> transactionsBeanList) {

        List<Transactions> transactions = new ArrayList<Transactions>();

        for (TransactionsBean bean : transactionsBeanList) {
            transactions.add(getEntityFromBean(bean));
        }

        return transactions;
    }

    public TransactionsBean getBeanFromEnitity(Transactions entity) {

        TransactionsBean bean = new TransactionsBean();
        try {
            populateAuditableBeanFromEntity(bean, entity);
            bean.setAmount(entity.getAmount());
            bean.setCashboxId(entity.getCashboxId());
            bean.setTransactionNote(entity.getNote());
            bean.setType(entity.getType());
            bean.setTransactionId(entity.getTransactionId());
            bean.setUpdatedBalance(entity.getUpdatedBalance());
        } catch (Exception e) {

        }
        return bean;
    }

    public List<TransactionsBean> getBeansFromEntities(List<Transactions> entities) {
        List<TransactionsBean> beans = new ArrayList<TransactionsBean>();

        for (Transactions entity : entities) {
            beans.add(getBeanFromEnitity(entity));
        }

        return beans;
    }
}
