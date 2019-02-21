import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.services.ImageTagService;
import com.insoft.practice.bl.services.impl.ImageTagServiceImpl;
import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageTagEntity;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
class TestTagServiceImpl {

    private ImageTagService imageTagService = null;

    @Mock
    ImageRepository imageRepository;

    @BeforeEach
    public void beforeEachTest() {
        // for initialization of mocks
        MockitoAnnotations.initMocks(this);
        imageTagService = new ImageTagServiceImpl(imageRepository);
    }

    @Test
    @DisplayName("Test Entity:")
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