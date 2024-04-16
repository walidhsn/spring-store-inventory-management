package com.tn.esprit.gestionmagasinstock.controller;

import com.tn.esprit.gestionmagasinstock.entity.Product;
import com.tn.esprit.gestionmagasinstock.service.ProductService;
import com.tn.esprit.gestionmagasinstock.dto.CreateProductDto;
import com.tn.esprit.gestionmagasinstock.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> list = productService.retrieveAllProducts();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.retrieveProduct(id);
        return product!=null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
    /*@PostMapping("/product")
    @Transactional
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductDto createProductDto, @RequestParam Long shelveId, @RequestParam Long stockId) {
        Product product = createProductDto.getProduct();
        ProductDetail productDetail = createProductDto.getProductDetail();
        Product result = productService.addProductDto(product, productDetail, shelveId, stockId);
        return result != null ? ResponseEntity.ok().body(result) : ResponseEntity.badRequest().build();
    }
    @PostMapping("/product/changeStock")
    public ResponseEntity<String> changeProductStock(@RequestParam Long stockId, @RequestParam Long productId) {
        productService.assignProduitToStock(productId,stockId);
        return ResponseEntity.ok().body("true");
    }
    @PostMapping("/product")
    public ResponseEntity<String> addSupplierToProduct(@RequestParam Long productId, @RequestParam Long supplierId) {
        productService.assignFournisseurToProduit(supplierId,productId);
        return ResponseEntity.ok().body("true");
    }
    @DeleteMapping("/product")
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId){
        Product result = productService.deleteProduct(productId);
        return result!= null ? ResponseEntity.ok("Product has been Deleted.") : ResponseEntity.notFound().build();
    }*/

}
