package com.jiaul.cloudapi.service;

import com.jiaul.cloudapi.entity.ApiKey;
import com.jiaul.cloudapi.entity.FilesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileServerService {

    private static final String BASE_PATH = "D:/React/web-cloud-server/src/main/resources/videos/";

    private static final String FORMAT = "classpath:videos/%s";

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApiKeyService apiKeyService;
    @Autowired
    private FilesInfoService filesInfoService;

    public Mono<Resource> getVideo(String title) {
        System.out.println(title);
        System.out.println(String.format(FORMAT, title));
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT, title)));
    }
    public byte[] getImageFile(String image) throws IOException {
        return Files.readAllBytes(new File(BASE_PATH + image).toPath());
    }

    public String storeVideoFile(MultipartFile file,String auth) throws IOException {
        ApiKey apiKey=getApiKye(auth);
        String fileName = String.valueOf(UUID.randomUUID()) + file.getOriginalFilename();
        file.transferTo(new File(BASE_PATH  + fileName));

        System.out.println("*************** hello ***************");

        FilesInfo filesInfo=new FilesInfo();
        filesInfo.setFileName(file.getOriginalFilename());
        filesInfo.setFileType("video");
        filesInfo.setUrl("http://localhost:8080/api-file/server/video/"+fileName);
        filesInfo.setApiKey(apiKey);

        System.out.println("*************** hello ***************");
        filesInfoService.saveFilesInfo(filesInfo);

        System.out.println("*************** hello ***************");
        return "http://localhost:8080/api-file/server/video/"+fileName;
    }
    public String storeImageFile(MultipartFile file, String auth) throws IOException {
        System.out.println(auth);
        ApiKey apiKey=getApiKye(auth);

        String fileName = String.valueOf(UUID.randomUUID()) + file.getOriginalFilename();
        file.transferTo(new File(BASE_PATH  + fileName));

        FilesInfo filesInfo=new FilesInfo();
        filesInfo.setFileName(file.getOriginalFilename());
        filesInfo.setFileType("image");
        filesInfo.setUrl("http://localhost:8080/api-file/server/image/"+fileName);
        filesInfo.setApiKey(apiKey);

        filesInfoService.saveFilesInfo(filesInfo);

        return "http://localhost:8080/api-file/server/image/"+fileName;
    }

    public ApiKey getApiKye(String auth){
        auth=auth.substring(7);
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.println(auth);
        return apiKeyService.getApiKey(auth);
    }


}
