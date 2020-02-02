package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SlotModel extends AbstractAuditableEntity {

    String modelName;
    String ModelNo;

    Long brandId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="brandId",updatable=false,insertable = false)
    Brand brand;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "slotModel")
    List<SlotMachine> slotMachines=new ArrayList<>();

}
