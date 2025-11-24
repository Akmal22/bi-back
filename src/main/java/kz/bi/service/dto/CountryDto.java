package kz.bi.service.dto;

import lombok.Data;

@Data
public class CountryDto {
    private String countryCode;
    private String countryName;
    private Integer currencyCode;
    private String currencyName;
}
