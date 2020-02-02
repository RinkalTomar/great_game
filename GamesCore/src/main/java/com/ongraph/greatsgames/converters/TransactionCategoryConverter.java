package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.TransactionCategoryBean;
import com.ongraph.greatsgames.entities.TransactionCategories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionCategoryConverter extends BaseConverter {

    public TransactionCategories getEntityFromBean(TransactionCategoryBean bean)
    {
        TransactionCategories entity = new TransactionCategories();

        try{
            populateBaseEntityFromBean(bean,entity);
            entity.setCategory(bean.getCategory());
            entity.setIsExpense(bean.getIsExpense());
        }catch (Exception e)
        { }
        return entity;

    }

    public List<TransactionCategoryBean> getBeansFromEntities(List<TransactionCategories> entities)
    {
        List<TransactionCategoryBean> beans = new ArrayList<TransactionCategoryBean>();
        for (TransactionCategories entity: entities) {
            beans.add(getBeanFromEntity(entity));
        }

        return beans;
    }

    private TransactionCategoryBean getBeanFromEntity(TransactionCategories entity) {

        TransactionCategoryBean bean = new TransactionCategoryBean();

        try{
            populateBaseBeanFromEntity(bean,entity);
            bean.setCategory(entity.getCategory());
            bean.setIsExpense(entity.getIsExpense());
            bean.setCategoryType(entity.getCategoryType());
        }catch (Exception e)
        { }
        return bean;
    }
}
