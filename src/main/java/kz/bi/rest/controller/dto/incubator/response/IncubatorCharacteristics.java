package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Incubator characteristics information")
@Data
public class IncubatorCharacteristics {
    @Schema(description = "Average number of employees per resident", example = "MORE_THAN_ONE_PER_ONE_RESIDENT", required = true, allowableValues = " MORE_THAN_ONE_PER_ONE_RESIDENT,ONE_PER_ONE_RESIDENT, LESS_THAN_ONE_PER_ONE_RESIDENT")
    private String averageEmployeePerResident;
    @Schema(description = "Share amount", example = "MORE_THAN_15_PERCENT", required = true, allowableValues = "MONETARY_COST_BUT_NO_SHARES, BETWEEN_1_AND_5_PERCENT BETWEEN_1_AND_10_PERCENT, BETWEEN_1_AND_20_PERCENT, BETWEEN_10_AND_20_PERCENT, MORE_THAN_15_PERCENT, FIXED")
    private String shareAmount;
    @Schema(description = "Total staff count", example = "20")
    private Integer totalStaff;
    @Schema(description = "Number of experts and consultants", example = "5")
    private Integer expertsAndConsultants;
    @Schema(description = "Number of managers", example = "3")
    private Integer managers;
    @Schema(description = "Whether monitoring and data collecting is enabled", example = "true")
    private Boolean monitoringAndDataCollecting;
    @Schema(description = "Requirements", example = "Must have minimum 2 years of operation")
    private String requirements;
}
