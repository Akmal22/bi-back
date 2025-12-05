package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "Incubator projects data for a specific year")
@Data
public class IncubatorProjects {
    @Schema(description = "Year", example = "2023")
    private Integer year;
    @Schema(description = "Number of projects", example = "10")
    private Integer projectsCount;
    @Schema(description = "Fund amount", example = "500000.00")
    private BigDecimal fund;
}
