package com.tn.esprit.gestionmagasinstock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceDetailId;
    private Integer quantity;
    private Float totalPrice;
    private Integer discountPercentage;
    private Integer discountAmount;
    @ManyToOne
    @JsonIgnore
    private Invoice invoice;
    @ManyToOne
    @JsonIgnore
    private Product product;
}
