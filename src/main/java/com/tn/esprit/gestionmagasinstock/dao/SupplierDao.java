package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier,Long> {
}
