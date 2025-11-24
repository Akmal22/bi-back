package kz.bi.rest.controller;

import jakarta.validation.Valid;
import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.SuccessResponse;
import kz.bi.rest.controller.dto.auth.LoginRequest;
import kz.bi.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Response> auth(@Valid @RequestBody LoginRequest loginRequest) {
        var authenticated = usersService.authorize(loginRequest.getUsername(), loginRequest.getPassword());
        if (authenticated) {
            return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Invalid username or password", "Invalid username or password"), HttpStatus.UNAUTHORIZED);
        }
    }
}
