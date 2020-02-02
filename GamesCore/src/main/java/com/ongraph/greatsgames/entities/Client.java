package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Client extends AbstractAuditableEntity {

    String name;
    String taxId;

    Long addressId;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", insertable = false, updatable = false)
    Address address;
}
