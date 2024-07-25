package com.jiaul.cloudapi.repository;

import com.jiaul.cloudapi.entity.FilesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesInfoRepository extends JpaRepository<FilesInfo,Integer> {
}
