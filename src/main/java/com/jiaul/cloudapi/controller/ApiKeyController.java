package com.jiaul.cloudapi.controller;

import com.jiaul.cloudapi.entity.ApiKey;
import com.jiaul.cloudapi.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api-key")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    @PostMapping("/create")
    public ApiKey saveApiKey(@RequestBody ApiKey apiKey){
        return apiKeyService.saveApiKey(apiKey);
    }

    @GetMapping("/get/{id}")
    public Optional<ApiKey> getApiKeyById(@PathVariable int id){
        return apiKeyService.getApiKeyById(id);
    }

    @GetMapping("/getAll")
    public List<ApiKey> getAllApiKey(){
        return apiKeyService.getAllApiKey();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApiKeyById(@PathVariable int id){
        return apiKeyService.deleteApiKeyById(id);
    }
}
