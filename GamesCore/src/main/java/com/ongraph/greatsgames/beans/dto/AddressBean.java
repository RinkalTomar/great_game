package com.ongraph.greatsgames.beans.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressBean extends BaseBean {

    String street;
    String innerValue;
    String outerValue;
    String city;
    String state;
    String zipcode;

}
