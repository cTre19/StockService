package com.cognizant.estock.controllers;

import com.cognizant.estock.domain.Stock;
import com.cognizant.estock.models.StatisticsDTO;
import com.cognizant.estock.services.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1.0/market/stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add/{companycode}")
    public ResponseEntity addStock(@PathVariable("companycode") String companyCode, @RequestBody Stock stock) {
        stock.setCompanyCode(companyCode.toLowerCase());
        Stock savedStock = null;
        try {
            log.info("Saving stock: " + companyCode);
            savedStock = stockService.add(stock);
        } catch (Exception e) {
            log.error("Error saving stock: " + companyCode);
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1.0/market/stock/" + savedStock.getCompanyCode());
        log.info("Stock successfully saved");

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping("/get/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<List<Stock>> getStocksByDateRangeAndCompanyCode(@PathVariable("companycode") String code,
                                                 @PathVariable("startdate") String startDate,
                                                 @PathVariable("enddate") String endDate) {

        log.info("Fetching all stocks for " + code + " from " + startDate + " to" + endDate);
        List<Stock> response = stockService.getStocksByDateRangeAndCompanyCode(code, startDate, endDate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getstats/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<StatisticsDTO> getStats(@PathVariable("companycode") String code,
                                                  @PathVariable("startdate") String startDate,
                                                  @PathVariable("enddate") String endDate) {
        log.info("Fetching stats for company " + code + " from " + " to " + endDate);
        double max = stockService.getMaxPrice(code, startDate, endDate);
        log.info("Max Price: " + max);
        double min = stockService.getMinPrice(code, startDate, endDate);
        log.info("Min Price: " + min);
        double avg = stockService.getAvgPrice(code, startDate, endDate);
        log.info("Avg Price: " + avg);
        StatisticsDTO statsDTO = new StatisticsDTO(min, max, avg);

        return new ResponseEntity<>(statsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{companycode}")
    public ResponseEntity deleteStocksByCompanyCode(@PathVariable("companycode") String companyCode) {
        log.info("Deleting all stocks for : " + companyCode);
        stockService.deleteStocksByCompanyCode(companyCode);
        log.info("Stocks deleted");

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/all/{companycode}")
    public ResponseEntity<List<Stock>> getAllStocks(@PathVariable("companycode") String companyCode) {
        log.info("Fetching all stocks for: " + companyCode);
        List<Stock> stockList = stockService.getAllStocksByCompanyCode(companyCode);

        return new ResponseEntity(stockList, HttpStatus.OK);
    }

    @GetMapping("/info/latest/{companycode}")
    public ResponseEntity<Stock> getLatestStock(@PathVariable("companycode") String companyCode) {
        log.info("Fetching latest stock for " + companyCode);
        Stock latest = stockService.getLatestStock(companyCode);
        if(latest == null) {
            log.error("Error retrieving latest stock");
            return new ResponseEntity<>(latest, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(latest, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Iterable<Stock>> getAll() {
        log.info("Fetching all stocks");
        Iterable<Stock> allStocks = this.stockService.getAll();

        return new ResponseEntity<>(allStocks, HttpStatus.OK);
    }

}
