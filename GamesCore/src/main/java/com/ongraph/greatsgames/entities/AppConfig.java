package com.ongraph.greatsgames.entities;


import com.ongraph.greatsgames.enums.Enumeration.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class AppConfig extends BaseEntity{


    @Enumerated(EnumType.STRING)
    ConfigKeys configKey;
    String configValue;

}
