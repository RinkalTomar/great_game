package com.ongraph.greatsgames.beans.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BrandBean extends AbstractAuditableBean {
    String name;
    List<SlotModelBean> slotModelBean=new ArrayList<>();
    Integer numberOfModel;
}
