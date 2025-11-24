package kz.bi.rest.controller;

import jakarta.validation.Valid;
import kz.bi.rest.controller.dto.country.Country;
import kz.bi.rest.controller.dto.country.CountryListResponse;
import kz.bi.rest.controller.dto.country.CreateCountryRequest;
import kz.bi.service.CountryService;
import kz.bi.service.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("country")
@RestController
public class CountryController {
    private final CountryService countryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCountry(@Valid @RequestBody CreateCountryRequest request) {
        countryService.addCountry(toCountryDto(request));
    }

    @GetMapping
    public ResponseEntity<CountryListResponse> getCountries() {
        List<CountryDto> countryDtos = countryService.getCountries();
        List<Country> countries = countryDtos.stream()
                .map(this::toCountry)
                .collect(Collectors.toList());
        CountryListResponse response = new CountryListResponse();
        response.setCountries(countries);
        return ResponseEntity.ok(response);
    }

    private CountryDto toCountryDto(CreateCountryRequest request) {
        CountryDto dto = new CountryDto();
        dto.setCountryCode(request.getCountryCode());
        dto.setCountryName(request.getCountryName());
        dto.setCurrencyCode(request.getCurrencyCode());
        dto.setCurrencyName(request.getCurrencyName());
        return dto;
    }

    private Country toCountry(CountryDto dto) {
        Country country = new Country();
        country.setCountryCode(dto.getCountryCode());
        country.setCountryName(dto.getCountryName());
        country.setCurrencyCode(dto.getCurrencyCode());
        country.setCurrencyName(dto.getCurrencyName());
        return country;
    }
}
