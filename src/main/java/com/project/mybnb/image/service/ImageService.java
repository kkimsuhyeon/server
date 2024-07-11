package com.project.mybnb.image.service;

import com.project.mybnb.image.domain.Image;
import com.project.mybnb.image.dto.ImageDto;
import com.project.mybnb.image.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public ImageDto upload(MultipartFile image) {
        String folderPath = makeFolder();
        ImageDto dto = this.toDto(image, folderPath);
        Path savePath = Paths.get(dto.getFilePath());

        try {
            image.transferTo(savePath);
            imageRepository.save(dto.toEntity());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dto;
    }

    public ImageDto getImage(Long id) {
        Image result = imageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("이미지 없음"));
        return ImageDto.fromEntity(result);
    }

    public ImageDto getImageByStoredName(String name) {
        Image result = imageRepository.findByStoredName(name).orElseThrow(() -> new IllegalArgumentException("이미지 없음"));
        return ImageDto.fromEntity(result);
    }

    private ImageDto toDto(MultipartFile image, String folderPath) {
        String originName = image.getOriginalFilename();
        Long fileSize = image.getSize();

        String uuid = UUID.randomUUID().toString();
        String storedName = uuid + "_" + originName;
        String filePath = folderPath + File.separator + storedName;

        return new ImageDto.Builder(originName, storedName, filePath).build();

    }

    private String makeFolder() {
        String folderPath = "images";

        File uploadPathFolder = new File(folderPath);
        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;

    }

}
