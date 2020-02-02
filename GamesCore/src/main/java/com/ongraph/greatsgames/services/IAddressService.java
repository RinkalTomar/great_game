package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.AddressBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface IAddressService {
    @Transactional(propagation = Propagation.REQUIRED)
    Long saveAddress(AddressBean body) throws Exception;
}
