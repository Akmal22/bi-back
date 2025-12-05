package kz.bi.service.converter;

import kz.bi.dao.entity.CountryEntity;
import kz.bi.service.dto.CountryDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryConverter {
    public static CountryEntity toEntity(CountryDto dto) {
        CountryEntity entity = new CountryEntity();
        entity.setCountryCode(dto.getCountryCode());
        entity.setCountryName(dto.getCountryName());
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());
        return entity;
    }

    public static CountryDto toDto(CountryEntity entity) {
        CountryDto dto = new CountryDto();
        dto.setCountryCode(entity.getCountryCode());
        dto.setCountryName(entity.getCountryName());
        dto.setCurrencyCode(entity.getCurrencyCode());
        dto.setCurrencyName(entity.getCurrencyName());
        return dto;
    }
}
