package com.cognizant.estock.repository;

import com.cognizant.estock.domain.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, String> {

    List<Stock> findByCreatedDateBetweenAndCompanyCode(Date to, Date from, String companyCode);

    void deleteStocksByCompanyCode(String companyCode);

    List<Stock> findAllByCompanyCode(String companyCode);

    Stock findFirstByCompanyCodeOrderByCreatedDateDesc(String companyCode);

    Stock findTopByCompanyCodeAndCreatedDateBetweenOrderByPriceDesc(String companyCode, Date from, Date to);

    Stock findTopByCompanyCodeAndCreatedDateBetweenOrderByPriceAsc(String companyCode, Date from, Date to);
}
