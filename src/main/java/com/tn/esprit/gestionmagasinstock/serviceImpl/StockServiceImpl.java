package com.tn.esprit.gestionmagasinstock.serviceImpl;

import com.tn.esprit.gestionmagasinstock.dao.StockDao;
import com.tn.esprit.gestionmagasinstock.entity.Stock;
import com.tn.esprit.gestionmagasinstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockDao stockDao;
    @Autowired
    public StockServiceImpl(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public List<Stock> retrieveAllStocks() {
        return stockDao.findAll();
    }

    @Override
    public Stock addStock(Stock s) {
        return stockDao.save(s);
    }

    @Override
    public Stock updateStock(Stock u) {
        return stockDao.save(u);
    }

    @Override
    public Stock retrieveStock(Long id) {
        Optional<Stock> optionalStock = stockDao.findById(id);
        return optionalStock.orElse(null);
    }
}
