package kz.bi.service.dto.incubator;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IncubatorDto {
    private Long id;
    private String name;
    private String description;
    private Long managerId;
    private Long countryId;
    private IncubatorCharacteristicsDto incubatorCharacteristics;
    private IncubatorInfrastructureDto incubatorInfrastructure;
    private IncubatorSpaceDto incubatorSpace;
    private IncubatorResidentsDto incubatorResidents;
    private IncubatorServiceDto incubatorServices;
    private IncubatorIncomeDto incubatorIncome;
    private IncubatorInvestmentDto incubatorInvestment;
    private IncubatorExpenseDto incubatorExpense;
    private List<IncubatorProjectsDto> incubatorProjects;
    private LocalDateTime founded;
}
