package com.logindemo.logindemo.dto;

import lombok.Data;

@Data
public class RespondDTO {
    private Integer statusCode;
    private String status;
    private String message;
    private Object data;

    public RespondDTO(Integer statusCode, String status, String message, Object data) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public RespondDTO(Integer statusCode, String status, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }
    public RespondDTO() {
    }
}
