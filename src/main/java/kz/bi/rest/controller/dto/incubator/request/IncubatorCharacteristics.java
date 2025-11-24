package kz.bi.rest.controller.dto.incubator.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IncubatorCharacteristics {
    @NotNull(message = "Average employee per resident is required")
    private String averageEmployeePerResident;

    @NotNull(message = "Share amount is required")
    private String shareAmount;

    @NotNull(message = "Total staff is required")
    @Min(value = 0, message = "Total staff must be non-negative")
    private Integer totalStaff;

    @NotNull(message = "Experts and consultants is required")
    @Min(value = 0, message = "Experts and consultants must be non-negative")
    private Integer expertsAndConsultants;

    @NotNull(message = "Managers is required")
    @Min(value = 0, message = "Managers must be non-negative")
    private Integer managers;

    private Boolean monitoringAndDataCollecting;

    @Size(max = 1024, message = "Requirements must not exceed 1024 characters")
    private String requirements;
}
