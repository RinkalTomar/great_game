package com.ongraph.greatsgames.beans.dto.http.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAcountResetOrActivateRequest {

    String email;
    String password;
    String emailCommunicationToken;
}
