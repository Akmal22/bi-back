package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Incubator space information")
@Data
public class IncubatorSpace {
    @Schema(description = "Overall space in square meters", example = "1000")
    private Integer overallSpace;
    @Schema(description = "Average resident space in square meters", example = "50")
    private Integer avgResidentSpace;
    @Schema(description = "Communal space in square meters", example = "200")
    private Integer communalSpace;
    @Schema(description = "Admin space in square meters", example = "100")
    private Integer adminSpace;
    @Schema(description = "Communal space ratio percentage", example = "20")
    private Integer communalSpaceRatio;
}
