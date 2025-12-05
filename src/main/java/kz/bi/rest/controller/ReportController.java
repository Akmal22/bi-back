package kz.bi.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.bi.rest.controller.converter.report.ShortReportConverter;
import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.report.FullReportResponse;
import kz.bi.rest.controller.dto.report.ShortReportResponse;
import kz.bi.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kz.bi.rest.controller.converter.report.FullReportConverter.convertToResponse;

@Tag(name = "Reports", description = "Report generation endpoints")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("report")
@RestController
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "Get short report", description = "Retrieves a short report for a specific incubator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ShortReportResponse.class))),
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
    @GetMapping("short/{incubatorUuid}")
    public ResponseEntity<Response> shortReport(
            @Parameter(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
            @PathVariable String incubatorUuid) {
        return new ResponseEntity<>(ShortReportConverter.convertToResponse(reportService.getShortReport(incubatorUuid)), HttpStatus.OK);
    }

    @Operation(summary = "Get full report", description = "Retrieves a full report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report retrieved successfully",
                    content = @Content(schema = @Schema(implementation = FullReportResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("full/{incubatorUuid}")
    public ResponseEntity<Response> fullReport(
            @Parameter(description = "Incubator UUID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
            @PathVariable String incubatorUuid) {
        return new ResponseEntity<>(convertToResponse(reportService.getFullReport(incubatorUuid)), HttpStatus.OK);
    }
}
