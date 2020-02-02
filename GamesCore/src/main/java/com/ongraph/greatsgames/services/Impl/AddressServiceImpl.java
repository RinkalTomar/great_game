package com.ongraph.greatsgames.services.Impl;


import com.ongraph.greatsgames.beans.dto.AddressBean;
import com.ongraph.greatsgames.converters.AddressConverter;
import com.ongraph.greatsgames.dao.AddressRepository;
import com.ongraph.greatsgames.entities.Address;
import com.ongraph.greatsgames.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl extends AbstractService implements IAddressService{

    @Autowired
    AddressConverter _addressConverter;

    @Autowired
    AddressRepository _addressRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long saveAddress(AddressBean body) throws Exception
    {
        Address address = _addressConverter.getEntityFromBean(body);
        _addressRepository.save(address).getId();

        return address.getId();
    }

}
