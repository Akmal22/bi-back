package kz.bi.rest.controller.dto;

import lombok.Getter;

@Getter
public class ErrorResponse implements Response {
    private final Status status = Status.FAIL;
    private final String message;
    private final String description;

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
