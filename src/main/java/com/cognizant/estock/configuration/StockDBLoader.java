package com.cognizant.estock.configuration;

import com.cognizant.estock.domain.Stock;
import com.cognizant.estock.services.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class StockDBLoader implements CommandLineRunner {

    private final StockService stockService;

    public StockDBLoader(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStocks();
    }

    private void loadStocks() throws ParseException {
        System.out.println("Populating stock database");
        Stock stock1 = new Stock("CTSH", 55.5);
        Stock stock2 = new Stock("AMZ", 100.12);
        Stock stock3 = new Stock("CTSH", 65.65);
        Stock stock4 = new Stock("AMZ", 201.10);
//        Stock stock1 = Stock.builder()
//                .companyCode("CTSH")
//                .price(55.00)
//                .build();
//        Stock stock2 = Stock.builder()
//                .companyCode("AMZ")
//                .price(100.10)
//                .build();
//        Stock stock3 = Stock.builder()
//                .companyCode("CTSH")
//                .price(65.77)
//                .build();
//        Stock stock4 = Stock.builder()
//                .companyCode("AMZ")
//                .price(210.69)
//                .build();

        stockService.add(stock1);
        stockService.add(stock2);
        stockService.add(stock3);
        stockService.add(stock4);
    }

}
