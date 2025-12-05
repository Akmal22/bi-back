package kz.bi.rest.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "Login response containing user information after successful authentication")
@Getter
@RequiredArgsConstructor
public class LoginResponse extends SuccessResponse {
    @Schema(description = "Authenticated username", example = "admin")
    private final String username;
    @Schema(description = "User role", example = "ROLE_ADMIN")
    private final String role;
}
