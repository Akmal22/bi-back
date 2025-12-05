package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Incubator applications summary")
@Data
@Accessors(chain = true)
public class IncubatorApplicationsInfo {
    @Schema(description = "Year", example = "2023")
    private int year;
    @Schema(description = "Number of applications", example = "50")
    private int applicationCount;
}
