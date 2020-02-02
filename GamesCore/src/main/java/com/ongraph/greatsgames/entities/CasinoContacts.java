package com.ongraph.greatsgames.entities;

import com.ongraph.greatsgames.enums.Enumeration;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "casino_contact_assn")
@Getter
@Setter
public class CasinoContacts extends BaseEntity{


    Long contactId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Contact.class)
    @JoinColumn(name = "contactId", nullable = false, insertable = false, updatable = false)
    Contact contact;

    Long casinoId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Casino.class)
    @JoinColumn(name = "casinoId", nullable = false, insertable = false, updatable = false)
    Casino casino;

    @Enumerated(EnumType.STRING)
    Enumeration.ContactType type;


}
