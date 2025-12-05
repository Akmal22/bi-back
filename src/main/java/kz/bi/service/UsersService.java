package kz.bi.service;

import kz.bi.dao.entity.user.UserEntity;
import kz.bi.dao.repo.UsersRepository;
import kz.bi.service.converter.UserConverter;
import kz.bi.service.dto.user.UserDto;
import kz.bi.service.dto.user.UsersDto;
import kz.bi.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class UsersService implements UserDetailsService {
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
        newUser.setEnabled(userDto.isEnabled());
        usersRepository.save(newUser);

        log.info("User with username {} created", userDto.getUsername());
    }

    public void updateUser(UserDto userDto) {
        var optionalUSer = usersRepository.findByUsername(userDto.getUsername());
        if (optionalUSer.isEmpty()) {
            log.error("User with username {} does not exist", userDto.getUsername());
            throw new ValidationException("user.not.exists", "User does not exists");
        }

        var userEntity = optionalUSer.get();

        userEntity.setFullName(userDto.getFullName());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEnabled(userDto.isEnabled());
        userEntity.setRole(userDto.getRole());
        usersRepository.save(userEntity);


        log.info("User with username {} saved", userDto.getUsername());
    }

    public UsersDto getUsers(Pageable pageable) {
        Page<UserEntity> users = usersRepository.findAll(pageable);
        return new UsersDto()
                .setUsers(users.getContent().stream()
                        .map(UserConverter::convertFromUserEntity)
                        .collect(Collectors.toList()))
                .setPage(users.getNumber())
                .setSize(users.getSize())
                .setTotal(users.getTotalElements())
                .setTotalPages(users.getTotalPages())
                .setLast(users.isLast());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = usersRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return optionalUser.get();
    }
}
