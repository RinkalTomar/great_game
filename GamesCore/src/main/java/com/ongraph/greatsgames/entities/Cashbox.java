package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cashbox")
public class Cashbox extends AbstractAuditableEntity {

    String name;
    String Note;


    @Column(precision = 10, scale = 2)
    BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cashboxId")
    List<Transactions> transactionsList = new ArrayList<Transactions>();

    Long userId;

    @ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    Users user;
}
