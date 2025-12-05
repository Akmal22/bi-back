package kz.bi.rest.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response status")
public enum Status {
    @Schema(description = "Success status")
    SUCCESS,
    @Schema(description = "Failure status")
    FAIL
}
