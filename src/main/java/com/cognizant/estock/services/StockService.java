package com.cognizant.estock.services;

import com.cognizant.estock.domain.Stock;
import com.cognizant.estock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Stock add(Stock stock) { return stockRepository.save(stock); }

    public List<Stock> getStocksByDateRangeAndCompanyCode(String companyCode, String startDate, String endDate) {
        Instant instant = Instant.parse(startDate);
        Instant instant1 = Instant.parse(endDate);
        Date from = Date.from(instant);
        Date to = Date.from(instant1);

        return stockRepository.findByCreatedDateBetweenAndCompanyCode(from, to, companyCode.toLowerCase());
    }

    public void deleteStocksByCompanyCode(String companyCode) {
        stockRepository.deleteStocksByCompanyCode(companyCode.toLowerCase());
    }

    public List<Stock> getAllStocksByCompanyCode(String companyCode) {
        return stockRepository.findAllByCompanyCode(companyCode.toLowerCase());
    }

    public Stock getLatestStock(String companyCode) {
        return stockRepository.findFirstByCompanyCodeOrderByCreatedDateDesc(companyCode.toLowerCase());
    }

    public double getMaxPrice(String companyCode, String startDate, String endDate) {
        Instant instant = Instant.parse(startDate);
        Instant instant1 = Instant.parse(endDate);
        Date from = Date.from(instant);
        Date to = Date.from(instant1);

        return stockRepository.findTopByCompanyCodeAndCreatedDateBetweenOrderByPriceDesc(companyCode.toLowerCase(), from, to).getPrice();
    }

    public double getMinPrice(String companyCode, String startDate, String endDate) {
        Instant instant = Instant.parse(startDate);
        Instant instant1 = Instant.parse(endDate);
        Date from = Date.from(instant);
        Date to = Date.from(instant1);

        return stockRepository.findTopByCompanyCodeAndCreatedDateBetweenOrderByPriceAsc(companyCode.toLowerCase(), from, to).getPrice();
    }

    public double getAvgPrice(String companyCode, String startDate, String endDate) {
        Instant instant = Instant.parse(startDate);
        Instant instant1 = Instant.parse(endDate);
        Date from = Date.from(instant);
        Date to = Date.from(instant1);

        return stockRepository.calcAvgPriceByCompanyCodeAndCreatedDateBetween(companyCode, from, to);
    }
}
