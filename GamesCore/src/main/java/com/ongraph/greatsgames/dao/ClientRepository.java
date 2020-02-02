package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
