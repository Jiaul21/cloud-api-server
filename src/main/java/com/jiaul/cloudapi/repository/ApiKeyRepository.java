package com.jiaul.cloudapi.repository;

import com.jiaul.cloudapi.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey,Integer> {

    ApiKey findByApiKey(String apiKey);
}
