package com.tn.esprit.gestionmagasinstock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private Float discountAmount;
    private Float invoiceAmount;
    private Date invoiceDate;
    private Boolean active;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
}
