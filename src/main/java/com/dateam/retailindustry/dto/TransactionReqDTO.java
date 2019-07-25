package com.dateam.retailindustry.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TransactionReqDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotNull
    private int transactionQty;

    @DateTimeFormat
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Date transactionDate;
}
