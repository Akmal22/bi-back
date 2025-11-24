package kz.bi.rest.controller.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCountryRequest {
    @NotBlank(message = "countryName should not be blank")
    private String countryName;
    @NotNull(message = "countryCode should not be null")
    private String countryCode;
    @NotBlank(message = "currencyName should not be blank")
    private String currencyName;
    @NotNull(message = "currencyCode should not be null")
    private Integer currencyCode;
}
