package kz.bi.rest.controller.dto.country;

import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryListResponse extends SuccessResponse {
    private List<Country> countries;
}
