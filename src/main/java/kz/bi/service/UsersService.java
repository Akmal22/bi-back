package kz.bi.service;

import kz.bi.config.security.UserAuthentication;
import kz.bi.dao.entity.user.UserEntity;
import kz.bi.dao.repo.UsersRepository;
import kz.bi.service.dto.UserDto;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        if (usersRepository.existsByUsername(userDto.getUsername())) {
            log.error("Username already exists");
            throw new ValidationException("user.already.exists", "User already exists");
        }

        var newUser = new UserEntity();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setEmail(userDto.getEmail());
        newUser.setFullName(userDto.getFullName());
        usersRepository.save(newUser);

        log.info("User with username {} created", userDto.getUsername());
    }

    public boolean authorize(String username, String password) {
        var optionalUser = usersRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.error("User with username {} not found", username);
            return false;
        }

        if (!passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            log.error("Passwords do not match");
            return false;
        }

        var user = optionalUser.get();
        var authentication = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("User {} authenticated", username);

        return true;
    }
}
