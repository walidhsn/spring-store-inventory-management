package com.tn.esprit.gestionmagasinstock.dto;

import com.tn.esprit.gestionmagasinstock.entity.Product;
import com.tn.esprit.gestionmagasinstock.entity.ProductDetail;
import lombok.Data;

@Data
public class CreateProductDto {
    private Product product;
    private ProductDetail productDetail;
}
