package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.CasinoContacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasinoContactRepository extends JpaRepository<CasinoContacts, Long> {
}
