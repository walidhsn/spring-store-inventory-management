package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Client;
import com.tn.esprit.gestionmagasinstock.enums.ClientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ClientDao extends JpaRepository<Client,Long> {
    @Query("SELECT SUM(i.invoiceAmount) FROM Client c JOIN c.invoices i WHERE c.clientCategory = :categorie AND i.invoiceDate >= :startDate AND i.invoiceDate <= :endDate")
    Float calculeChifreDaffaireByCategorie(@Param("categorie")ClientCategory category, @Param("startDate")Date startDate,@Param("endDate") Date endDate);
}
