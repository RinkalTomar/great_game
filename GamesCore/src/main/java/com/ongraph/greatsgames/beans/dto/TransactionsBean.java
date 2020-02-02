package com.ongraph.greatsgames.beans.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(value = "Transaction Record", description = "Captures a Transaction Record")
public class TransactionsBean extends AbstractAuditableBean {

    String transactionNote;

    BigDecimal amount;

    //@ApiModelProperty(readOnly = true)
    Long cashboxId;

    Long categoryId;

    @ApiModelProperty(readOnly = true)
    String transactionId;

    @ApiModelProperty(hidden = true)
    TransactionType type;

    @ApiModelProperty(readOnly = true)
    CashBoxBean cashbox;

    @ApiModelProperty(readOnly = true)
    BigDecimal updatedBalance;

    @ApiModelProperty(readOnly = true)
    Long transactionNumber;

    @ApiModelProperty(name = "_to")
    Long tranferToCashbox;

    @ApiModelProperty(name = "category", readOnly = true)
    @JsonProperty(value = "category", access = JsonProperty.Access.READ_ONLY)
    TransactionCategoryBean categoryBean;

}
