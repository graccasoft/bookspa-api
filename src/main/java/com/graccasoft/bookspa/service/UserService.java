package com.graccasoft.bookspa.service;

import com.graccasoft.bookspa.model.dto.JwtDto;
import com.graccasoft.bookspa.model.dto.RegisterUserDto;
import com.graccasoft.bookspa.model.entity.User;
import com.graccasoft.bookspa.model.mapper.TenantMapper;
import com.graccasoft.bookspa.model.mapper.UserMapper;
import com.graccasoft.bookspa.exception.RecordNotFoundException;
import com.graccasoft.bookspa.exception.UserAlreadyExistsException;
import com.graccasoft.bookspa.repository.UserRepository;
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

    public void editUser(RegisterUserDto updateUserRequest){
        User dbUser = userRepository.findById(updateUserRequest.id())
                .orElseThrow(()-> new RecordNotFoundException("User with id not found"));

        if(!updateUserRequest.password().isBlank()) {
            dbUser.setPassword(passwordEncoder.encode(updateUserRequest.password()));
        }
        dbUser.setFirstName(updateUserRequest.firstName());
        dbUser.setLastName(updateUserRequest.lastName());
        dbUser.setRole(updateUserRequest.role());

        userRepository.save(dbUser);
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RecordNotFoundException("User not found"));

        user.setIsDeleted(true);
        user.setEnabled (false);
        userRepository.save(user);
    }

    public List<RegisterUserDto> getTenantUsers(Long tenantId){
        return userMapper.toDtoList( userRepository.findAllByTenant_IdAndIsDeleted(tenantId, false) );
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
