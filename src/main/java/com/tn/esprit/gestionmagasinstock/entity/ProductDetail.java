package com.tn.esprit.gestionmagasinstock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tn.esprit.gestionmagasinstock.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;
    private Date creationDate;
    private Date lastModificationDate;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @OneToOne(mappedBy = "productDetail", cascade = CascadeType.ALL)
    @JsonIgnore
    private Product product;
}
