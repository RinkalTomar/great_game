package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SlotGroup extends AbstractAuditableEntity  {

     String name;
     String description;
     Integer numberOfMachines;

     @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "slotGroup")
     List<SlotMachine>slotMachines=new ArrayList<>();


}
