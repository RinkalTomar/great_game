package com.ongraph.greatsgames.beans.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.ongraph.greatsgames.utils.AppUtility;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class CashBoxBean extends AbstractAuditableBean {

    String name;
    String note;

    @ApiModelProperty(readOnly = true)
    @Digits(fraction = 2, integer = 10)
    BigDecimal balance;

    BigDecimal totalIncome;
    BigDecimal totalExpense;

    @ApiModelProperty(required = true)
    Long userId;



    List<TransactionsBean> transactions;

    @ApiModelProperty(readOnly = true, hidden = true)
    UserBean user;

    @JsonGetter(value = "last_updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
    @ApiModelProperty(readOnly = true)
    public Date getLastUpdated()
    {
        return getUpdateDatetime();
    }

}
