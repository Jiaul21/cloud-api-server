package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.dto.LoginRequest;
import com.jiaul.cloudapi.dto.RegistrationRequest;
import com.jiaul.cloudapi.entity.UserInfo;
import com.jiaul.cloudapi.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo saveUser(RegistrationRequest registrationRequest){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(registrationRequest.getUserName());
        userInfo.setEmail(registrationRequest.getEmail());
        userInfo.setPassword(registrationRequest.getPassword());
        return userInfoRepository.save(userInfo);
    }

    public UserInfo loginUser(LoginRequest loginRequest){
        UserInfo userInfo=getUserByEmail(loginRequest.getEmail());
        if(loginRequest.getPassword().equals(userInfo.getPassword())){
            return userInfo;
        }
        return null;
    }

    public Optional<UserInfo> getUserById(int id){
        return userInfoRepository.findById(id);
    }

    public UserInfo getUserByEmail(String email){
        return userInfoRepository.findByEmail(email);
    }
}
