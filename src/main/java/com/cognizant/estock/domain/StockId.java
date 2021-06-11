package com.cognizant.estock.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class StockId implements Serializable {

    private String companyCode;
    private double price;

    public StockId(String companyCode, double price) {
        this.companyCode = companyCode;
        this.price = price;
    }
}
