package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.entity.UserInfo;
import com.jiaul.cloudapi.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoRepository.findByEmail(username);
    }

    public UserInfo saveUserInfo(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

    public Optional<UserInfo> getUserById(int id){
        return userInfoRepository.findById(id);
    }

    public UserInfo getUserInfoByEmail(String email){
        return userInfoRepository.findByEmail(email);
    }


}
