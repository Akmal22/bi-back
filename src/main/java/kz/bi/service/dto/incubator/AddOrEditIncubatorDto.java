package kz.bi.service.dto.incubator;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AddOrEditIncubatorDto {
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private Long managerId;
    private String countryCode;
    private IncubatorCharacteristicsDto incubatorCharacteristics;
    private IncubatorInfrastructureDto incubatorInfrastructure;
    private IncubatorSpaceDto incubatorSpace;
    private List<IncubatorResidentsDto> incubatorResidents;
    private IncubatorServiceDto incubatorServices;
    private List<IncubatorIncomeDto> incubatorIncome;
    private List<IncubatorInvestmentDto> incubatorInvestment;
    private IncubatorExpenseDto incubatorExpense;
    private List<IncubatorProjectsDto> incubatorProjects;
    private LocalDate founded;
}
