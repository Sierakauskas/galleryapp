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
import static org.mockito.Mockito.any;

@ExtendWith(SpringExtension.class)
class TestTagServiceImpl {

    @InjectMocks
    private ImageTagServiceImpl imageTagService;

    @Mock
    ImageRepository imageRepository;

//    @BeforeEach
//    public void beforeEachTest() {
//        // for initialization of mocks
//        MockitoAnnotations.initMocks(this);
//        imageTagService = new ImageTagServiceImpl(imageRepository);
//    }

    @Test
//    @DisplayName("Test Entity:")
    public void addAndDeleteTag() {
        Long id = 1L;
        String tagname = "tagas";

        ImageEntity entity = new ImageEntity(null, "mountain", ".png", "100 Kb");

        Mockito.when(imageRepository.getOne(id)).thenReturn(entity);
        Mockito.when(imageRepository.save(any())).thenReturn(entity);

        ImageTagEntity entityTag = imageTagService.storeTag(tagname, id);
        assertEquals(tagname, entityTag.getTagName());
        assertEquals(1, entity.getTags().size());

        imageTagService.deleteStoredTags(entity);
        assertEquals(0, entity.getTags().size());
    }
}