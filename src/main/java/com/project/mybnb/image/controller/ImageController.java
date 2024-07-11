package com.project.mybnb.image.controller;

import com.project.mybnb.image.dto.ImageDto;
import com.project.mybnb.image.dto.request.ImageUploadDto;
import com.project.mybnb.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
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

        List<String> result = new ArrayList<>();

        for (MultipartFile image : dto.uploadedImages()) {
            if (!Objects.requireNonNull(image.getContentType()).startsWith("image")) {
                // image 파일이 아닌경우에 대한 에러 처리
            }

            ImageDto temp = imageService.upload(image);
            result.add(temp.getStoredName());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{stored-name}")
    public ResponseEntity<?> getImageByName(@PathVariable("stored-name") String name) throws MalformedURLException {
        ImageDto image = imageService.getImageByStoredName(name);

        UrlResource urlResource = new UrlResource("file:" + image.getFilePath());

        String encodedUploadFileName = UriUtils.encode(image.getOriginalName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
