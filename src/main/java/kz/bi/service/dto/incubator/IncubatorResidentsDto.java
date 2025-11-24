package kz.bi.service.dto.incubator;

import lombok.Data;

@Data
public class IncubatorResidentsDto {
    private Long id;
    private Long incubatorId;
    private long incubatedCompanies;
    private long failedCompanies;
    private long graduatedCompanies;
    private long receivedApplication;
    private long acceptedApplication;
    private long activeAfter3Months;
    private long activeAfter6Months;
    private long activeAfter1Year;
    private long activeAfter3Years;
    private long activeAfter5Years;
    private long failedAfter3Months;
    private long failedAfter6Months;
    private long failedAfter1Year;
    private long failedAfter3Years;
    private long failedAfter5Years;
}
