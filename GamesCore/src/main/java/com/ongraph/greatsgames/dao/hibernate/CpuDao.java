package com.ongraph.greatsgames.dao.hibernate;

import com.ongraph.greatsgames.beans.dto.search.CpuSearchCriteria;
import com.ongraph.greatsgames.entities.Cpu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ongraph on 17/9/18.
 */
@Repository
public class CpuDao extends AbstractDao<Cpu>{

    public CpuDao(){super(Cpu.class);}

    public int count(CpuSearchCriteria searchCriteria) {
        int totalRows = 0;
        TypedQuery<Long> query = getSearchQuery(searchCriteria, true,  Long.class);
        totalRows = query.getResultList().get(0).intValue();
        return totalRows;
    }

    public List<Cpu> search(CpuSearchCriteria searchCriteria, boolean isExportRequest) {
        TypedQuery<Cpu> query = getSearchQuery(searchCriteria, false, Cpu.class);
       /* if (!isExportRequest) {
            addPagingParameters(query, searchCriteria);
        }*/
        return query.getResultList();
    }

    public <T> TypedQuery<T> getSearchQuery(CpuSearchCriteria searchCriteria, boolean isCountQuery,  Class<T> type) {
        // standard fields
        Boolean isSearchKeyword = Boolean.FALSE;
        Map<String, Object> paramsMap = new HashMap<>();

        SearchDaoHelper<T> daoHelper = new SearchDaoHelper<T>();

        StringBuffer hql = null;
        if (isCountQuery) {
            hql = new StringBuffer("SELECT count(distinct cb.id)");
        }
        else {
            hql = new StringBuffer("SELECT distinct cb");
        }

        hql.append(" FROM Cpu cb");
        hql.append(" where 1=1 ");

        if (searchCriteria != null && StringUtils.isNotBlank(searchCriteria.getSearchKeyword())) {
            isSearchKeyword = Boolean.TRUE;
            String[] stringFields = {"name"};
            String[] numberFields = {};
            hql.append(daoHelper.getSearchWhereStatement(stringFields, numberFields, searchCriteria.getSearchKeyword(),
                    true));
        }

        if (daoHelper.isCriteriaListIsEmpty(searchCriteria.getSelectedIds()) == false) {
            {
                hql.append(" AND cb.id IN (:selectedIds)");
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
