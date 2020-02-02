package com.ongraph.greatsgames.beans.dto;

import lombok.Getter;
import lombok.Setter;
import com.ongraph.greatsgames.enums.Enumeration.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmailEventBean extends BaseBean {

    String reciever;
    EmailSubjects subject;
    String content;

    Boolean hasAttachment;
    String attachmentName;


    EmailEventStatus status;

    Blob attachmentContent;

    LocalDateTime sentOn;

}
