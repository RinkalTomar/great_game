package com.ongraph.greatsgames.dao;


import com.ongraph.greatsgames.entities.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractsRepository extends JpaRepository<Contracts,Long> {
}
