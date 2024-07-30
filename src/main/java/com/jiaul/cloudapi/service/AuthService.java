package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.dto.AuthRequest;
import com.jiaul.cloudapi.dto.AuthResponse;
import com.jiaul.cloudapi.dto.LogoutRequest;
import com.jiaul.cloudapi.entity.AuthToken;
import com.jiaul.cloudapi.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthTokenService authTokenService;

    public AuthResponse Login(AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();

        try {
            UserInfo userInfo = (UserInfo) authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())).getPrincipal();

            AuthToken newAuthToken = new AuthToken();
            if (userInfo.getAuthToken() == null) {     // first login try after SignUp
                newAuthToken = authTokenService.createAuthTokenByUserInfo(userInfo);
                userInfo.setAuthToken(newAuthToken);
            } else if (userInfo.getAuthToken().isRevoked() &&             // have to revoke and not expire
                    !jwtService.isTokenExpired(userInfo.getAuthToken().getTokenValue())) {
                response.setMessage("Already login with a Device");
                return response;
            } else {
                newAuthToken = authTokenService.updateAuthTokenValueByUserInfo(userInfo);
            }

            if (authTokenService.saveAuthToken(newAuthToken) != null) {
                String message = "User Login Successfully";
                response = makeAuthResponse(userInfo, newAuthToken, message);
            }
        } catch (Exception e) {
            response.setMessage("Bad Credential ! Try Right User & Password. " + e.getMessage());
        }
        return response;
    }

    public String signUp(AuthRequest authRequest) {

        UserInfo userInfo = new UserInfo();
        userInfo.setName(authRequest.getName());
        userInfo.setEmail(authRequest.getEmail());
        userInfo.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userInfo.setRole(authRequest.getRole().toUpperCase());
        userInfo.setEnabled(true);

        try {
            if (userInfoService.saveUserInfo(userInfo) != null) {
                return "Registration Complete";
            }
        } catch (Exception e) {
            return "Internal Server Error. " + e.getMessage();
        }
        return "Registration Failed";
    }

    public String logOut(LogoutRequest logoutRequest) {

        UserInfo userInfo = userInfoService.getUserInfoByEmail(logoutRequest.getEmail());

        if (userInfo != null && userInfo.getAuthToken().getTokenValue().equals(logoutRequest.getTokenValue())) {
            authTokenService.revokeToken(userInfo.getAuthToken());
            return "Logout Successfully";
        }
        return "Logout Failed";
    }

    public AuthResponse makeAuthResponse(UserInfo userInfo, AuthToken authToken, String message) {
        AuthResponse response = new AuthResponse();
        response.setId(userInfo.getId());
        response.setEmail(userInfo.getEmail());
        response.setName(userInfo.getName());
        response.setRole(userInfo.getRole());
        response.setMessage(message);
        response.setToken(authToken.getTokenValue());
        response.setTokenType(authToken.getTokenType());
        return response;
    }
}
