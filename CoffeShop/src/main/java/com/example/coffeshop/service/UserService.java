package com.example.coffeshop.service;

import com.example.coffeshop.model.dto.LoginUserDto;
import com.example.coffeshop.model.dto.RegisterUserDto;
import com.example.coffeshop.model.dto.UsersDto;
import com.example.coffeshop.model.entity.UserEntity;
import com.example.coffeshop.repository.UserRepository;
import com.example.coffeshop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register(RegisterUserDto registerUserDto) {

        UserEntity user = modelMapper
                .map(registerUserDto, UserEntity.class);
        userRepository.save(user);
    }

    public void loginUser(Long id, String username) {

        currentUser.setId(id);
        currentUser.setUsername(username);
    }
    public void logout(){
        currentUser.logout();
    }

    public UserEntity findByUsernameAndPassword(String username, String password) {

       return userRepository
                       .findByUsernameAndPassword(username,password);

    }

    public UserEntity findById(Long id) {

      return userRepository.findById(id)
              .orElse(null);
    }

    public List<UsersDto> getAllUsers() {

        return userRepository
                .findByOrdersDesc()
                .stream()
                .map(u->{
                    UsersDto usersDto = new UsersDto();
                    usersDto.setUsername(u.getUsername());
                    usersDto.setCountOrders(u.getOrders().size());
                    return usersDto;
                }).collect(Collectors.toList());
    }
}
