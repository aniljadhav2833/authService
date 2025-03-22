package com.aniljadhav2833.dosti.auth_service.controller;

import com.aniljadhav2833.dosti.auth_service.DTO.LoginUser;
import com.aniljadhav2833.dosti.auth_service.DTO.RegisterUser;
import com.aniljadhav2833.dosti.auth_service.DTO.Response;
import com.aniljadhav2833.dosti.auth_service.DTO.UserDTO;
import com.aniljadhav2833.dosti.auth_service.Exceptions.CustomException;
import com.aniljadhav2833.dosti.auth_service.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    @PostMapping("register")
    public Response createNewUser(@RequestBody RegisterUser registerUser){
       return userService.createUser(registerUser);
    }

    @GetMapping("users")
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("users/{name}")
    public UserDTO getUserByUsername(@PathVariable String name) throws CustomException {
        return userService.getUser(name);
    }

    @PostMapping("checkLogin")
    public boolean createNewUser(@RequestBody LoginUser loginUser){
        return userService.checkCredentials(loginUser);
    }


}
