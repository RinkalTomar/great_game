package com.ongraph.greatsgames.beans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResetBean  {
        String token;
        String password;
}
