package com.ongraph.greatsgames.beans.dto;

import com.ongraph.greatsgames.enums.Enumeration.ContractStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(description = "Captures the details for the Contracts available per casino", value = "Contract")
public class ContractBean extends AbstractAuditableBean {

    String ContractNumber;
    Long casinoId;
    CasinoBean casino;
    ContractStatus status;

    @ApiModelProperty(notes = "true is the Contract is Rented contract, False By Default")
    Boolean isRent = false;

    LocalDate startDate;

    @ApiModelProperty(notes = "true if the Brackets are already paid, False By Default")
    Boolean isBracketsPaid = false;

    @ApiModelProperty(notes = "true if the Delivery are already paid, False By Default")
    Boolean isDeliveryPaid = false;

    Integer totalNumberOfMachines;
}
