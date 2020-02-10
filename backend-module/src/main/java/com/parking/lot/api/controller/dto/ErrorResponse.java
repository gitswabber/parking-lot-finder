package com.parking.lot.api.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    public static ErrorResponse build(HttpStatus status, String message, String path) {
        final ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.status = status.value();
        errorResponse.error = status.getReasonPhrase();
        errorResponse.message = message;
        errorResponse.path = path;

        return errorResponse;
    }

    @Schema(name = "timestamp", example = "2019-12-13 04:32:46")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    @Schema(name = "status", description = "HTTP Status Code", example = "500")
    private int status;

    @Schema(name = "error", description = "ERROR", example = "Internal Server Error")
    private String error;

    @Schema(name = "message", description = "error message", example = "java.net.ConnectException")
    private String message;

    @Schema(name = "path", description = "requested path", example = "/stocks/search")
    private String path;
}
