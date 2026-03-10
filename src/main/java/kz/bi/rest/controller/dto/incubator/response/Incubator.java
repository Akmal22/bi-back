package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Detailed incubator information")
@Data
public class Incubator {
    @Schema(description = "Incubator name", example = "TechHub Incubator")
    private String name;
    @Schema(description = "Incubator description", example = "A technology-focused business incubator")
    private String description;
    @Schema(description = "Manager information")
    private ManagerInfo manager;
    @Schema(description = "Country information")
    private CountryInfo country;
    @Schema(description = "Date when incubator was founded", example = "2020-01-15T10:00:00")
    private LocalDate founded;
    @Schema(description = "Incubator characteristics")
    private IncubatorCharacteristics incubatorCharacteristics;
    @Schema(description = "Incubator infrastructure")
    private IncubatorInfrastructure incubatorInfrastructure;
    @Schema(description = "Incubator space information")
    private IncubatorSpace incubatorSpace;
    @Schema(description = "List of incubator residents data")
    private List<IncubatorResidents> incubatorResidents;
    @Schema(description = "Incubator services information")
    private IncubatorService incubatorServices;
    @Schema(description = "List of incubator income data")
    private List<IncubatorIncome> incubatorIncome;
    @Schema(description = "List of incubator investment data")
    private List<IncubatorInvestment> incubatorInvestment;
    @Schema(description = "Incubator expense information")
    private IncubatorExpense incubatorExpense;
    @Schema(description = "List of incubator projects")
    private List<IncubatorProjects> incubatorProjects;
}
