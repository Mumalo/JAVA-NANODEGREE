package com.tichalo.userauthentication.services;

import com.tichalo.userauthentication.mapper.UserMapper;
import com.tichalo.userauthentication.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) != null;
    }

    public int save(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        User userInstance = new User();
        userInstance
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setPassword(hashPassword)
                .setSalt(encodedSalt);
        return userMapper.insertUser(user);
    }
}
