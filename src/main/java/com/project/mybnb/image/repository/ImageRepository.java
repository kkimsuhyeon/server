package com.project.mybnb.image.repository;

import com.project.mybnb.image.domain.Image;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImageRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Optional<Image> findById(Long id) {
        Image result = entityManager.find(Image.class, id);
        return Optional.ofNullable(result);
    }

    public Optional<Image> findByStoredName(String name) {
        String query = "SELECT i FROM Image AS i" +
                " WHERE i.storedFileName = :name";

        Image result = entityManager.createQuery(query, Image.class).setParameter("name", name).getSingleResult();

        return Optional.ofNullable(result);
    }

    public Image save(Image newImage) {
        entityManager.persist(newImage);
        return newImage;
    }
}
