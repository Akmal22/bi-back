package kz.bi.rest.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.bi.rest.controller.dto.ErrorResponse;
import kz.bi.rest.controller.dto.Response;
import kz.bi.rest.controller.dto.user.CreateUserRequest;
import kz.bi.rest.controller.dto.user.GetUsersRequest;
import kz.bi.rest.controller.dto.user.GetUsersResponse;
import kz.bi.rest.controller.dto.user.User;
import kz.bi.service.UsersService;
import kz.bi.service.dto.user.UserDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Users", description = "User management endpoints (Admin only)")
@RequestMapping("/admin/users")
@RestController
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user in the system (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        usersService.createUser(convertUserCreation(createUserRequest));
    }

    @Operation(summary = "Update an existing user", description = "Updates information for an existing user (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
            usersService.updateUser(convertUserCreation(createUserRequest));
    }

    @Operation(summary = "Get users with pagination", description = "Retrieves a paginated list of users (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = GetUsersResponse.class))),
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
    public ResponseEntity<Response> getUsers(@Valid @RequestBody GetUsersRequest request) {
        String sortField = Optional.ofNullable(request.getSortField()).orElse("id");
        String sortDirection = Optional.ofNullable(request.getSortDirection()).orElse("asc");

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortField);

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                sort
        );

        var users = usersService.getUsers(pageable);

        var responseBody = new GetUsersResponse()
                .setPage(users.getPage())
                .setSize(users.getSize())
                .setTotalPages(users.getTotalPages())
                .setTotalElements(users.getTotal())
                .setLast(users.isLast())
                .setUsers(users.getUsers().stream()
                        .map(this::convertFromUserDto)
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private User convertFromUserDto(UserDto user) {
        return new User()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setFullName(user.getFullName())
                .setEnabled(user.isEnabled());
    }

    private UserDto convertUserCreation(CreateUserRequest createUserRequest) {
        return new UserDto()
                .setUsername(createUserRequest.getUsername())
                .setEmail(createUserRequest.getEmail())
                .setPassword(createUserRequest.getPassword())
                .setRole(createUserRequest.getRole())
                .setFullName(createUserRequest.getFullName())
                .setEnabled(createUserRequest.isEnabled());
    }
}
