package com.ongraph.greatsgames.beans.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CpuBean extends AbstractAuditableBean {

    String name;
    double version;
    List<SlotMachineBean> slotMachine;
}
