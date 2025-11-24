package kz.bi.rest.controller.admin;

import jakarta.validation.Valid;
import kz.bi.rest.controller.dto.user.CreateUserRequest;
import kz.bi.service.UsersService;
import kz.bi.service.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/users")
@RestController
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        usersService.createUser(convertUserCreation(createUserRequest));
    }


    private UserDto convertUserCreation(CreateUserRequest createUserRequest) {
        return new UserDto()
                .setUsername(createUserRequest.getUsername())
                .setEmail(createUserRequest.getEmail())
                .setPassword(createUserRequest.getPassword())
                .setRole(createUserRequest.getRole())
                .setFullName(createUserRequest.getFullName());
    }
}
