package com.ongraph.greatsgames.beans.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlotModelBean extends AbstractAuditableBean {

    @ApiModelProperty(required = true)
    @NotNull
    String modelName;

    String modelNo;

    Long brandId;
    BrandBean brand;

    List<SlotMachineBean>slotMachines=new ArrayList<>();
}
