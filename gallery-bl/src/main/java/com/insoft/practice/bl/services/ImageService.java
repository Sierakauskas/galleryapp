package com.insoft.practice.bl.services;

import com.insoft.practice.model.ImageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ImageService {
    List<ImageEntity> getAll();
    List<ImageEntity> getfive();
    List<ImageEntity> getrequired(String text, String keyWord);
    List<ImageEntity> getByTagsName (String text);
    ImageEntity storeFile(byte[] data, String fileName, String fileType, String fileSize);
    ImageEntity getImageById(Long id);
    String getTagName(Long id);
    void setImageName(Long id, String name);
    void deleteImage(Long id);

}
