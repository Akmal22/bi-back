package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IncubatorRequest {
    
    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name must not exceed 64 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 256, message = "Description must not exceed 256 characters")
    private String description;

    @NotNull(message = "Manager ID is required")
    private Long managerId;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotNull(message = "Founded date is required")
    private LocalDateTime founded;

    @NotNull(message = "Incubator characteristics is required")
    @Valid
    private IncubatorCharacteristics incubatorCharacteristics;

    @NotNull(message = "Incubator infrastructure is required")
    @Valid
    private IncubatorInfrastructure incubatorInfrastructure;

    @NotNull(message = "Incubator space is required")
    @Valid
    private IncubatorSpace incubatorSpace;

    @NotNull(message = "Incubator residents is required")
    @Valid
    private IncubatorResidents incubatorResidents;

    @NotNull(message = "Incubator services is required")
    @Valid
    private IncubatorService incubatorServices;

    @NotNull(message = "Incubator income is required")
    @Valid
    private IncubatorIncome incubatorIncome;

    @NotNull(message = "Incubator investment is required")
    @Valid
    private IncubatorInvestment incubatorInvestment;

    @NotNull(message = "Incubator expense is required")
    @Valid
    private IncubatorExpense incubatorExpense;

    @NotNull(message = "Incubator projects is required")
    @Valid
    private List<IncubatorProjects> incubatorProjects;
}
