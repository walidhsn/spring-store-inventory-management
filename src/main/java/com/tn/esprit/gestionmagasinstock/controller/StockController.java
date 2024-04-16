package com.tn.esprit.gestionmagasinstock.controller;

import com.tn.esprit.gestionmagasinstock.entity.Stock;
import com.tn.esprit.gestionmagasinstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {
    private final StockService stockService;
    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStocks(){
        List<Stock> list = stockService.retrieveAllStocks();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/stock/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable("id") Long id){
        Stock stock = stockService.retrieveStock(id);
        return stock!=null ? ResponseEntity.ok(stock) : ResponseEntity.notFound().build();
    }
    @PostMapping("/stock")
    public ResponseEntity<Stock> CreateStock(@RequestBody Stock Stock){
        Stock result = stockService.addStock(Stock);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
    @PutMapping("/stock")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock Stock){
        Stock result = stockService.updateStock(Stock);
        return result!=null ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
}
