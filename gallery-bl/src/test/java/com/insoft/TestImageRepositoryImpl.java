package com.insoft;

import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestImageRepositoryImpl {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageService imageService;

    public void saveEntities() {
        imageRepository.save(new ImageEntity(null, "mountain1", ".png", "110 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain1", ".png", "120 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain1", ".png", "130 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain4", ".png", "140 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain5", ".png", "150 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain6", ".png", "160 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain7", ".png", "160 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain8", ".png", "100 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain9", ".png", "100 Kb"));
        imageRepository.save(new ImageEntity(null, "mountain", ".png", "100 Kb"));
    }

    @Test
    public void testCustomRepositoryMethods() {

        saveEntities();
        List<ImageEntity> listFileName = imageRepository.getRequired("mountain1", "File Name");
        assertEquals(listFileName.size(), 3);

        List<ImageEntity> listFileType = imageRepository.getRequired("png", "Type");
        assertEquals(listFileType.size(), 10);

        List<ImageEntity> listFileSize = imageRepository.getRequired("100", "Size");
        assertEquals(listFileSize.size(), 3);

        List<ImageEntity> listFileID = imageRepository.getRequired("1", "ID");
        assertEquals(listFileID.size(), 1);

        List<ImageEntity> listFileBadID = imageRepository.getRequired("bad", "ID");
        assertEquals(listFileBadID.size(), 0);

        ImageEntity imageEntity = imageRepository.findById(1L).get();
        assertEquals(".png", imageEntity.getImageType());

        testServiceRepositoryMethods();

        imageRepository.deleteAll();
        assertEquals(imageRepository.findAll().size(), 0);
    }

    public void testServiceRepositoryMethods() {
        assertEquals(imageService.getAll().size(), 10);

        imageService.storeFile(null, "newfile", ".png", "10 Kb");
        List<ImageEntity> listFileName = imageService.getrequired("new", "File Name");
        assertEquals(listFileName.size(), 1);

        // can't implement. Lazy call/wrong proxy
        List<ImageEntity> listTags = imageService.getByTagsName("tag");
        assertEquals(listTags.size(), 0);

        imageService.deleteImage(1L);
        assertFalse(imageRepository.findById(1L).isPresent());
    }
}