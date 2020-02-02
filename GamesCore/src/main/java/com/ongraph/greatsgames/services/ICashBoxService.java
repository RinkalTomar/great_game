package com.ongraph.greatsgames.services;


import com.ongraph.greatsgames.beans.dto.AbstractAuditableBean;
import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;

import java.util.List;

public interface ICashBoxService {

    Long createCashBox(CashBoxBean bean) throws Exception;

    void getAllCasBoxes(AbstractResponseBean response, CashBoxSearchCriteria searchCriteria) throws Exception;

    CashBoxBean getCashboxById(Long id) throws Exception;

    void updateBalance(Long cashboxId) throws Exception;

    void remove(Long id) throws Exception;

    Integer count(CashBoxSearchCriteria searchCriteria) throws Exception;

    Boolean validateCashBoxAccess(Long id) throws Exception;

    void updateCashBox(CashBoxBean cashBoxBean)throws Exception;

    void update(CashBoxBean cashBoxBean,AbstractResponseBean responseBean);

}
