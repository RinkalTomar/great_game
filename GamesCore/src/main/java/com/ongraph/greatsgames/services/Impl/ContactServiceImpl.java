package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.beans.dto.ClientBean;
import com.ongraph.greatsgames.beans.dto.ContactBean;
import com.ongraph.greatsgames.beans.dto.search.ContactSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.converters.CasinoContactConverter;
import com.ongraph.greatsgames.converters.ContactConverter;
import com.ongraph.greatsgames.dao.CasinoContactRepository;
import com.ongraph.greatsgames.dao.ContactRepository;
import com.ongraph.greatsgames.dao.hibernate.CasinoContactDao;
import com.ongraph.greatsgames.dao.hibernate.ContactDao;
import com.ongraph.greatsgames.entities.CasinoContacts;
import com.ongraph.greatsgames.entities.Contact;
import com.ongraph.greatsgames.enums.Enumeration;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ContactServiceImpl extends AbstractService implements IContactService {

    @Autowired
    ContactRepository _contactRepository;

    @Autowired
    ContactConverter _contactConverter;

    @Autowired
    ContactDao _contactDao;

    @Autowired
    CasinoContactDao _casinoContactDao;

    @Autowired
    CasinoContactConverter _casinoContactConverter;


    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public Long saveOrUpdateContact(ContactBean bean) throws Exception {

        Contact entity = new Contact();

        entity.setEmail(bean.getEmail());
        entity.setName(bean.getName());
        entity.setPhoneNumber(bean.getContactNumber());

        return _contactRepository.save(entity).getId();

    }

    @Override
    @Transactional
    public void getAllContacts(AbstractResponseBean response, ContactSearchCriteria searchCriteria) throws Exception {

        //TODO:ValidateSearchCriteria
        int count = _contactDao.count(searchCriteria);
        response.setObjects(_contactConverter.getBeansFromEntities(_contactDao.search(searchCriteria,false)));
        response.setTotalResultsCount(count);
        response.setCurrentPageIndex(searchCriteria.getPageNumber());
        response.setResultCountPerPage(response.getObjects().size());
        response.setAuthenticated(_coreService.isAuthenticated());
        response.setSuccess(true);
    }

    @Override
    @Transactional
    public void getContactById(AbstractResponseBean response, Long id) throws Exception {
        ContactSearchCriteria searchCriteria = new ContactSearchCriteria();
        searchCriteria.getSelectedIds().add(id);
        ContactBean bean;
        if(_contactDao.count(searchCriteria) > 0) {
            bean = _contactConverter.getBeanFromEntity(_contactDao.search(searchCriteria, false).get(0),Enumeration.ResultType.SELECTION);
            response.setObject(bean);
            response.setObjectId(bean.getId());
            response.setSuccess(true);
            response.setAuthenticated(_coreService.isAuthenticated());
        }else
            throw new BadRequestException("");
    }

    @Override
    @Transactional
    public void deleteCasino(AbstractResponseBean responseBean, List<Long> ids) throws Exception {
        for(Long id:ids){
            CasinoContacts casinoContacts = _casinoContactDao.getById(id);
            _casinoContactDao.delete(casinoContacts);
        }
        responseBean.setObjectId(ids);
        responseBean.setSuccess(true);
    }

    @Override
    @Transactional
    public void update(AbstractResponseBean responseBean, ContactBean bean)throws Exception {
        Contact contact = _contactDao.getById(bean.getId());
        contact.setEmail(bean.getEmail());
        contact.setName(bean.getName());
        contact.setPhoneNumber(bean.getContactNumber());
        _contactDao.update(contact);
        responseBean.setObjectId(bean.getId());
        responseBean.setSuccess(true);
    }

    @Override
    @Transactional
    public void createCasinoContact(AbstractResponseBean responseBean, CasinoContactBean bean) throws Exception {
        CasinoContacts casinoContacts = _casinoContactConverter.getEntityFromBean(bean);
        _casinoContactDao.save(casinoContacts);
        responseBean.setObjectId(casinoContacts.getId());
        responseBean.setSuccess(true);
    }


}
