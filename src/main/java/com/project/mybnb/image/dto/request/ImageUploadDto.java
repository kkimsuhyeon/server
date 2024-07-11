package com.project.mybnb.image.dto.request;

import com.project.mybnb.image.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ImageUploadDto(
        List<MultipartFile> uploadedImages
) {

    public List<ImageDto> toDto(String folderPath) {

        return uploadedImages.stream().map((image) -> {
            String originName = image.getOriginalFilename();
            Long fileSize = image.getSize();
            String storedName = makeStoredName(originName);

            return new ImageDto.Builder(originName, storedName, folderPath).fileSize(fileSize).build();
        }).collect(Collectors.toList());

    }

    private String makeStoredName(String fileName) {
        String uuid = UUID.randomUUID().toString();
        return uuid + "_" + fileName;
    }

}
