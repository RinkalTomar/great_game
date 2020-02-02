package com.ongraph.greatsgames.entities;


import lombok.Getter;
import lombok.Setter;

import com.ongraph.greatsgames.enums.Enumeration.CasinoStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Casino extends AbstractAuditableEntity{

    String name;
    String publicDocumentFileName;
    String publicDocumentUrl;
    String publicDocumentDate;
    String publicDocumentDueDate;
    String publicDocumentNotary;
    String publicDocumentRegister;
    String publicDocumentState;
    String legalRepresentative;
    String notraryEstate;
    Long publicNotaryNumber;
    /*Long cashlessSystemId;*/

    Long clientId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
    @JoinColumn(name = "clientId", nullable = false, insertable = false, updatable = false)
    Client client;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
    @JoinColumn(name = "addressId",nullable = false, insertable = false, updatable = false)
    Address address;

    @Enumerated(EnumType.STRING)
    CasinoStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "casinoId",targetEntity = CasinoContacts.class)
    List<CasinoContacts> casinoContacts;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "casino")
    List<SlotMachine>slotMachines=new ArrayList<>();


}
