package com.ongraph.greatsgames.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class SlotMachine extends AbstractAuditableEntity {

     String slotInfo;
     String serialNo;
     Date   manufacturingDate;
     String topper;
     String photo;
     String notes;
     String cabinetColour;
     Long   warehouseId;
     Long   warehouseCountId;
     Long   cpuId;
     Long   installedGame;
     Long   nomId;
     Long   invoiceId;
     Long   configurationId;
     Long   slotGroupId;
     Long   casinoId;
     Long   modelId;
     Long buttonPanelId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "modelId",updatable = false,insertable = false)
     SlotModel slotModel;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="slotGroupId",updatable=false,insertable=false)
     SlotGroup slotGroup;

     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="casinoId",insertable = false,updatable = false)
     Casino casino;

     @ManyToOne(fetch = FetchType.LAZY)
      @JoinColumn(name="cpuId",updatable = false,insertable = false)
     Cpu cpu;
    
/*
     SlotStatus status;
     Cabinet cabinet;
     ButtonPanel buttonPanel;

*/

    
}
