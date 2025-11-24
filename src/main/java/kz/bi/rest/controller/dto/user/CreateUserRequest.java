package kz.bi.rest.controller.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.bi.dao.entity.user.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateUserRequest {
    @NotBlank(message = "Username should not be blank")
    private String username;
    @NotBlank(message = "Password should not be blank")
    private String password;
    @NotBlank(message = "Email should not be blank")
    private String email;
    @NotNull(message = "Role should not be blank")
    private Role role;
    private String fullName;
}
