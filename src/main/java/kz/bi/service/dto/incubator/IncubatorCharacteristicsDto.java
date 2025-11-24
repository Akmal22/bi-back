package kz.bi.service.dto.incubator;

import kz.bi.dao.entity.incubator.AverageEmployeePerResident;
import kz.bi.dao.entity.incubator.ShareAmount;
import lombok.Data;

@Data
public class IncubatorCharacteristicsDto {
    private long id;
    private Long incubatorId;
    private AverageEmployeePerResident averageEmployeePerResident;
    private ShareAmount shareAmount;
    private Integer totalStaff;
    private Integer expertsAndConsultants;
    private Integer managers;
    private boolean monitoringAndDataCollecting;
    private String requirements;
}
