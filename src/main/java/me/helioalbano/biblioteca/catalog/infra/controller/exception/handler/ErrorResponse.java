package me.helioalbano.biblioteca.catalog.infra.controller.exception.handler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int code;
    private String message;
}
