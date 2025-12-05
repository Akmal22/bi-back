package kz.bi.rest.controller.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(description = "Response containing paginated list of users")
@Data
@Accessors(chain = true)
public class GetUsersResponse extends SuccessResponse {
    @Schema(description = "List of users")
    private List<User> users;
    @Schema(description = "Current page number", example = "0")
    private int page;
    @Schema(description = "Page size", example = "10")
    private int size;
    @Schema(description = "Total number of elements", example = "100")
    private long totalElements;
    @Schema(description = "Total number of pages", example = "10")
    private int totalPages;
    @Schema(description = "Whether this is the last page", example = "false")
    private boolean last;
}
