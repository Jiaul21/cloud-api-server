package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.entity.FilesInfo;
import com.jiaul.cloudapi.repository.FilesInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilesInfoService {

    @Autowired
    private FilesInfoRepository filesInfoRepository;

    public FilesInfo saveFilesInfo(FilesInfo filesInfo) {
        return filesInfoRepository.save(filesInfo);
    }

    public List<FilesInfo> getAllFileInfo(){
        List<FilesInfo> images= new ArrayList<>();

        List<FilesInfo> info= filesInfoRepository.findAll();
        info.forEach(filesInfo -> {
            if(filesInfo.getFileType().equals("image")){
                System.out.println(filesInfo.getUrl());
                images.add(filesInfo);
            }

        });
        return images;
    }
}
