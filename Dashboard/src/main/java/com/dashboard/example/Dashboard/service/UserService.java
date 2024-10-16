package com.dashboard.example.Dashboard.service;

import com.dashboard.example.Dashboard.dto.GetUserListDTO;
import com.dashboard.example.Dashboard.dto.UserDTO;
import com.dashboard.example.Dashboard.dto.UserDeleteDTO;
import com.dashboard.example.Dashboard.dto.UserUpdateDTO;
import com.dashboard.example.Dashboard.entity.User;
import com.dashboard.example.Dashboard.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public GetUserListDTO<?> getAllUsers(){
        List<User> users = userRepository.findAll();
        return GetUserListDTO.builder()
                .status(true)
                .data(users)
                .build();
    }

    public UserUpdateDTO<?> updateUser(UserDTO userDTO){
        Optional<User> existingUser = userRepository.findById(userDTO.getId());
        if(existingUser.isPresent()){
            User user = existingUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
//            user.setLastName(userDTO.getLastName());
            userRepository.save(user);
            return UserUpdateDTO.builder()
                    .status(true)
                    .data(null)
                    .message("User Updated Successfully.")
                    .build();
        }else {
            return UserUpdateDTO.builder()
                    .status(false)
                    .data(null)
                    .message("Id not found")
                    .build();
        }
    }

    public UserDeleteDTO <?> deleteUser(UserDTO userDTO){
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isPresent()){
            userRepository.deleteById(userDTO.getId());
            return UserDeleteDTO.builder()
                    .status(true)
                    .data(null)
                    .message("User Successfully Deleted.")
                    .build();
        }else {
            return UserDeleteDTO.builder()
                    .status(false)
                    .data(null)
                    .message("User id not found")
                    .build();
        }
    }

}
