package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.UserDto;
import com.learn.GeminiProject.models.User;
import com.learn.GeminiProject.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServicesImp implements UserServices {
    private final UserRepo userRepo;
    //private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserDto> createUser(User user) {
       UserDto userDto = modelMapper.map(userRepo.save(user), UserDto.class);
       return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(User user) {
        UserDto userDto = modelMapper.map(userRepo.save(user), UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User is not existed"));
        userRepo.delete(user);
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
