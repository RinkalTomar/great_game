package com.ongraph.greatsgames.entities;

import com.ongraph.greatsgames.enums.Enumeration.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmailEvent extends BaseEntity{

    String reciever;

    @Enumerated(EnumType.STRING)
    EmailSubjects subject;

    @Column(columnDefinition = "TEXT")
    String content;

    Boolean hasAttachment = false;
    String attachmentName;

    @Enumerated(EnumType.STRING)
    EmailEventStatus status;

    Blob attachmentContent;

    LocalDateTime sentOn;

    String receiver;

}
