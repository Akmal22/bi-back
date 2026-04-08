package kz.bi.rest.controller.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Request to change the current user password")
@Data
public class ChangePasswordRequest {
    @Schema(description = "Current password of the user", required = true)
    @NotBlank(message = "Current password cannot be empty")
    private String currentPassword;

    @Schema(description = "New password to set", required = true)
    @NotBlank(message = "New password cannot be empty")
    @Size(min = 8, message = "New password must be at least 8 characters")
    private String newPassword;
}
