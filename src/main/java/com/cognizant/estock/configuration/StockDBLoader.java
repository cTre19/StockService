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

        stockService.add(stock1);
        stockService.add(stock2);
        stockService.add(stock3);
        stockService.add(stock4);
        System.out.println("Avg price: " + stockService.getAvgPrice("ctsh",
                "2021-06-04T04:24:26.463Z", "2021-07-04T04:24:26.463Z"));
    }

}
