package com.campuslands.grocery_management.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Integer statusCode;
    private String error;
    private String message;
}
