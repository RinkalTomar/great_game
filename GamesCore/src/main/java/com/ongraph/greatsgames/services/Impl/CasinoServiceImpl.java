package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CasinoBean;
import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.beans.dto.search.CasinoSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.CasinoConverter;
import com.ongraph.greatsgames.dao.AddressRepository;
import com.ongraph.greatsgames.dao.CasinoRepository;
import com.ongraph.greatsgames.dao.ClientRepository;
import com.ongraph.greatsgames.dao.hibernate.CasinoDao;
import com.ongraph.greatsgames.entities.Casino;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.services.ICasinoContactService;
import com.ongraph.greatsgames.services.ICasinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CasinoServiceImpl extends AbstractService implements ICasinoService {

    @Autowired
    CasinoConverter _casinoConverter;

    @Autowired
    ClientRepository _clientRepository;

    @Autowired
    AddressRepository _addressRepository;

    @Autowired
    CasinoRepository _casinoRepository;

    @Autowired
    ICasinoContactService _casinoContactService;

    @Autowired
    CasinoDao _casinoDao;


    @Override
    public Long saveOrUpdateCasino(CasinoBean body) throws Exception
    {
        Casino casino = _casinoConverter.getEntityFromBean(body);

        if(body.getClientId() != null && !_clientRepository.existsById(body.getClientId()))
            throw new BadRequestException(_message.getMessage("invalid.client.identifier"));
        else {
            if(body.getClient() != null)
            {
                _clientRepository.save(casino.getClient());
                casino.setClientId(casino.getClient().getId());
            }

        }
        if(casino.getAddress() != null)
            _addressRepository.save(casino.getAddress());
        else throw new BadRequestException(_message.getMessage("address.undefined"));

        _casinoRepository.save(casino);
        List<CasinoContactBean> casinoContactBeans = body.getContacts();

        for(CasinoContactBean casinoContact : casinoContactBeans )
            casinoContact.setCasinoId(casino.getId());

        _casinoContactService.createCasinoContacts(casinoContactBeans);
        return casino.getId();
    }

    @Override
    public void getallCasino(AbstractResponseBean responseBean, CasinoSearchCriteria searchCriteria) throws Exception {
        int count = _casinoDao.count(searchCriteria);
        List<CasinoBean> casinos = _casinoConverter.getBeansFromEntities(_casinoDao.search(searchCriteria,false),searchCriteria.getResultType());
        responseBean.setTotalResultsCount(count);
        responseBean.setCurrentPageIndex(searchCriteria.getPageNumber());
        responseBean.setResultCountPerPage(casinos.size());
        responseBean.setObjects(casinos);
    }

    @Override
    public CasinoBean getCasinoById(Long id) throws Exception {
        return _casinoConverter.getBeanFromEntity(_casinoDao.getById(id), Enumeration.ResultType.SELECTION);
    }


    private void validateSearchCriteria(CasinoSearchCriteria searchCriteria) throws Exception
    {
        if(!_coreService.hasPermission("CASINO_SEARCH"))
        {
            Long logg_in_user = _coreService.getCurrentUser().getId();

        }
    }
}
