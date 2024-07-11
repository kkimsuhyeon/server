package com.project.mybnb.image.dto;

import com.project.mybnb.image.domain.Image;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImageDto {

    private Long id;
    private String originalName;
    private String storedName;
    private String filePath;
    private Long fileSize;

    public ImageDto(Builder builder) {
        this.id = builder.id;
        this.originalName = builder.originalName;
        this.storedName = builder.storedName;
        this.filePath = builder.filePath;
        this.fileSize = builder.fileSize;
    }

    public Image toEntity() {
        return Image.builder()
                .originalFileName(originalName)
                .storedFileName(storedName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();
    }

    public static ImageDto fromEntity(Image entity) {
        return new Builder(entity.getOriginalFileName(), entity.getStoredFileName(), entity.getFilePath())
                .id(entity.getId())
                .fileSize(entity.getFileSize())
                .build();
    }

    public static class Builder {
        private Long id;
        private String originalName;
        private String storedName;
        private String filePath;
        private Long fileSize;

        public Builder(String originalName, String storedName, String filePath) {
            this.originalName = originalName;
            this.storedName = storedName;
            this.filePath = filePath;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder fileSize(Long fileSize) {
            this.fileSize = fileSize;
            return this;
        }

        public ImageDto build() {
            return new ImageDto(this);
        }
    }

}
