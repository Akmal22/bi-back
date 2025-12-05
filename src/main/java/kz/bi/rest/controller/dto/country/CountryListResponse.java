package kz.bi.rest.controller.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "Response containing a list of countries")
@Data
@EqualsAndHashCode(callSuper = true)
public class CountryListResponse extends SuccessResponse {
    @Schema(description = "List of countries")
    private List<Country> countries;
}
