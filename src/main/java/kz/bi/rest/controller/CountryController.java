package kz.bi.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.bi.rest.controller.converter.CountryConverter;
import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.country.*;
import kz.bi.service.CountryService;
import kz.bi.service.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static kz.bi.rest.controller.converter.CountryConverter.toCountry;
import static kz.bi.rest.controller.converter.CountryConverter.toCountryDto;

@Tag(name = "Countries", description = "Country management endpoints")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("countries")
@RestController
public class CountryController {
    private final CountryService countryService;

    @Operation(summary = "Create a new country", description = "Adds a new country to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Country created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCountry(@Valid @RequestBody CreateCountryRequest request) {
        countryService.addCountry(toCountryDto(request));
    }

    @Operation(summary = "Update exiting country", description = "Edit existing country in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Country updated"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updateCountry(@Valid @RequestBody UpdateCountryRequest request) {
        countryService.updateCountry(toCountryDto(request));
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CountryListResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<CountryListResponse> getCountries() {
        List<CountryDto> countryDtos = countryService.getCountries();
        List<Country> countries = countryDtos.stream()
                .map(CountryConverter::toCountry)
                .collect(Collectors.toList());
        CountryListResponse response = new CountryListResponse();
        response.setCountries(countries);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get country by code", description = "Retrieves detailed information about a specific country by its code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CountryInfoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("{countryCode}")
    public ResponseEntity<Response> getCountry(
            @Parameter(description = "Country code", example = "KZ", required = true)
            @PathVariable String countryCode) {
        return new ResponseEntity<>(new CountryInfoResponse(toCountry(countryService.getCountry(countryCode))), HttpStatus.OK);
    }
}
