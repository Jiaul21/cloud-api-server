package com.jiaul.cloudapi.dto;

import lombok.Data;

@Data
public class FileInfoResponse {
    private int id;
    private String fileName;
    private String fileType;
    private String url;
}
