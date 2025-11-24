package kz.bi.service;

import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.repo.CountryRepository;
import kz.bi.service.dto.CountryDto;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public void addCountry(CountryDto country) {
        if (countryRepository.existsByCountryName(country.getCountryName())) {
            log.error("Country with name {} already exists", country.getCountryName());
            throw new ValidationException("country.already.exists", "Country already exists");
        }

        countryRepository.save(toEntity(country));
    }

    public List<CountryDto> getCountries() {
        return countryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CountryEntity toEntity(CountryDto dto) {
        CountryEntity entity = new CountryEntity();
        entity.setCountryCode(dto.getCountryCode());
        entity.setCountryName(dto.getCountryName());
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());
        return entity;
    }

    public CountryDto toDto(CountryEntity entity) {
        CountryDto dto = new CountryDto();
        dto.setCountryCode(entity.getCountryCode());
        dto.setCountryName(entity.getCountryName());
        dto.setCurrencyCode(entity.getCurrencyCode());
        dto.setCurrencyName(entity.getCurrencyName());
        return dto;
    }
}
