package com.project.mybnb.image.controller;

import com.project.mybnb.image.dto.request.ImageUploadDto;
import com.project.mybnb.image.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(ImageUploadDto dto) {

        for (MultipartFile image : dto.uploadedImages()) {
            if (!Objects.requireNonNull(image.getContentType()).startsWith("image")) {
                // image 파일이 아닌경우에 대한 에러 처리
            }

            imageService.upload(image);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getImage() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
