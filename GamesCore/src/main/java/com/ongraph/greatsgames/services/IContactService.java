package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.beans.dto.search.ContactSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface IContactService {

    Long saveOrUpdateContact(ContactBean bean) throws Exception;
    void getAllContacts(AbstractResponseBean response, ContactSearchCriteria searchCriteria)throws Exception;
    void getContactById(AbstractResponseBean response, Long id)throws Exception;
    void deleteCasino(AbstractResponseBean responseBean,List<Long> id)throws Exception;
    void update(AbstractResponseBean responseBean , ContactBean bean)throws Exception;
    void createCasinoContact(AbstractResponseBean responseBean, CasinoContactBean bean)throws Exception;
}
