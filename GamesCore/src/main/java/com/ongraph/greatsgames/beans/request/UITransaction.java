package com.ongraph.greatsgames.beans.request;

import com.ongraph.greatsgames.beans.dto.TransactionsBean;
import com.ongraph.greatsgames.enums.Enumeration.TransactionType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UITransaction {


    //This will be populated only in case of Transfer.
    Long toCashbox;
    Long fromCashbox;
    TransactionsBean transaction;
    TransactionType type;

}
