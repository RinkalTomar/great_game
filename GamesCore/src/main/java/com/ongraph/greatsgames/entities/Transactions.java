package com.ongraph.greatsgames.entities;


import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transactions extends AbstractAuditableEntity {

    String note;

    @Column(precision = 10, scale = 2)
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    TransactionType type;

    Long cashboxId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cashboxId", nullable = false, insertable = false, updatable = false)
    Cashbox cashbox;

    @Column(unique = true, nullable = false)
    String transactionId;

    Long categoryId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false, insertable = false, updatable = false)
    TransactionCategories txnCategory;


    Long transactionNumber;


    @Column(precision = 10, scale = 2)
    BigDecimal updatedBalance;




}
