package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.exception.UserAlreadyExistsException;
import com.graccasoft.redkokia.model.dto.JwtDto;
import com.graccasoft.redkokia.model.dto.RegisterUserDto;
import com.graccasoft.redkokia.model.entity.User;
import com.graccasoft.redkokia.model.mapper.TenantMapper;
import com.graccasoft.redkokia.model.mapper.UserMapper;
import com.graccasoft.redkokia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final TenantMapper tenantMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(RegisterUserDto registerUserDto){
        User checkUser = userRepository.findByUsername(registerUserDto.username()).orElse(null);
        if( checkUser != null ){
            throw new UserAlreadyExistsException("User with provided name already exists");
        }

        User user = userMapper.toEntity(registerUserDto);
        user.setPassword( passwordEncoder.encode(registerUserDto.password() ) );
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);

        userRepository.save(user);
    }

    public List<RegisterUserDto> getTenantUsers(Long tenantId){
        return userMapper.toDtoList( userRepository.findAllByTenant_Id(tenantId) );
    }

    public JwtDto getUserJwt(String username, String token){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(("User with name not found")));

        return JwtDto.builder()
                .id(user.getId())
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .tenant(tenantMapper.toDto(user.getTenant()))
                .build();
    }
}
