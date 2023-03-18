package com.george.cfopapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String message;

}
