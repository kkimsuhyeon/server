package com.project.mybnb.image.domain;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long id;

    private String originalFileName;

    private String storedFileName;

    private String filePath;

    private Long fileSize;
}
