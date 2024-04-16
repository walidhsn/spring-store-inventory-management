package com.tn.esprit.gestionmagasinstock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;
    private Integer qtStock;
    private Integer qtMin;
    private String stockLibel;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "stock")
    private List<Product> products = new ArrayList<>();
}
