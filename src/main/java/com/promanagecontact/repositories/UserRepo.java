package com.promanagecontact.repositories;

import com.promanagecontact.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    @Query(""" 
              select password 
              from User where 
              email = :email """)
    String findPasswordByEmail(String email);
}
