package com.dateam.retailindustry.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductReqDTO {

    @NotEmpty
    private String productName;

    @NotNull
    private Integer productPrice;

    @NotNull
    private Long productStock;

}