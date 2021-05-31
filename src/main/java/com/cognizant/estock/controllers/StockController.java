package com.cognizant.estock.controllers;

import com.cognizant.estock.models.StatisticsDTO;
import com.cognizant.estock.models.Stock;
import com.cognizant.estock.services.StockService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add/{companycode}")
    public ResponseEntity addStock(@PathVariable("companycode") String companyCode, @RequestBody Stock stock) {
        stock.setCompanyCode(companyCode.toLowerCase());
        Stock savedStock = stockService.add(stock);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1.0/market/stock/" + savedStock.getCompanyCode());

        return new ResponseEntity(headers, HttpStatus.CREATED);

    }

    @GetMapping("/get/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<List<Stock>> getStocksByDateRangeAndCompanyCode(@PathVariable("companycode") String code,
                                                 @PathVariable("startdate") String startDate,
                                                 @PathVariable("enddate") String endDate) {

        List<Stock> response = stockService.getStocksByDateRangeAndCompanyCode(code, startDate, endDate);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/getstats/{companycode}/{startdate}/{enddate}")
    public ResponseEntity<StatisticsDTO> getStats(@PathVariable("companycode") String code,
                                                  @PathVariable("startdate") String startDate,
                                                  @PathVariable("enddate") String endDate) {
        double max = stockService.getMaxPrice(code, startDate, endDate);
        double min = stockService.getMinPrice(code, startDate, endDate);
        double avg = (max + min) / 2.0; // fix this later
        StatisticsDTO statsDTO = new StatisticsDTO(min, max, avg);

        return new ResponseEntity<>(statsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{companycode}")
    public ResponseEntity deleteStocksByCompanyCode(@PathVariable("companycode") String companyCode) {
        stockService.deleteStocksByCompanyCode(companyCode);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get/all/{companycode}")
    public ResponseEntity<List<Stock>> getAllStocks(@PathVariable("companycode") String companyCode) {
        List<Stock> stockList = stockService.getAllStocksByCompanyCode(companyCode);

        return new ResponseEntity(stockList, HttpStatus.OK);
    }

    @GetMapping("/info/latest/{companycode}")
    public ResponseEntity<Stock> getLatestStock(@PathVariable("companycode") String companyCode) {
        Stock latest = stockService.getLatestStock(companyCode);

        return new ResponseEntity<>(latest, HttpStatus.OK);
    }

}
