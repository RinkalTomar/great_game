package com.ongraph.greatsgames.services;


import com.ongraph.greatsgames.beans.dto.UsergroupBean;
import com.ongraph.greatsgames.beans.dto.search.UserGroupSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

public interface UserGroupService {


    void getAllUsergroup(AbstractResponseBean<Long, UsergroupBean> responseBean, UserGroupSearchCriteria searchCriteria);
}
