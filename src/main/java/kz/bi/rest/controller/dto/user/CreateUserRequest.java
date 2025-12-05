package kz.bi.rest.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.bi.dao.entity.user.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "Request to create or update a user")
@RequiredArgsConstructor
@Getter
public class CreateUserRequest {
    @Schema(description = "Username", example = "john_doe", required = true)
    @NotBlank(message = "Username should not be blank")
    private String username;
    @Schema(description = "Password", example = "SecurePassword123!", required = true)
    @NotBlank(message = "Password should not be blank")
    private String password;
    @Schema(description = "Email address", example = "john.doe@example.com", required = true)
    @NotBlank(message = "Email should not be blank")
    private String email;
    @Schema(description = "User role", example = "ROLE_ADMIN", required = true)
    @NotNull(message = "Role should not be blank")
    private Role role;
    @Schema(description = "Full name", example = "John Doe")
    private String fullName;
    @Schema(description = "Whether the user is enabled", example = "true")
    private boolean enabled;
}
