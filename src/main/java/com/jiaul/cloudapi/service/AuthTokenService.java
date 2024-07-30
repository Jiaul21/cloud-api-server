package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.entity.AuthToken;
import com.jiaul.cloudapi.entity.UserInfo;
import com.jiaul.cloudapi.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AuthToken saveAuthToken(AuthToken authToken){
        return authTokenRepository.save(authToken);
    }
    public AuthToken createAuthTokenByUserInfo(UserInfo userInfo){
        AuthToken authToken = new AuthToken();
        authToken.setTokenValue(jwtService.generateToken(userInfo));
        authToken.setTokenType("Bearer ");
        authToken.setExpired(false);
        authToken.setRevoked(false);
        authToken.setUserInfo(userInfo);
        return authToken;
    }

    public AuthToken updateAuthTokenValueByUserInfo(UserInfo userInfo){
        userInfo.getAuthToken().setTokenValue(jwtService.generateToken(userInfo));
        userInfo.getAuthToken().setExpired(false);
        userInfo.getAuthToken().setRevoked(false);
        return authTokenRepository.save(userInfo.getAuthToken());
    }

    public void revokeToken(AuthToken authToken){
        authToken.setRevoked(true);
        authTokenRepository.save(authToken);
    }
}
