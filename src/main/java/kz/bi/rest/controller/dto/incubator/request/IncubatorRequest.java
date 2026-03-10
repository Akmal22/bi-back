package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Request to create a new incubator")
@Data
public class IncubatorRequest {

    @Schema(description = "Incubator name", example = "TechHub Incubator", required = true)
    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name must not exceed 64 characters")
    private String name;

    @Schema(description = "Incubator description", example = "A technology-focused business incubator", required = true)
    @NotBlank(message = "Description is required")
    @Size(max = 256, message = "Description must not exceed 256 characters")
    private String description;

    @Schema(description = "Manager ID", example = "1")
    private Long managerId;

    @Schema(description = "Country code", example = "KZ", required = true)
    @NotNull(message = "Country code is required")
    private String countryCode;

    @Schema(description = "Date when incubator was founded", example = "2020-01-15T10:00:00", required = true)
    @NotNull(message = "Founded date is required")
    private LocalDate founded;

    @Schema(description = "Incubator characteristics", required = true)
    @NotNull(message = "Incubator characteristics is required")
    @Valid
    private IncubatorCharacteristics incubatorCharacteristics;

    @Schema(description = "Incubator infrastructure", required = true)
    @NotNull(message = "Incubator infrastructure is required")
    @Valid
    private IncubatorInfrastructure incubatorInfrastructure;

    @Schema(description = "Incubator space information", required = true)
    @NotNull(message = "Incubator space is required")
    @Valid
    private IncubatorSpace incubatorSpace;

    @Schema(description = "List of incubator residents data", required = true)
    @NotNull(message = "Incubator residents is required")
    @Valid
    private List<IncubatorResidents> incubatorResidents;

    @Schema(description = "Incubator services information", required = true)
    @NotNull(message = "Incubator services is required")
    @Valid
    private IncubatorService incubatorServices;

    @Schema(description = "List of incubator income data", required = true)
    @NotNull(message = "Incubator income is required")
    @Valid
    private List<IncubatorIncome> incubatorIncome;

    @Schema(description = "List of incubator investment data", required = true)
    @NotNull(message = "Incubator investment is required")
    @Valid
    private List<IncubatorInvestment> incubatorInvestment;

    @Schema(description = "Incubator expense information", required = true)
    @NotNull(message = "Incubator expense is required")
    @Valid
    private IncubatorExpense incubatorExpense;

    @Schema(description = "List of incubator projects", required = true)
    @NotNull(message = "Incubator projects is required")
    @Valid
    private List<IncubatorProjects> incubatorProjects;
}
