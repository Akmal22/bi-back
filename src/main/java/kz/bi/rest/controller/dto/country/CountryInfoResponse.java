package kz.bi.rest.controller.dto.country;

import io.swagger.v3.oas.annotations.media.Schema;
import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "Response containing country information")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CountryInfoResponse extends SuccessResponse {
    @Schema(description = "Country details")
    private Country country;
}
