package com.dateam.retailindustry.util;

import lombok.Data;

@Data
public class Response <T> {
    private String message;
    private T data;
}
