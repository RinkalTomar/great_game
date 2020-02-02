package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.EmailEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEventRepository extends JpaRepository<EmailEvent, Long> {

}
