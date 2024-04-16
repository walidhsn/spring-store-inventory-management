package com.tn.esprit.gestionmagasinstock.service;

import com.tn.esprit.gestionmagasinstock.entity.Product;
import com.tn.esprit.gestionmagasinstock.entity.ProductDetail;

import java.util.Date;
import java.util.List;

public interface ProductService {
    List<Product> retrieveAllProducts();
    Product addProduct(Product p, Long shelveId, Long stockId);
    Product addProductDto(Product product, ProductDetail productDetail, Long shelveId, Long stockId);
    Product retrieveProduct(Long id);

    Product deleteProduct(Long id);
    void assignProduitToStock(Long idProduit, Long idStock);
    public void assignFournisseurToProduit(Long fournisseurId, Long produitId) ;
    float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate);
    String retrieveStatusStock();
}
