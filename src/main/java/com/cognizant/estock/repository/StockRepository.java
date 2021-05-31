package com.cognizant.estock.repository;

import com.cognizant.estock.models.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {

    List<Stock> findByDateBetweenAndCompanyCode(Date to, Date from, String companyCode);

    void deleteStocksByCompanyCode(String companyCode);

    List<Stock> findAllByCompanyCode(String companyCode);

    Stock findFirstByCompanyCodeOrderByDateDesc(String companyCode);

    Stock findTopByCompanyCodeAndDateBetweenOrderByPriceDesc(String companyCode, Date from, Date to);

    Stock findTopByCompanyCodeAndDateBetweenOrderByPriceAsc(String companyCode, Date from, Date to);
}
