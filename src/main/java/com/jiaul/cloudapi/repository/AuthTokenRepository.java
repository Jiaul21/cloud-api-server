package com.jiaul.cloudapi.repository;

import com.jiaul.cloudapi.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Integer> {
}
