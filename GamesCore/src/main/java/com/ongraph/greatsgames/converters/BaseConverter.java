package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.BaseBean;
import com.ongraph.greatsgames.entities.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseConverter {


    public void populateBaseBeanFromEntity(BaseBean bean, BaseEntity entity) {
        bean.setId(entity.getId());
        bean.setCreationDatetime(entity.getCreationDatetime());
        bean.setUpdateDatetime(entity.getUpdateDatetime());

    }

    public void populateBaseEntityFromBean(BaseBean bean, BaseEntity entity) {
        entity.setId(bean.getId());
        entity.setCreationDatetime(bean.getCreationDatetime());
        entity.setUpdateDatetime(bean.getUpdateDatetime());
    }
}
