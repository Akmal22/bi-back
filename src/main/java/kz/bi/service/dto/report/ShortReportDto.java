package kz.bi.service.dto.report;

import kz.bi.service.dto.CountryDto;
import kz.bi.service.dto.incubator.IncubatorIncomeDto;
import kz.bi.service.dto.incubator.IncubatorInvestmentDto;
import kz.bi.service.dto.incubator.IncubatorProjectsDto;
import kz.bi.service.dto.incubator.IncubatorResidentsDto;
import kz.bi.service.dto.user.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ShortReportDto {
    private CountryDto country;
    private UserDto manager;
    private List<IncubatorResidentsDto> incubatorResidents;
    private List<IncubatorIncomeDto> incubatorIncome;
    private List<IncubatorInvestmentDto> incubatorInvestment;
    private List<IncubatorProjectsDto> incubatorProjects;
}
