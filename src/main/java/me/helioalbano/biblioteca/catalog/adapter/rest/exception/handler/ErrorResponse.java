package me.helioalbano.biblioteca.catalog.adapter.rest.exception.handler;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, int code, String message) {

}
