package kz.bi.rest.controller.dto.incubator.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "Manager information")
@Data
@Accessors(chain = true)
public class ManagerInfo {
    @Schema(description = "Manager ID", example = "1")
    private long id;
    @Schema(description = "Username", example = "manager1")
    private String username;
    @Schema(description = "Email address", example = "manager@example.com")
    private String email;
    @Schema(description = "Full name", example = "John Doe")
    private String fullName;
}
