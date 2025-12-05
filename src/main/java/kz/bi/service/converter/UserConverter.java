package kz.bi.service.converter;

import kz.bi.dao.entity.user.UserEntity;
import kz.bi.service.dto.user.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {
    public static UserDto convertFromUserEntity(UserEntity user) {
        return new UserDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setFullName(user.getFullName())
                .setPassword(user.getPassword())
                .setEnabled(user.isEnabled());
    }
}
