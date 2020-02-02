package com.ongraph.greatsgames.beans.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseBean {

    Long id;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date creationDatetime;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date updateDatetime;

}
