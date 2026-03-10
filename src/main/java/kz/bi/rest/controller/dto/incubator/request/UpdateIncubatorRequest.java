package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Schema(description = "Request to update an existing incubator")
@Data
public class UpdateIncubatorRequest {

    @Schema(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    @NotBlank(message = "UUID is required")
    private String uuid;

    @Schema(description = "Incubator description", example = "Updated description", required = true)
    @NotBlank(message = "Description is required")
    @Size(max = 256, message = "Description must not exceed 256 characters")
    private String description;

    @Schema(description = "Date when incubator was founded", example = "2020-01-15T10:00:00", required = true)
    @NotNull(message = "Founded date is required")
    private LocalDate founded;

    @Schema(description = "Incubator characteristics")
    @Valid
    private IncubatorCharacteristics incubatorCharacteristics;

    @Schema(description = "Incubator infrastructure")
    @Valid
    private IncubatorInfrastructure incubatorInfrastructure;

    @Schema(description = "Incubator space information")
    @Valid
    private IncubatorSpace incubatorSpace;

    @Schema(description = "List of incubator residents data")
    @Valid
    private Set<IncubatorResidents> incubatorResidents;

    @Schema(description = "Incubator services information")
    @Valid
    private IncubatorService incubatorServices;

    @Schema(description = "List of incubator income data")
    @Valid
    private Set<IncubatorIncome> incubatorIncome;

    @Schema(description = "List of incubator investment data")
    @Valid
    private Set<IncubatorInvestment> incubatorInvestment;

    @Schema(description = "Incubator expense information")
    @Valid
    private IncubatorExpense incubatorExpense;

    @Schema(description = "List of incubator projects")
    @Valid
    private Set<IncubatorProjects> incubatorProjects;
}
