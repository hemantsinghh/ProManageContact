package com.promanagecontact.services;

import com.promanagecontact.entities.User;
import java.util.*;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean userExist(String userId);
    boolean userExistByEmail(String email);
    List<User> getAllUser = null;
}
