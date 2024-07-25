package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.entity.ApiKey;
import com.jiaul.cloudapi.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public ApiKey saveApiKey(ApiKey apiKey){
        apiKey.setApiKey(String.valueOf(UUID.randomUUID()));
        System.out.println(apiKey);
        return apiKeyRepository.save(apiKey);
    }

    public Optional<ApiKey> getApiKeyById(int id){
        return apiKeyRepository.findById(id);
    }

    public List<ApiKey> getAllApiKey(){
        return apiKeyRepository.findAll();
    }
    public String deleteApiKeyById(int id){
        apiKeyRepository.deleteById(id);
        return "deleted "+id;
    }
    public ApiKey getApiKey(String key){
        return apiKeyRepository.findByApiKey(key);
    }
}
