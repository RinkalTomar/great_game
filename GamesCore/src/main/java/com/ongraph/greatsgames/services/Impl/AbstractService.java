package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.message.MessageProperties;
import com.ongraph.greatsgames.services.IConfigService;
import com.ongraph.greatsgames.services.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {


    @Autowired
    ICoreService _coreService;

    @Autowired
    MessageProperties _message;

    @Autowired
    IConfigService _configService;


}
