package kz.bi.rest.controller.dto.country;

import lombok.Data;

@Data
public class Country {
    private String countryCode;
    private String countryName;
    private Integer currencyCode;
    private String currencyName;
}
