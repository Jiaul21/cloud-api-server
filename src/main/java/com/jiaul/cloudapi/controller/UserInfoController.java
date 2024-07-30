package com.jiaul.cloudapi.controller;

import com.jiaul.cloudapi.entity.UserInfo;
import com.jiaul.cloudapi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api-user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/get-id/{id}")
    public Optional<UserInfo> getUserById(@PathVariable int id){
        return userInfoService.getUserById(id);
    }

    @GetMapping("/get-email/{email}")
    public UserInfo getUserByEmail(@PathVariable String email){
        System.out.println(email);
        return userInfoService.getUserInfoByEmail(email);
    }
}
