package com.dateam.retailindustry.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    final int status;
    final String message;
    final String[] errors;
}
