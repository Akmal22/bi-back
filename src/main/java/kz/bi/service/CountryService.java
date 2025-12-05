package kz.bi.service;

import kz.bi.dao.entity.CountryEntity;
import kz.bi.dao.repo.CountryRepository;
import kz.bi.service.converter.CountryConverter;
import kz.bi.service.dto.CountryDto;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.bi.service.converter.CountryConverter.toDto;
import static kz.bi.service.converter.CountryConverter.toEntity;

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

    public void updateCountry(CountryDto countryDto) {
        var optionalCountry = countryRepository.findByCountryCode(countryDto.getCountryCode());
        if (optionalCountry.isEmpty()) {
            log.error("Country with code {} not found", countryDto.getCountryCode());
            throw new ValidationException("country.not.exist", "Country not found");
        }

        var country = optionalCountry.get();

        if (!country.getCountryName().equals(countryDto.getCountryName()) && countryRepository.existsByCountryName(countryDto.getCountryName())) {
            log.error("Country with name {} already exists", countryDto.getCountryName());
            throw new ValidationException("country.already.exists", "Country already exists");
        }

        country.setCountryName(countryDto.getCountryName());
        country.setCurrencyCode(countryDto.getCurrencyCode());
        country.setCurrencyName(countryDto.getCurrencyName());

        countryRepository.save(country);
    }

    public List<CountryDto> getCountries() {
        return countryRepository.findAll().stream()
                .map(CountryConverter::toDto)
                .collect(Collectors.toList());
    }

    public CountryDto getCountry(String countryCode) {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountryCode(countryCode);

        if (countryEntity.isEmpty()) {
            log.error("Country with code {} not found", countryCode);
            throw new ValidationException("country.not.exist", "Country not found");
        }

        return toDto(countryEntity.get());
    }
}
