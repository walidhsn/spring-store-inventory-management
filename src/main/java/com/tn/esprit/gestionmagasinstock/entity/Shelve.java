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
public class Shelve implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shelveId;
    private String shelveCode;
    private String shelveLibel;
    @OneToMany(mappedBy = "shelve",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
