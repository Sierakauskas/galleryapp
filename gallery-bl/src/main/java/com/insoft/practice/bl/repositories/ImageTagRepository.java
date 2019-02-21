package com.insoft.practice.bl.repositories;

import com.insoft.practice.model.ImageTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ImageTagRepository extends JpaRepository<ImageTagEntity, Long> {
    List<ImageTagEntity> getTagRequired(String text);

    List<ImageTagEntity> getTagsByImageName(String text);
}
