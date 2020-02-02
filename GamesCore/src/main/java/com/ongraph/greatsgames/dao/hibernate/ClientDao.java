package com.ongraph.greatsgames.dao.hibernate;

import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.ClientSearchCriteria;
import com.ongraph.greatsgames.entities.Cashbox;
import com.ongraph.greatsgames.entities.Client;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ClientDao extends AbstractDao<Client> {

    public ClientDao() {
        super(Client.class);
    }

    public int count(ClientSearchCriteria searchCriteria) {
        int totalRows = 0;
        TypedQuery<Long> query = getSearchQuery(searchCriteria, true,  Long.class);
        totalRows = query.getResultList().get(0).intValue();
        return totalRows;
    }

    public List<Client> search(ClientSearchCriteria searchCriteria, boolean isExportRequest) {
        TypedQuery<Client> query = getSearchQuery(searchCriteria, false, Client.class);

        /* if (!isExportRequest) {
            addPagingParameters(query, searchCriteria);
        }*/

        return query.getResultList();
    }

    public <T> TypedQuery<T> getSearchQuery(ClientSearchCriteria searchCriteria, boolean isCountQuery,  Class<T> type) {
        // standard fields
        Boolean isSearchKeyword = Boolean.FALSE;
        Map<String, Object> paramsMap = new HashMap<>();

        SearchDaoHelper<T> daoHelper = new SearchDaoHelper<T>();

        StringBuffer hql = null;
        if (isCountQuery) {
            hql = new StringBuffer("SELECT count(distinct client.id)");
        }
        else {
            hql = new StringBuffer("SELECT distinct client");
        }

        hql.append(" FROM Client client");
        hql.append(" where 1=1 ");

        if (searchCriteria != null && StringUtils.isNotBlank(searchCriteria.getSearchKeyword())) {
            isSearchKeyword = Boolean.TRUE;
            String[] stringFields = {"name", "taxId"};
            String[] numberFields = {};
            hql.append(daoHelper.getSearchWhereStatement(stringFields, numberFields, searchCriteria.getSearchKeyword(),
                    true));
        }

        /*if (daoHelper.isCriteriaListIsEmpty(searchCriteria.getSelectedUserId()) == false) {
            {
                hql.append(" AND client.userId IN (:selectedUserId)");
                paramsMap.put("selectedUserId", searchCriteria.getSelectedUserId());
            }

        }*/

        if (daoHelper.isCriteriaListIsEmpty(searchCriteria.getSelectedIds()) == false) {
            {
                hql.append(" AND client.id IN (:selectedIds)");
                paramsMap.put("selectedIds", searchCriteria.getSelectedIds());
            }

        }

        if(!searchCriteria.isReports())
            hql.append(" AND is_deleted = false");

        if(isCountQuery == false && searchCriteria.getSortBy() != null){
            hql.append(" ORDER BY " + searchCriteria.getSortBy() + " " + searchCriteria.getSortOrder());
        }

        TypedQuery<T> q = getSession().createQuery(hql.toString(), type);

        if (isSearchKeyword) {
            daoHelper.setSearchStringValue(q);
        }

        for (Map.Entry<String, Object> parameterEntry : paramsMap.entrySet()) {
            q.setParameter(parameterEntry.getKey(), parameterEntry.getValue());
        }
        return q;

    }
}
