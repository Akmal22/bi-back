package kz.bi.rest.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Error response")
@Getter
public class ErrorResponse implements Response {
    @Schema(description = "Response status", example = "FAIL")
    private final Status status = Status.FAIL;
    @Schema(description = "Error message", example = "Validation failed")
    private final String message;
    @Schema(description = "Error description", example = "Invalid input data")
    private final String description;

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
