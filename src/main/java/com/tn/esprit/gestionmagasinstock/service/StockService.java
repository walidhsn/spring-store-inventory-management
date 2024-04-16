package com.tn.esprit.gestionmagasinstock.service;

import com.tn.esprit.gestionmagasinstock.entity.Stock;

import java.util.List;

public interface StockService {
    List<Stock> retrieveAllStocks();
    Stock addStock(Stock s);
    Stock updateStock(Stock u);
    Stock retrieveStock(Long id);
}
