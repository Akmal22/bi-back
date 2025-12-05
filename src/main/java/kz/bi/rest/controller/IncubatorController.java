package kz.bi.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.bi.rest.controller.converter.IncubatorConverter;
import kz.bi.rest.controller.converter.IncubatorRequestToDtoConverter;
import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.incubator.request.GetIncubatorsRequest;
import kz.bi.rest.controller.dto.incubator.request.IncubatorRequest;
import kz.bi.rest.controller.dto.incubator.request.UpdateIncubatorRequest;
import kz.bi.rest.controller.dto.incubator.response.CreateUpdateIncubatorResponse;
import kz.bi.rest.controller.dto.incubator.response.GetIncubatorsResponse;
import kz.bi.rest.controller.dto.incubator.response.IncubatorResponse;
import kz.bi.service.IncubatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

import static kz.bi.rest.controller.converter.IncubatorConverter.toIncubator;

@Tag(name = "Incubators", description = "Business incubator management endpoints")
@RequestMapping("/incubators")
@RestController
@RequiredArgsConstructor
@Slf4j
public class IncubatorController {
    private final IncubatorService incubatorService;

    @Operation(summary = "Create a new incubator", description = "Adds a new business incubator to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incubator created successfully",
                    content = @Content(schema = @Schema(implementation = CreateUpdateIncubatorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Response> addIncubator(@Valid @RequestBody IncubatorRequest request) {
        String incubatorUuid = incubatorService.addIncubator(IncubatorRequestToDtoConverter.convertToDto(request));
        return new ResponseEntity<>(new CreateUpdateIncubatorResponse().setIncubatorUuid(incubatorUuid), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing incubator", description = "Updates information for an existing business incubator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incubator updated successfully",
                    content = @Content(schema = @Schema(implementation = CreateUpdateIncubatorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Incubator not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping
    public ResponseEntity<Response> updateIncubator(@Valid @RequestBody UpdateIncubatorRequest request) {
        String incubatorUuid = incubatorService.updateIncubator(IncubatorRequestToDtoConverter.convertToDto(request));
        return new ResponseEntity<>(new CreateUpdateIncubatorResponse().setIncubatorUuid(incubatorUuid), HttpStatus.OK);
    }

    @Operation(summary = "Get incubators with pagination", description = "Retrieves a paginated list of business incubators")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incubators retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GetIncubatorsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("list")
    public ResponseEntity<Response> getIncubators(@Valid @RequestBody GetIncubatorsRequest request) {
        String sortField = Optional.ofNullable(request.getSortField()).orElse("id");
        String sortDirection = Optional.ofNullable(request.getSortDirection()).orElse("asc");

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortField);

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                sort
        );

        var incubators = incubatorService.getIncubators(pageable);

        var responseBody = new GetIncubatorsResponse()
                .setPage(incubators.getPage())
                .setSize(incubators.getSize())
                .setTotalPages(incubators.getTotalPages())
                .setTotalElements(incubators.getTotal())
                .setLast(incubators.isLast())
                .setIncubators(incubators.getIncubators().stream()
                        .map(IncubatorConverter::convertToSimpleIncubator)
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Get incubator by UUID", description = "Retrieves detailed information about a specific incubator by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incubator retrieved successfully",
                    content = @Content(schema = @Schema(implementation = IncubatorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Incubator not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("{incubatorUuid}")
    public ResponseEntity<Response> getIncubator(
            @Parameter(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
            @PathVariable String incubatorUuid) {
        var incubator = incubatorService.getIncubator(incubatorUuid);
        return new ResponseEntity<>(new IncubatorResponse(toIncubator(incubator)), HttpStatus.OK);
    }
}
