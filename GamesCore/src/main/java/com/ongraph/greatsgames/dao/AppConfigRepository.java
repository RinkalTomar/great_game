package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.AppConfig;
import com.ongraph.greatsgames.enums.Enumeration.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {


    @Query("SELECT ac.configValue FROM AppConfig ac WHERE configKey = ?1")
    String getConfigValue(ConfigKeys keys) throws Exception;
}
