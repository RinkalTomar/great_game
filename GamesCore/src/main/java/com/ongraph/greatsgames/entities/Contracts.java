package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;
import com.ongraph.greatsgames.enums.Enumeration.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Contracts extends AbstractAuditableEntity{

    String ContractNumber;

    @Column(nullable = false)
    Long casinoId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Casino.class)
    @JoinColumn(name = "casinoId", insertable = false, updatable = false)
    Casino casino;

    ContractStatus status;


    Boolean isRent;

    LocalDate startDate;

    Boolean isBracketsPaid;

    Boolean isDeliveryPaid;

    Integer totalNumberOfMachines;
}
