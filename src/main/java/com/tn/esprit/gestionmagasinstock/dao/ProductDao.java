package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.stock.qtStock < p.stock.qtMin")
    List<Product> findByStockQuantityLessThanMin();
}
