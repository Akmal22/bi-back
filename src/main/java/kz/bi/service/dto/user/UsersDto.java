package kz.bi.service.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UsersDto {
    private List<UserDto> users;
    private int page;
    private int size;
    private long total;
    private int totalPages;
    private boolean last;
}
