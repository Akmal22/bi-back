package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Incubator projects summary")
@Data
@Accessors(chain = true)
public class IncubatorProjectsInfo {
    @Schema(description = "Year", example = "2023")
    private int year;
    @Schema(description = "Number of projects", example = "10")
    private int projectsCount;
}
