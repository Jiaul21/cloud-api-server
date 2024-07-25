package com.jiaul.cloudapi.controller;

import com.jiaul.cloudapi.service.FileServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api-file/server")
public class FileServerController {

    @Autowired
    private FileServerService fileServerService;

    @GetMapping(value = "/video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title) {
        System.out.println(title);
        return fileServerService.getVideo(title);
    }

    @PostMapping("/upload/video")
    public ResponseEntity<?> storeVideoFile(@RequestParam("video") MultipartFile file,@RequestHeader("Authorization") String auth) throws IOException {
        return ResponseEntity.ok(fileServerService.storeVideoFile(file,auth));
    }

    @PostMapping("/upload/image")
    public ResponseEntity<?> storeImageFile(@RequestParam("image") MultipartFile file,@RequestHeader("Authorization") String auth) throws IOException {
        return ResponseEntity.ok(fileServerService.storeImageFile(file,auth));
    }
    @GetMapping("/image/{image}")
    public ResponseEntity<?> getImageFile(@PathVariable String image) throws IOException{
        System.out.println(image);
        byte[] imageFile= fileServerService.getImageFile(image);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(imageFile);
    }
}
