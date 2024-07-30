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
        List<FilesInfo> files= new ArrayList<>();

        filesInfoRepository.findAll().forEach(file -> {
            FilesInfo newFile=new FilesInfo();
            newFile.setId(file.getId());
            newFile.setFileName(file.getFileName());
            newFile.setFileType(file.getFileType());
            newFile.setUrl(file.getUrl());

            files.add(newFile);
        });
        return files;
    }
}
