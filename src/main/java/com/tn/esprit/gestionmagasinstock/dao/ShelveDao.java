package com.tn.esprit.gestionmagasinstock.dao;

import com.tn.esprit.gestionmagasinstock.entity.Shelve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelveDao extends JpaRepository<Shelve,Long> {
}
