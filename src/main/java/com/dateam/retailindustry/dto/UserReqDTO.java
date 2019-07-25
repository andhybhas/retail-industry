package com.dateam.retailindustry.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserReqDTO {

    @NotNull
    private String userName;

    @NotNull
    private int userBalance;

}
