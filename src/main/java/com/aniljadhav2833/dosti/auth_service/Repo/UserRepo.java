package com.aniljadhav2833.dosti.auth_service.Repo;

import com.aniljadhav2833.dosti.auth_service.DTO.UserDTO;
import com.aniljadhav2833.dosti.auth_service.Entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
