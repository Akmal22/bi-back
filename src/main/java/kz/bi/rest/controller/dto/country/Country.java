package kz.bi.rest.controller.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Country information")
@Data
public class Country {
    @Schema(description = "Country code", example = "KZ")
    private String countryCode;
    @Schema(description = "Country name", example = "Kazakhstan")
    private String countryName;
    @Schema(description = "Currency code", example = "398")
    private Integer currencyCode;
    @Schema(description = "Currency name", example = "Tenge")
    private String currencyName;
}
