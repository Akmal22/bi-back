package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Getter;

@Schema(description = "Response containing detailed incubator information")
@Getter
public class IncubatorResponse extends SuccessResponse {
    @Schema(description = "Incubator details")
    private final Incubator incubator;

    public IncubatorResponse(Incubator incubator) {
        this.incubator = incubator;
    }
}
