package kz.bi.rest.controller.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "Request to create a new country")
@Data
public class CreateCountryRequest {
    @Schema(description = "Name of the country", example = "Kazakhstan", required = true)
    @NotBlank(message = "countryName should not be blank")
    private String countryName;
    @Schema(description = "Country code", example = "KZ", required = true)
    @NotNull(message = "countryCode should not be null")
    private String countryCode;
    @Schema(description = "Name of the currency", example = "Tenge", required = true)
    @NotBlank(message = "currencyName should not be blank")
    private String currencyName;
    @Schema(description = "Currency code", example = "398", required = true)
    @NotNull(message = "currencyCode should not be null")
    private Integer currencyCode;
}
