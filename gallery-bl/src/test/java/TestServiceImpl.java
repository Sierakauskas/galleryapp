import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.bl.services.impl.ImageServiceImpl;
import com.insoft.practice.model.ImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TestServiceImpl {

    private ImageService imageService;
    private static final Long ID = 1L;

    @Mock
    ImageRepository imageRepository;

    @Mock
    ImageRepository imageTagRepository;

    @BeforeEach
    public void beforeEachTest() {
        // for initialization of mocks
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(imageRepository);
    }

    @Test
    public void testImageByIdOk() {
        ImageEntity entityForTest1 = new ImageEntity(null, "mountain", ".png", "100 Kb");
        entityForTest1.setImageId(ID);
        when(imageRepository.findById(ID)).thenReturn(Optional.of(entityForTest1));
        ImageEntity imageEntity = imageService.getImageById(ID);
        assertEquals(imageEntity, entityForTest1);
    }

    @Test
    public void testGetImageByIdException() {
//         can't implement
        when(imageRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(FileStorageException.class, () -> {
            imageService.getImageById(0L);
        });
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

        when(imageRepository.findAll()).thenReturn(list);
        List<ImageEntity> newListEntity = imageService.getfive();

        assertNotEquals(newListEntity.size(), listOfCreatedEntity.size());
        assertEquals(newListEntity.size(), 5);
    }

    @Test
    public void saveImageName() {
        ImageEntity entityForTest = new ImageEntity(null, "mountain", ".png", "100 Kb");

        when(imageRepository.getOne(ID)).thenReturn(entityForTest);
        when(imageRepository.save(any())).thenReturn(entityForTest);

        String newName = "lake";
        imageService.setImageName(ID, newName);
        assertEquals(entityForTest.getImageName(), newName);
    }

    @Test
    public void deleteImageEntity() {
        ImageEntity entityForTest = new ImageEntity(null, "mountain", ".png", "100 Kb");

        when(imageRepository.getOne(ID)).thenReturn(entityForTest);

        imageService.deleteImage(ID);

        // todo
        // kaip implementuoti repository.remove()
    }
}