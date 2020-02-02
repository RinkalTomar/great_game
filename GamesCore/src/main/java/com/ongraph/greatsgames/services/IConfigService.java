package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.enums.Enumeration.*;

public interface IConfigService {

    String getValue(ConfigKeys Key) throws Exception;
}
