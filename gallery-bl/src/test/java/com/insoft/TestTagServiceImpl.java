package com.insoft;

import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.impl.ImageTagServiceImpl;
import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
class TestTagServiceImpl {

    @InjectMocks
    private ImageTagServiceImpl imageTagService;

    @Mock
    ImageRepository imageRepository;

    @Test
    public void testAddAndDeleteTag() {
        Long id = 1L;
        String tagname = "tagas";

        ImageEntity entity = new ImageEntity(null, "mountain", ".png", "100 Kb");

        Mockito.when(imageRepository.getOne(id)).thenReturn(entity);
        Mockito.when(imageRepository.save(any())).thenReturn(entity);

        // test if tag name is the same in ImageTagEntity
        ImageTagEntity entityTag = imageTagService.storeTag(tagname, id);
        assertEquals(tagname, entityTag.getTagName());
        assertEquals(1, entity.getTags().size());

        // test if method does not allow to store tags with same name
        ImageTagEntity entityTag1 = imageTagService.storeTag(tagname, id);
        assertNull(entityTag1);

        // test if tag is deleted
        imageTagService.deleteStoredTags(entity);
        assertEquals(0, entity.getTags().size());
    }
}