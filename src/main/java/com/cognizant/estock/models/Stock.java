package com.cognizant.estock.models;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@ToString
@Document
public class Stock {

    @NotNull
    private String companyCode;
    private Date date;
    @NotNull
    private double price;

    public Stock(String companyCode, double price) {
        this.companyCode = companyCode.toLowerCase();
        this.price = price;
        this.date = new Date();
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode.toLowerCase();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
