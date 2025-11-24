package kz.bi.rest.controller;

import jakarta.validation.Valid;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.SuccessResponse;
import kz.bi.rest.controller.dto.incubator.request.IncubatorRequest;
import kz.bi.rest.controller.dto.incubator.response.IncubatorResponse;
import kz.bi.service.IncubatorService;
import kz.bi.service.util.RequestToDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.bi.rest.controller.util.IncubatorDtoConverter.toIncubator;

@RequestMapping("/incubators")
@RestController
@RequiredArgsConstructor
@Slf4j
public class IncubatorController {
    private final IncubatorService incubatorService;

    @PostMapping
    public ResponseEntity<Response> addIncubator(@Valid @RequestBody IncubatorRequest request) {
        incubatorService.addIncubator(RequestToDtoConverter.convertToDto(request));
        return new ResponseEntity<>(new SuccessResponse(), HttpStatus.CREATED);
    }

    @GetMapping("{incubatorUuid}")
    public ResponseEntity<Response> getIncubator(@PathVariable String incubatorUuid) {
        var incubator = incubatorService.findByUuid(incubatorUuid);
        return new ResponseEntity<>(new IncubatorResponse(toIncubator(incubator)), HttpStatus.OK);
    }
}
