package com.insoft.practice.bl.services;

import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;
import org.springframework.stereotype.Service;

public interface ImageTagService {
    ImageTagEntity storeTag(String tagname, Long id);
    void deleteStoredTags (ImageEntity entity);

}
