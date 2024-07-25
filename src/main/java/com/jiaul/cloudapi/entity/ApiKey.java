package com.jiaul.cloudapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String apiKey;
    private String projectName;

    @ManyToOne
    private UserInfo userInfo;

    @OneToMany(mappedBy = "apiKey",cascade = CascadeType.ALL)
    private List<FilesInfo> filesInfoList;
}
