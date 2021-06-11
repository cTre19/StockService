package com.cognizant.estock.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(StockId.class)
public class Stock {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @NotNull
////    @Column(updatable = false)
//    private Long stockId = 0L;

    @Id
    @NotNull
    @Column(unique = false)
    private String companyCode;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @Id
    @NotNull
    private double price;

    public Stock(String companyCode, double price) {
        this.companyCode = companyCode.toLowerCase();
        this.price = price;
//        this.stockId++;
    }
}
