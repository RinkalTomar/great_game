package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.ContractBean;
import com.ongraph.greatsgames.beans.dto.search.ContactSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.ContractsSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.ContractsConverter;
import com.ongraph.greatsgames.dao.ContractsRepository;
import com.ongraph.greatsgames.dao.hibernate.ContractsDao;
import com.ongraph.greatsgames.entities.Contracts;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.IContractsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ContractsServiceImpl extends AbstractService implements IContractsService {

    private Logger _log = LogManager.getLogger(ContractsServiceImpl.class);

    @Autowired
    ContractsRepository _contractsRepository;

    @Resource
    ContractsConverter _contractsConverter;

    @Resource
    MessageProperties _message;

    @Autowired
    ContractsDao _contractsDao;

    @Override
    public void getallContracts(AbstractResponseBean responseBean, ContractsSearchCriteria searchCriteria) throws Exception {
        int count = _contractsDao.count(searchCriteria);
        List<ContractBean> beans = _contractsConverter.getBeansFromEntities(_contractsDao.search(searchCriteria,false));
        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(beans);
    }

    @Override
    public ContractBean getContractById(Long id) throws Exception {
        ContractsSearchCriteria searchCriteria = new ContractsSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        if(_contractsDao.count(searchCriteria) > 0) {
            return _contractsConverter.getBeanFromEntity(_contractsDao.getById(id));
        }
        throw new GreatGamesAuthorizationException();
    }

    @Override
    public Long saveContract(ContractBean contractsBean) throws Exception {
        return _contractsRepository.save(_contractsConverter.getEntityfromBean(contractsBean)).getId();
    }


    private void validateSearchCriteria(ContactSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("CONTRACT_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
