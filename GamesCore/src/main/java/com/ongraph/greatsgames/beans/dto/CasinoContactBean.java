package com.ongraph.greatsgames.beans.dto;


import lombok.Getter;
import lombok.Setter;
import com.ongraph.greatsgames.enums.Enumeration.ContactType;

@Getter
@Setter
public class CasinoContactBean extends BaseBean {

    Long contactId;
    ContactBean contact;

    Long casinoId;
    CasinoBean casino;

    ContactType type;

}
