package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Brand extends AbstractAuditableEntity {

    String name;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "brand")

    List<SlotModel> slotModel=new ArrayList<>();


}
