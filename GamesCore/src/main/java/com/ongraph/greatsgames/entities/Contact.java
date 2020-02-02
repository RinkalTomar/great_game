package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
public class Contact extends AbstractAuditableEntity {

    String name;
    String email;
    String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactId", targetEntity = CasinoContacts.class)
    List<CasinoContacts> casinos;


}
