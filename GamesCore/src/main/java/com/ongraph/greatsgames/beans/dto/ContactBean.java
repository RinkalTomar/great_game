package com.ongraph.greatsgames.beans.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Captures identifying Information Who can logs into the Great Games Applicaiton", value = "Contact")
public class ContactBean extends AbstractAuditableBean{

    String email;
    String name;
    String contactNumber;
    List<CasinoContactBean> casinoContacts;
}
