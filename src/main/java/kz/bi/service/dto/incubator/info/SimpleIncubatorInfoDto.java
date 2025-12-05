package kz.bi.service.dto.incubator.info;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SimpleIncubatorInfoDto {
    private String incubatorUuid;
    private String name;
    private String description;
    private LocalDateTime founded;
}
