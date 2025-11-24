package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

@Data
public class IncubatorCharacteristics {
    private String averageEmployeePerResident;
    private String shareAmount;
    private Integer totalStaff;
    private Integer expertsAndConsultants;
    private Integer managers;
    private Boolean monitoringAndDataCollecting;
    private String requirements;
}
