package kz.bi.rest.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Success response")
@Getter
public class SuccessResponse implements Response {
    @Schema(description = "Response status", example = "SUCCESS")
    private final Status status = Status.SUCCESS;
}
