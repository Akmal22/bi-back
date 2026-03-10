package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Schema(description = "Simple incubator information")
@Data
@Accessors(chain = true)
public class SimpleIncubator {
    @Schema(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000")
    private String incubatorUuid;
    @Schema(description = "Incubator name", example = "TechHub Incubator")
    private String name;
    @Schema(description = "Incubator description", example = "A technology-focused business incubator")
    private String description;
    @Schema(description = "Date when incubator was founded", example = "2020-01-15T10:00:00")
    private LocalDate founded;
}
