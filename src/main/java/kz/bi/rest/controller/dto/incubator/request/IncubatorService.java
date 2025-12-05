package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Incubator services information")
@Data
public class IncubatorService {
    @Schema(description = "Number of offered services", example = "10", required = true)
    @NotNull(message = "Offered services is required")
    @Min(value = 0, message = "Offered services must be non-negative")
    private Integer offeredServices;

    @Schema(description = "Number of free services", example = "5", required = true)
    @NotNull(message = "Free services is required")
    @Min(value = 0, message = "Free services must be non-negative")
    private Integer freeServices;

    @Schema(description = "Number of paid services", example = "5", required = true)
    @NotNull(message = "Paid services is required")
    @Min(value = 0, message = "Paid services must be non-negative")
    private Integer paidServices;

    @Schema(description = "Number of used services", example = "8", required = true)
    @NotNull(message = "Used services is required")
    @Min(value = 0, message = "Used services must be non-negative")
    private Integer usedServices;

    @Schema(description = "Number of offered facilities", example = "8", required = true)
    @NotNull(message = "Offered facilities is required")
    @Min(value = 0, message = "Offered facilities must be non-negative")
    private Integer offeredFacilities;

    @Schema(description = "Number of free facilities", example = "4", required = true)
    @NotNull(message = "Free facilities is required")
    @Min(value = 0, message = "Free facilities must be non-negative")
    private Integer freeFacilities;

    @Schema(description = "Number of paid facilities", example = "4", required = true)
    @NotNull(message = "Paid facilities is required")
    @Min(value = 0, message = "Paid facilities must be non-negative")
    private Integer paidFacilities;

    @Schema(description = "Number of used facilities", example = "6", required = true)
    @NotNull(message = "Used facilities is required")
    @Min(value = 0, message = "Used facilities must be non-negative")
    private Integer usedFacilities;

    @Schema(description = "Number of offered trainings", example = "12", required = true)
    @NotNull(message = "Offered trainings is required")
    @Min(value = 0, message = "Offered trainings must be non-negative")
    private Integer offeredTrainings;

    @Schema(description = "Number of free trainings", example = "6", required = true)
    @NotNull(message = "Free trainings is required")
    @Min(value = 0, message = "Free trainings must be non-negative")
    private Integer freeTrainings;

    @Schema(description = "Number of paid trainings", example = "6", required = true)
    @NotNull(message = "Paid trainings is required")
    @Min(value = 0, message = "Paid trainings must be non-negative")
    private Integer paidTrainings;

    @Schema(description = "Number of used trainings", example = "10", required = true)
    @NotNull(message = "Used trainings is required")
    @Min(value = 0, message = "Used trainings must be non-negative")
    private Integer usedTrainings;
}
