package com.ongraph.greatsgames.beans.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBean extends AbstractAuditableBean{

    String bussinessName;
    String taxationId;

    Long addressId;
    AddressBean ficalAddress;

}
