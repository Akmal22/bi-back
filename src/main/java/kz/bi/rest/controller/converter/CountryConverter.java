package kz.bi.rest.controller.converter;

import kz.bi.rest.controller.dto.country.Country;
import kz.bi.rest.controller.dto.country.CreateCountryRequest;
import kz.bi.rest.controller.dto.country.UpdateCountryRequest;
import kz.bi.service.dto.CountryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryConverter {
    public static CountryDto toCountryDto(CreateCountryRequest request) {
        CountryDto dto = new CountryDto();
        dto.setCountryCode(request.getCountryCode());
        dto.setCountryName(request.getCountryName());
        dto.setCurrencyCode(request.getCurrencyCode());
        dto.setCurrencyName(request.getCurrencyName());
        return dto;
    }

    public static CountryDto toCountryDto(UpdateCountryRequest request) {
        CountryDto dto = new CountryDto();
        dto.setCountryCode(request.getCountryCode());
        dto.setCountryName(request.getCountryName());
        dto.setCurrencyCode(request.getCurrencyCode());
        dto.setCurrencyName(request.getCurrencyName());
        return dto;
    }

    public static Country toCountry(CountryDto dto) {
        Country country = new Country();
        country.setCountryCode(dto.getCountryCode());
        country.setCountryName(dto.getCountryName());
        country.setCurrencyCode(dto.getCurrencyCode());
        country.setCurrencyName(dto.getCurrencyName());
        return country;
    }
}
