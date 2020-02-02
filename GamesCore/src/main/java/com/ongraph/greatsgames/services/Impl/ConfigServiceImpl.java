package com.ongraph.greatsgames.services.Impl;

import com.ongraph.greatsgames.dao.AppConfigRepository;
import com.ongraph.greatsgames.services.IConfigService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ongraph.greatsgames.enums.Enumeration.*;

@Transactional(readOnly = true)
@Service
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    AppConfigRepository _appCofigRepository;

    @Override
    public String getValue(ConfigKeys Key) throws Exception
    {
        return _appCofigRepository.getConfigValue(Key);
    }
}
