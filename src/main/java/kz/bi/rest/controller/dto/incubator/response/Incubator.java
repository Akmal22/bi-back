package kz.bi.rest.controller.dto.incubator.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Incubator {
    private String name;
    private String description;
    private Long managerId;
    private Long countryId;
    private LocalDateTime founded;
    private IncubatorCharacteristics incubatorCharacteristics;
    private IncubatorInfrastructure incubatorInfrastructure;
    private IncubatorSpace incubatorSpace;
    private IncubatorResidents incubatorResidents;
    private IncubatorService incubatorServices;
    private IncubatorIncome incubatorIncome;
    private IncubatorInvestment incubatorInvestment;
    private IncubatorExpense incubatorExpense;
    private List<IncubatorProjects> incubatorProjects;
}
