package com.tn.esprit.gestionmagasinstock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private  String productCode;
    private String productLibel;
    private Float unitPrice;
    @ManyToOne
    @JsonIgnore
    private Shelve shelve;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Supplier> suppliers = new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Stock stock;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_detail_id", referencedColumnName = "productDetailId")
    private ProductDetail productDetail;
    @OneToMany(mappedBy = "product",cascade = CascadeType.PERSIST)
    private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
}
