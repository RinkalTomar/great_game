package com.ongraph.greatsgames.beans.dto;


import com.ongraph.greatsgames.enums.Enumeration.CasinoStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CasinoBean extends AbstractAuditableBean {

    String name;
    String publicDocumentFileName;
    String publicDocumentUrl;
    String publicDocumentDate;
    String publicDocumentDueDate;
    String publicDocumentNotary;
    String publicDocumentRegister;
    String publicDocumentState;
    String legalRepresentative;
    String notraryEstate;

    Long publicNotaryNumber;

    Long addressId;
    AddressBean address;

    Long clientId;
    ClientBean client;

    List<CasinoContactBean> contacts = new ArrayList<>();

    @ApiModelProperty(readOnly = true, reference = "CasinoStatus")
    CasinoStatus status;

    List<SlotMachineBean>slotMachines=new ArrayList<>();
}
