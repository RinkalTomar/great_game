package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
