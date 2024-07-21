package com.promanagecontact.services.Implementaion;

import com.promanagecontact.entities.User;
import com.promanagecontact.form.ResourceNotFoundException;
import com.promanagecontact.repositories.UserRepo;
import com.promanagecontact.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    @Override
    public User saveUser(User user) {
        String userId  = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of("NORMAL_USER"));
        user.setUserId(userId);
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User validUser = userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        validUser.setName(user.getName());
        validUser.setEmail(user.getEmail());
        validUser.setAbout(user.getAbout());
        validUser.setPassword(user.getPassword());
        validUser.setProfilePic(user.getProfilePic());
        validUser.setEnabled(user.isEnabled());
        validUser.setEmailVerified(user.isEmailVerified());
        User u = userRepo.save(validUser);
        return Optional.ofNullable(u);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
        userRepo.deleteById(user.getUserId());
    }

    @Override
    public boolean userExist(String userId) {
        User user = userRepo.findById(userId).orElse(null);
        return user != null;
    }

    @Override
    public boolean userExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user!=null;
    }


    public Boolean IfPasswordMatches(String email, String enteredPassword){
            String password = userRepo.findPasswordByEmail(email);
            return passwordEncoder.matches(enteredPassword, password);
    }
    public List<User> getAllUser(){
        return userRepo.findAll();
    }
}
