package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "Incubator income summary")
@Data
@Accessors(chain = true)
public class IncubatorIncomeInfo {
    @Schema(description = "Year", example = "2023")
    private int year;
    @Schema(description = "Total income", example = "500000.00")
    private BigDecimal income;
}
