package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Response containing incubator UUID after create or update")
@Data
@Accessors(chain = true)
public class CreateUpdateIncubatorResponse extends SuccessResponse {
    @Schema(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000")
    private String incubatorUuid;
}
