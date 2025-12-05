package kz.bi.rest.controller.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Schema(description = "Incubator fund summary")
@Data
@Accessors(chain = true)
public class IncubatorFundInfo {
    @Schema(description = "Year", example = "2023")
    private int year;
    @Schema(description = "Fund amount", example = "1000000.00")
    private BigDecimal amount;
}
