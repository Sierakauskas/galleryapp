package com.insoft.practice.bl.services.impl;


import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.repositories.ImageRepositoryImpl;
import com.insoft.practice.model.ImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.*;
import java.util.List;



@RequiredArgsConstructor
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageRepositoryImpl imageRepositoryCustom;

    public List<ImageEntity> getAll() {
        return imageRepository.findAll();
    }

    public ImageEntity storeFile(byte[] data, String fileName, String fileType, String fileSize) {
        ImageEntity imageEntity = new ImageEntity(data, fileName, fileType, fileSize);
        return imageRepository.save(imageEntity);
    }

    public ImageEntity getImageById(Long id) {
        if (imageRepository.findById(id).isPresent()) {
            return imageRepository.findById(id).get();
        }
        throw new FileStorageException("Sorry! Filename contains invalid id " + id);
    }

    public List<ImageEntity> getfive() {
        List<ImageEntity> allList = imageRepository.findAll();
        while (allList.size() > 5) {
            allList.remove(allList.get(0));
        }
        Collections.reverse(allList);
        return allList;
    }


    public List<ImageEntity> getrequired(String text, String keyWord) {
        return imageRepositoryCustom.getRequired(text, keyWord);
    }

    public List<ImageEntity> getByTagsName(String text) {
        return imageRepositoryCustom.getByTagsName(text);
    }

    public String getencodedImage(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    public void setImageName(Long id, String name) {
        ImageEntity entity = imageRepository.getOne(id);
        entity.setImageName(name);
        imageRepository.save(entity);
    }

    public void deleteImage(Long id) {
        ImageEntity entity = imageRepository.getOne(id);
        imageRepository.delete(entity);
    }


}

