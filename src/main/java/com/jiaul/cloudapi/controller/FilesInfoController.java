package com.jiaul.cloudapi.controller;

import com.jiaul.cloudapi.entity.FilesInfo;
import com.jiaul.cloudapi.service.FilesInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api-file")
public class FilesInfoController {
    @Autowired
    private FilesInfoService filesInfoService;

    @PostMapping("/save")
    public FilesInfo saveFilesInfo(FilesInfo filesInfo) {
        return filesInfoService.saveFilesInfo(filesInfo);
    }

    @GetMapping("/getAll-image")
    public List<FilesInfo> getAllFileInfo() {
        return filesInfoService.getAllFileInfo();
    }

}
