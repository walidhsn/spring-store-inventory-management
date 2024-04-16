package com.tn.esprit.gestionmagasinstock.serviceImpl;

import com.tn.esprit.gestionmagasinstock.dao.*;
import com.tn.esprit.gestionmagasinstock.entity.*;
import com.tn.esprit.gestionmagasinstock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final ProductDetailDao productDetailDao;
    private final ShelveDao shelveDao;
    private final StockDao stockDao;
    private final SupplierDao supplierDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao,ShelveDao shelveDao,StockDao stockDao,ProductDetailDao productDetailDao,SupplierDao supplierDao) {
        this.productDao = productDao;
        this.shelveDao = shelveDao;
        this.stockDao = stockDao;
        this.productDetailDao = productDetailDao;
        this.supplierDao = supplierDao;
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product addProduct(Product p, Long shelveId, Long stockId) {
        Optional<Shelve> optionalShelve = shelveDao.findById(shelveId);
        Optional<Stock> optionalStock = stockDao.findById(stockId);
        if(optionalShelve.isPresent() && optionalStock.isPresent()){
            Shelve shelve = optionalShelve.get();
            Stock stock = optionalStock.get();
            p.setShelve(shelve);
            p.setStock(stock);
            Product result = productDao.save(p);
            return result;
        }
        return null;
    }

    @Override
    public Product addProductDto(Product product, ProductDetail productDetail, Long shelveId, Long stockId) {
        product.setProductDetail(productDetail);
        Product result = addProduct(product,shelveId,stockId);
        return result;
    }
    @Override
    public Product retrieveProduct(Long id) {
        Optional<Product> optionalProduct = productDao.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> optionalProduct = productDao.findById(id);
        Product result = optionalProduct.orElse(null);
        if(result!=null){
            productDao.deleteById(id);
        }
        return result;
    }

    @Override
    public void assignProduitToStock(Long idProduit, Long idStock) {
        Optional<Stock> optionalStock = stockDao.findById(idStock);
        Optional<Product> optionalProduct = productDao.findById(idProduit);
        if(optionalProduct.isPresent() && optionalStock.isPresent()){
            Product product = optionalProduct.get();
            Stock oldStock = product.getStock() != null ? product.getStock() : null;
            Stock stock = optionalStock.get();
            if(oldStock != null){
                oldStock.getProducts().remove(product);
                stockDao.save(oldStock);
            }
            product.setStock(stock);
            productDao.save(product);
        }
        else {
            System.out.println("Error finding the product or the stock");
        }
    }

    @Override
    public void assignFournisseurToProduit(Long fournisseurId, Long produitId) {
        Optional<Supplier> optionalSupplier = supplierDao.findById(fournisseurId);
        Optional<Product> optionalProduct = productDao.findById(produitId);
        if(optionalProduct.isPresent() && optionalSupplier.isPresent()){
            Product product = optionalProduct.get();
            product.getSuppliers().add(optionalSupplier.get());
            productDao.save(product);
        }
    }

    @Override
    public float getRevenuBrutProduit(Long idProduit, Date startDate, Date endDate) {
        Optional<Product> optionalProduct = productDao.findById(idProduit);
        float totalRev = 0;
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            List<InvoiceDetail> invoiceDetailList = product.getInvoiceDetails();
            for(InvoiceDetail detail : invoiceDetailList){
                if(withinDateRange(detail.getInvoice().getInvoiceDate(), startDate,endDate)){
                    totalRev+=detail.getQuantity()*product.getUnitPrice();
                }
            }
        }else System.out.println("Error finding the product !");
        return totalRev;
    }
    @Scheduled(cron = "0 0 22 * * *")
    @Override
    public String retrieveStatusStock() {
        List<Product> productsWithLowStock = productDao.findByStockQuantityLessThanMin();
        for (Product product : productsWithLowStock) {
            System.out.println("Product: " + product.getProductLibel() + " has low stock.");
        }
        return productsWithLowStock.toString();
    }

    public Boolean withinDateRange(Date date,Date startDate,Date endDate){
        return !date.before(startDate) && !date.after(endDate);
    }
}
