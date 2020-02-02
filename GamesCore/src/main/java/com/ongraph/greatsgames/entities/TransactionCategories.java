package com.ongraph.greatsgames.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class TransactionCategories extends BaseEntity{

    @Column(unique = true, nullable = false)
    String category;

    Boolean isExpense = false;

    String categoryType;
}
