package kz.bi.rest.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.dao.entity.user.Role;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "User information")
@Data
@Accessors(chain = true)
public class User {
    @Schema(description = "User ID", example = "1")
    private long id;
    @Schema(description = "Username", example = "john_doe")
    private String username;
    @Schema(description = "Password", example = "password")
    private String password;
    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Full name", example = "John Doe")
    private String fullName;
    @Schema(description = "Whether the user is enabled", example = "true")
    private boolean enabled;
    @Schema(description = "User role", example = "ROLE_ADMIN")
    private Role role;
}
