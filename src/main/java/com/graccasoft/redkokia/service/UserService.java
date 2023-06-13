package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.UserAlreadyExistsException;
import com.graccasoft.redkokia.model.dto.UserDto;
import com.graccasoft.redkokia.model.entity.User;
import com.graccasoft.redkokia.model.mapper.UserMapper;
import com.graccasoft.redkokia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserDto userDto){
        User checkUser = userRepository.findByUsername(userDto.username()).orElse(null);
        if( checkUser != null ){
            throw new UserAlreadyExistsException("User with provided name already exists");
        }

        User user = userMapper.toEntity(userDto);
        user.setPassword( passwordEncoder.encode(userDto.password() ) );
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setRole("TENANT");

        userRepository.save(user);
    }
}
