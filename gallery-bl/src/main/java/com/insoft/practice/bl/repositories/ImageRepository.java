package com.insoft.practice.bl.repositories;

import com.insoft.practice.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> getRequired(String text, String keyword);
    List<ImageEntity> getByTagsName(String text);
}