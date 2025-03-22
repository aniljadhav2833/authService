package com.aniljadhav2833.dosti.auth_service.service;

import com.aniljadhav2833.dosti.auth_service.DTO.LoginUser;
import com.aniljadhav2833.dosti.auth_service.Exceptions.CustomException;
import com.aniljadhav2833.dosti.auth_service.DTO.RegisterUser;
import com.aniljadhav2833.dosti.auth_service.DTO.Response;
import com.aniljadhav2833.dosti.auth_service.DTO.UserDTO;
import com.aniljadhav2833.dosti.auth_service.Entitiy.User;
import com.aniljadhav2833.dosti.auth_service.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final String domain= "@dosti.com";

    public Response createUser(RegisterUser registerUser){
        User user = new User();
        String newUserName = createNewUsername(registerUser.getFirstName().trim(),registerUser.getLastName().trim());
        user.setUsername(newUserName);
        user.setPassword(registerUser.getPassword());
        User savedUser = userRepo.save(user);
        return  new Response("Created", user.getUsername()+" is created ", LocalDateTime.now());
    }

    public List<UserDTO> getAllUsers(){
        return userRepo.findAll().stream().
                map(s-> new UserDTO(s.getUsername(),s.isAccountActive(),s.isCurrentlyLogin())).collect(Collectors.toList());
    }
    public UserDTO getUser(String username) throws CustomException {
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isEmpty()){
            throw new CustomException("No user found with username:  " + username );
        }else{
            return convertUserToDTO(user.get());
        }
    }

    public boolean checkCredentials(LoginUser loginUser){
        Optional<User> user   = userRepo.findByUsername(loginUser.getUsername());
        return user.map(value -> value.getPassword().equals(loginUser.getPassword())).orElse(false);
    }

    private boolean isUserExist(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        return user.isPresent();
    }

    private String createNewUsername(String firstName, String lastName){
        if(!isUserExist(firstName.trim()+lastName.trim()+domain)){
            return firstName.trim()+lastName.trim()+domain;
        }else{
            Random random = new Random();
            return createNewUsername(firstName, lastName+random.nextInt(1,9999999));
        }
    }

    private UserDTO convertUserToDTO(User user){
        return new UserDTO(user.getUsername(), user.isAccountActive(), user.isAccountActive());
    }

}
