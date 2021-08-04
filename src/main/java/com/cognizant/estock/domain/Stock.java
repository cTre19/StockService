package com.cognizant.estock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@IdClass(StockId.class)
public class Stock implements Serializable {

    private static final long serialVersionUID = -3997944153009689649L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long stockId = 0L;

//    @Id
    @Column(nullable = false, unique = false)
    private String companyCode;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdDate;

//    @Id
    @Column(nullable = false)
    private double price;

    public Stock(String companyCode, double price) {
        this.companyCode = companyCode.toLowerCase();
        this.price = price;
    }
}
