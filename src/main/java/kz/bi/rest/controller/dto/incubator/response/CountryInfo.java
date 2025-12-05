package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Country information")
@Data
@Accessors(chain = true)
public class CountryInfo {
    @Schema(description = "Country code", example = "KZ")
    private String countryCode;
    @Schema(description = "Country name", example = "Kazakhstan")
    private String countryName;
}
