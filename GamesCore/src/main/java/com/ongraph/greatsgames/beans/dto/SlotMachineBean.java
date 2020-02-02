package com.ongraph.greatsgames.beans.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SlotMachineBean extends AbstractAuditableBean {
    String slotInfo;
    String serialNo;
    Date manufacturingDate;
    String topper;
    String photo;
    String notes;
    String cabinetColour;
    Long   warehouseId;
    Long   warehouseCountId;

    Long   installedGame;
    Long   nomId;
    Long   invoiceId;
    Long   configurationId;
    Long buttonPanelId;

    Long   modelId;
    SlotModelBean slotModel;

    Long   slotGroupId;
    SlotGroupBean slotGroup;

    Long   casinoId;
    CasinoBean casino;

    Long   cpuId;
    CpuBean cpu;
    }