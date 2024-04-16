package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock,Long> {
}
