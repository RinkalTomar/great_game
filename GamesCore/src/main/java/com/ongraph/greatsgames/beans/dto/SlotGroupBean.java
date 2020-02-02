package com.ongraph.greatsgames.beans.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SlotGroupBean extends AbstractAuditableBean {
    
     String name;
     String description;
     Integer numberOfMachines;

    //private List<SlotMachine> slotMachine = new ArrayList();
    List<SlotMachineBean>slotMachines=new ArrayList<>();
}
