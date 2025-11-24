package kz.bi.rest.controller.dto;

import lombok.Getter;

@Getter
public class SuccessResponse implements Response {
    private final Status status = Status.SUCCESS;
}
