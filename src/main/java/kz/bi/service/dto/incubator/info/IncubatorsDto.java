package kz.bi.service.dto.incubator.info;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class IncubatorsDto {
    private List<SimpleIncubatorInfoDto> incubators;
    private int page;
    private int size;
    private long total;
    private int totalPages;
    private boolean last;
}
