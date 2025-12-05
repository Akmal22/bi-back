package kz.bi.rest.controller.dto.incubator.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Incubator characteristics information")
@Data
public class IncubatorCharacteristics {
    @Schema(description = "Average number of employees per resident", example = "MORE_THAN_ONE_PER_ONE_RESIDENT", required = true, allowableValues = " MORE_THAN_ONE_PER_ONE_RESIDENT,ONE_PER_ONE_RESIDENT, LESS_THAN_ONE_PER_ONE_RESIDENT")
    @NotNull(message = "Average employee per resident is required")
    private String averageEmployeePerResident;

    @Schema(description = "Share amount", example = "MORE_THAN_15_PERCENT", required = true, allowableValues = "MONETARY_COST_BUT_NO_SHARES, BETWEEN_1_AND_5_PERCENT BETWEEN_1_AND_10_PERCENT, BETWEEN_1_AND_20_PERCENT, BETWEEN_10_AND_20_PERCENT, MORE_THAN_15_PERCENT, FIXED")
    @NotNull(message = "Share amount is required")
    private String shareAmount;

    @Schema(description = "Total staff count", example = "20", required = true)
    @NotNull(message = "Total staff is required")
    @Min(value = 0, message = "Total staff must be non-negative")
    private Integer totalStaff;

    @Schema(description = "Number of experts and consultants", example = "5", required = true)
    @NotNull(message = "Experts and consultants is required")
    @Min(value = 0, message = "Experts and consultants must be non-negative")
    private Integer expertsAndConsultants;

    @Schema(description = "Number of managers", example = "3", required = true)
    @NotNull(message = "Managers is required")
    @Min(value = 0, message = "Managers must be non-negative")
    private Integer managers;

    @Schema(description = "Whether monitoring and data collecting is enabled", example = "true")
    private Boolean monitoringAndDataCollecting;

    @Schema(description = "Requirements", example = "Must have minimum 2 years of operation", maxLength = 1024)
    @Size(max = 1024, message = "Requirements must not exceed 1024 characters")
    private String requirements;
}
