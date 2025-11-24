package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncubatorService {
    @NotNull(message = "Offered services is required")
    @Min(value = 0, message = "Offered services must be non-negative")
    private Integer offeredServices;

    @NotNull(message = "Free services is required")
    @Min(value = 0, message = "Free services must be non-negative")
    private Integer freeServices;

    @NotNull(message = "Paid services is required")
    @Min(value = 0, message = "Paid services must be non-negative")
    private Integer paidServices;

    @NotNull(message = "Used services is required")
    @Min(value = 0, message = "Used services must be non-negative")
    private Integer usedServices;

    @NotNull(message = "Offered facilities is required")
    @Min(value = 0, message = "Offered facilities must be non-negative")
    private Integer offeredFacilities;

    @NotNull(message = "Free facilities is required")
    @Min(value = 0, message = "Free facilities must be non-negative")
    private Integer freeFacilities;

    @NotNull(message = "Paid facilities is required")
    @Min(value = 0, message = "Paid facilities must be non-negative")
    private Integer paidFacilities;

    @NotNull(message = "Used facilities is required")
    @Min(value = 0, message = "Used facilities must be non-negative")
    private Integer usedFacilities;

    @NotNull(message = "Offered trainings is required")
    @Min(value = 0, message = "Offered trainings must be non-negative")
    private Integer offeredTrainings;

    @NotNull(message = "Free trainings is required")
    @Min(value = 0, message = "Free trainings must be non-negative")
    private Integer freeTrainings;

    @NotNull(message = "Paid trainings is required")
    @Min(value = 0, message = "Paid trainings must be non-negative")
    private Integer paidTrainings;

    @NotNull(message = "Used trainings is required")
    @Min(value = 0, message = "Used trainings must be non-negative")
    private Integer usedTrainings;
}
