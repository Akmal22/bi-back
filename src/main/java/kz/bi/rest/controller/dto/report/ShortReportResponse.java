package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import kz.bi.rest.controller.dto.country.Country;
import kz.bi.rest.controller.dto.incubator.response.ManagerInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Schema(description = "Short report response containing key metrics")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShortReportResponse extends SuccessResponse {
    @Schema(description = "Incubator country info")
    private Country country;
    @Schema(description = "Incubator manager info")
    private ManagerInfo managerInfo;
    @Schema(description = "Set of incubator projects")
    private List<IncubatorProjectsInfo> incubatorProjectInfos;
    @Schema(description = "Set of incubator income data")
    private List<IncubatorIncomeInfo> incubatorIncomeInfo;
    @Schema(description = "List of incubator fund data")
    private List<IncubatorFundInfo> incubatorFundInfo;
    @Schema(description = "List of application data")
    private List<IncubatorApplicationsInfo> applications;
}
