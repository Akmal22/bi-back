package kz.bi.rest.controller.dto.incubator.response;

import kz.bi.rest.controller.dto.SuccessResponse;
import lombok.Getter;

@Getter
public class IncubatorResponse extends SuccessResponse {
    private final Incubator incubator;

    public IncubatorResponse(Incubator incubator) {
        this.incubator = incubator;
    }
}
