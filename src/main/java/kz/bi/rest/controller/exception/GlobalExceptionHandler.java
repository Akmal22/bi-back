package kz.bi.rest.controller.exception;

import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.service.exception.IncubatorNotAvailableException;
import kz.bi.service.exception.IncubatorNotFoundException;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorResponse(messageSource.getMessage("internal.error", null, Locale.getDefault()), "Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleException(ValidationException e) {
        return new ResponseEntity<>(new ErrorResponse(messageSource.getMessage(e.getErrorCode(), null, Locale.getDefault()), e.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncubatorNotFoundException.class)
    public ResponseEntity<Response> handleException(IncubatorNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(messageSource.getMessage("incubator.not.found", null, Locale.getDefault()), "Incubator not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncubatorNotAvailableException.class)
    public ResponseEntity<Response> handleException(IncubatorNotAvailableException e) {
        return new ResponseEntity<>(new ErrorResponse(messageSource.getMessage("incubator.not.available", null, Locale.getDefault()), "User does not has access to incubator"), HttpStatus.FORBIDDEN);
    }
}
