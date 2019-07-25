package com.dateam.retailindustry.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TransactionRespDTO {

    private Long transactionId;

    private Long userId;

    private String userName;

    private Long productId;

    private String productName;

    private int transactionQty;

    @DateTimeFormat
    private Date transactionDate;

    private Long productStock;

    private int totalTransaction;

    private int userBalance;
}
