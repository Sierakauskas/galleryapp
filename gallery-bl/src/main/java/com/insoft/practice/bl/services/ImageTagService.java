package com.insoft.practice.bl.services;

import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;

public interface ImageTagService {
    ImageTagEntity storeTag(String tagname, Long id);
    void deleteStoredTags (ImageEntity entity);

}
