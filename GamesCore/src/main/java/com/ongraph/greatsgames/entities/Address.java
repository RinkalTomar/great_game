package com.ongraph.greatsgames.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Address extends BaseEntity{

    String street;

    String innerValue;
    String outerValue;
    String city;
    String state;
    String zipcode;
}
