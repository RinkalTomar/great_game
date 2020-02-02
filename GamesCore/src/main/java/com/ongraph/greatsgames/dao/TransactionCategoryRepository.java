package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.TransactionCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategories, Long> {
}
