import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.repositories.ImageRepositoryImpl;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.bl.services.impl.ImageServiceImpl;
import com.insoft.practice.model.ImageEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
class TestServiceImpl {

    private ImageService imageService;
    private Long id = 1L;

    @Mock
    ImageRepository imageRepository;

    @Mock
    ImageRepositoryImpl imageTagRepositoryImpl;

    @BeforeEach
    public void beforeEachTest() {
        // for initialization of mocks
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(imageRepository, imageTagRepositoryImpl);
    }

    @Test
    public void getImageByID() {
        ImageEntity entityForTest1 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        entityForTest1.setImageId(id);
        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.of(entityForTest1));
        ImageEntity imageEntity = imageService.getImageById(id);
        assertEquals(imageEntity, entityForTest1);

        // can't implement
//        Mockito.when(imageRepository.findById(id)).thenReturn(Optional.empty());
//        assertThrows(FileStorageException.class, () -> {
//            imageService.getImageById(0L);
//        });
    }

    @Test
    public void getFiveFromList() {
        ImageEntity entityForTest1 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        ImageEntity entityForTest2 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        ImageEntity entityForTest3 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        ImageEntity entityForTest4 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        ImageEntity entityForTest5 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        ImageEntity entityForTest6 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        List<ImageEntity> listOfCreatedEntity = asList(entityForTest1, entityForTest2, entityForTest3, entityForTest4, entityForTest5, entityForTest6);
        List<ImageEntity> list = new LinkedList<>(listOfCreatedEntity);

        Mockito.when(imageRepository.findAll()).thenReturn(list);
        List<ImageEntity> newListEntity = imageService.getfive();

        assertNotEquals(newListEntity.size(), listOfCreatedEntity.size());
        assertEquals(newListEntity.size(), 5);
    }

    @Test
    public void saveImageName() {
        ImageEntity entityForTest = new ImageEntity(null, "mountain", ".png", "100 Kb");

        Mockito.when(imageRepository.getOne(id)).thenReturn(entityForTest);
        Mockito.when(imageRepository.save(any())).thenReturn(entityForTest);

        String newName = "lake";
        imageService.setImageName(id, newName);
        assertEquals(entityForTest.getImageName(), newName);
    }

    @Test
    public void deleteImageEntity() {
        ImageEntity entityForTest = new ImageEntity(null, "mountain", ".png", "100 Kb");

        Mockito.when(imageRepository.getOne(id)).thenReturn(entityForTest);

        imageService.deleteImage(id);

        // todo
        // kaip implementuoti repository.remove()
    }
}