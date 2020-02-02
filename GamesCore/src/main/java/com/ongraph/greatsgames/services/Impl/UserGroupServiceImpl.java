package com.ongraph.greatsgames.services.Impl;

import com.google.common.collect.Lists;
import com.ongraph.greatsgames.beans.dto.UsergroupBean;
import com.ongraph.greatsgames.beans.dto.search.UserGroupSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.UsergroupConverter;
import com.ongraph.greatsgames.dao.hibernate.UserGroupDao;
import com.ongraph.greatsgames.entities.Usergroup;
import com.ongraph.greatsgames.services.UserGroupService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class UserGroupServiceImpl implements UserGroupService{

    @Resource
    UserGroupDao _usergroupRepository;

    @Resource
    UsergroupConverter _usergroupConverter;


    @Override
    public void getAllUsergroup(AbstractResponseBean<Long, UsergroupBean> responseBean, UserGroupSearchCriteria searchCriteria) {

        int count = _usergroupRepository.count(searchCriteria);
        Set<UsergroupBean> beans = _usergroupConverter.getBeansFromEntities(
                _usergroupRepository.search(searchCriteria, false), searchCriteria.getResultType());

        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(Lists.newArrayList(beans));
    }
}
