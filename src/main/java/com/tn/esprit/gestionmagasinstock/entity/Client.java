package com.tn.esprit.gestionmagasinstock.entity;

import com.tn.esprit.gestionmagasinstock.enums.ClientCategory;
import com.tn.esprit.gestionmagasinstock.enums.Occupation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String name;
    private String lastName;
    private Date birthdate;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientCategory clientCategory;
    @Enumerated(EnumType.STRING)
    private Occupation occupation;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();
}
