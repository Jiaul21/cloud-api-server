package com.jiaul.cloudapi.repository;

import com.jiaul.cloudapi.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByEmail(String email);
}
