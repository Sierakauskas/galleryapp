package com.insoft.practice.bl.services.impl;

import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;


@RequiredArgsConstructor
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public List<ImageEntity> getAll() {
        return imageRepository.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
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

    public String getTagName(Long id) {
        ImageEntity entity = imageRepository.getOne(id);
        String names = "";
        Iterator<ImageTagEntity> iterator = entity.getTags().iterator();
        while (iterator.hasNext()) {
            ImageTagEntity imageTagEntity = iterator.next();
            names += "#" + imageTagEntity.getTagName() + " ";
        }
        if (names.equals("")) {
            return "No Tags";
        }
        return names;
    }

    public List<ImageEntity> getrequired(String text, String keyWord) {
        return imageRepository.getRequired(text, keyWord);
    }

    public List<ImageEntity> getByTagsName(String text) {
        return imageRepository.getByTagsName(text);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void setImageName(Long id, String name) {
        ImageEntity entity = imageRepository.getOne(id);
        entity.setImageName(name);
        imageRepository.save(entity);
    }

    @Secured("ROLE_ADMIN")
    public void deleteImage(Long id) {
        ImageEntity entity = imageRepository.getOne(id);
        imageRepository.delete(entity);
    }
}

