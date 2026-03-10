package kz.bi.service.dto.incubator.info;

import kz.bi.service.dto.CountryDto;
import kz.bi.service.dto.incubator.*;
import kz.bi.service.dto.user.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class IncubatorInfoDto {
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private UserDto manager;
    private CountryDto country;
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
