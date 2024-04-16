package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvoiceDao extends JpaRepository<Invoice,Long> {
    @Query("SELECT i FROM Invoice i WHERE i.client.clientId = :clientId")
    List<Invoice> findInvoicesByClientId(@Param("clientId") Long clientId);
}
