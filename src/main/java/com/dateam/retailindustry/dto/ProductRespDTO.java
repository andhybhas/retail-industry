package com.dateam.retailindustry.dto;

import lombok.Data;

@Data
public class ProductRespDTO {

    private Long productId;

    private String productName;

    private Integer productPrice;

    private Long productStock;
}
