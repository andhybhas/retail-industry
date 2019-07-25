package com.dateam.retailindustry.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "transaction_total")
    private int transactionTotal;

    @Column(name = "transaction_date")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Date transactionDate;

    @Column(name = "transaction_qty")
    private int transactionQty;

    @Column(name = "user_balance")
    private int userBalance;
}
