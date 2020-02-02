package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.beans.dto.CasinoContactBean;
import com.ongraph.greatsgames.converters.CasinoContactConverter;
import com.ongraph.greatsgames.dao.ContactRepository;
import com.ongraph.greatsgames.dao.CasinoContactRepository;
import com.ongraph.greatsgames.entities.CasinoContacts;
import com.ongraph.greatsgames.services.ICasinoContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CasinoContactsServiceImpl extends AbstractService implements ICasinoContactService {

    @Autowired
    CasinoContactConverter _casinoContactConverter;

    @Autowired
    ContactRepository _contactRepository;

    @Autowired
    CasinoContactRepository _casinoContactRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createCasinoContacts(List<CasinoContactBean> casinoContactBeans) throws Exception
    {
        List<CasinoContacts> casinoContacts = _casinoContactConverter.getEntitiesFromBeans(casinoContactBeans);

        for(CasinoContacts casinoContact : casinoContacts)
        {
            Long contact_id = _contactRepository.save(casinoContact.getContact()).getId();
            casinoContact.setContactId(contact_id);
        }

        _casinoContactRepository.saveAll(casinoContacts);
    }
}
