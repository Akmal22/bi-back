package kz.bi.rest.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "Login request containing user credentials")
@Data
public class LoginRequest {
    @Schema(description = "Username for authentication", example = "admin", required = true)
    @NotBlank(message = "Username cannot be empty")
    private String username;
    @Schema(description = "Password for authentication", example = "password123", required = true)
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
