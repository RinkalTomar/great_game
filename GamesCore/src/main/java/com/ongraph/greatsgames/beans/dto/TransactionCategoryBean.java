package com.ongraph.greatsgames.beans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ongraph.greatsgames.entities.Transactions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionCategoryBean extends BaseBean {

    String category;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    Boolean isExpense;

    String categoryType;
}
