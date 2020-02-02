package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CashBoxBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.dto.search.CashBoxSearchCriteria;
import com.ongraph.greatsgames.beans.dto.search.ClientSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.ClientConverter;
import com.ongraph.greatsgames.dao.AddressRepository;
import com.ongraph.greatsgames.dao.ClientRepository;
import com.ongraph.greatsgames.dao.hibernate.ClientDao;
import com.ongraph.greatsgames.entities.Client;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.IAddressService;
import com.ongraph.greatsgames.services.IClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl extends AbstractService implements IClientService {

    private static final Logger _log = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    ClientConverter _clientConverter;

    @Autowired
    ClientDao _clientDao;

    @Autowired
    ClientRepository _clientRepository;

    @Autowired
    AddressRepository _addressRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Long saveOrUpdateClient(ClientBean body) throws Exception
    {
        Client client = _clientConverter.getEntityFromBean(body);
        _clientRepository.save(client);

        //Save Address
        if(client.getAddress() != null)
            _addressRepository.save(client.getAddress());


        return client.getId();

    }

    @Override
    public void getAllClient(AbstractResponseBean responseBean, ClientSearchCriteria searchCriteria) throws Exception {

        validateSearchCriteria(searchCriteria);
        int count = _clientDao.count(searchCriteria);
        List<ClientBean> beans = _clientConverter.getBeansFromEntities(_clientDao.search(searchCriteria, false));
        responseBean.setTotalResultsCount(count);
        responseBean.setResultCountPerPage(beans.size());
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setObjects(beans);
    }


    @Override
    public ClientBean getClientById(Long id) throws Exception {
        ClientSearchCriteria searchCriteria = new ClientSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        validateSearchCriteria(searchCriteria);
        if(_clientDao.count(searchCriteria) > 0) {
            return  _clientConverter.getBeanFromEntity(_clientDao.getById(id), Enumeration.ResultType.SELECTION);

        }
        throw new GreatGamesAuthorizationException();
    }

    private void validateSearchCriteria(ClientSearchCriteria searchCriteria) throws Exception{
        if(!_coreService.hasPermission("CLIENT_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();
            if(searchCriteria.getSelectedUserId().isEmpty())
            {
                searchCriteria.getSelectedUserId().add(logg_in_user);
            }
            else
            {
                if(!searchCriteria.getSelectedUserId().contains(logg_in_user))
                    throw new GreatGamesAuthorizationException();
                else{
                    //Remove additional Ids which are not accessible to the current user.
                    searchCriteria.getSelectedUserId().removeIf(i -> i != logg_in_user);
                }

                if(searchCriteria.getSelectedUserId().isEmpty())
                    throw new GreatGamesAuthorizationException();
            }

        }
    }


}
