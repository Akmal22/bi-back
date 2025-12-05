package kz.bi.rest.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Schema(description = "Request to get paginated list of users")
@Data
public class GetUsersRequest {
    @Schema(description = "Page number (0-indexed)", example = "0", required = true)
    @Min(0)
    private int page;
    @Schema(description = "Page size", example = "10", required = true)
    @Min(1)
    private int size;
    @Schema(description = "Field to sort by", example = "id")
    String sortField;
    @Schema(description = "Sort direction (asc or desc)", example = "asc")
    String sortDirection;
}
