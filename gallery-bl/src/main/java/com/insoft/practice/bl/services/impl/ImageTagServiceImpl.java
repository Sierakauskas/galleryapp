package com.insoft.practice.bl.services.impl;

import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.ImageTagService;
import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service("imageTagService")
public class ImageTagServiceImpl implements ImageTagService {

    private final ImageRepository imageRepository;


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Override
    public ImageTagEntity storeTag(String tagname, Long id) {
            ImageTagEntity tagEntity = new ImageTagEntity(tagname);
            ImageEntity entity = imageRepository.getOne(id);
            entity.getTags().add(tagEntity);
            imageRepository.save(entity);
            return tagEntity;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteStoredTags (ImageEntity entity) {
        Set<ImageTagEntity> newList = entity.getTags();
        entity.getTags().removeAll(newList);
        imageRepository.save(entity);
    }
}
