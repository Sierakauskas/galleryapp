import com.insoft.practice.bl.repositories.ImageRepository;
import com.insoft.practice.bl.repositories.ImageRepositoryImpl;
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
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
class TestImageRepositoryImpl {

    private ImageRepository imageRepository;
    private SimpleJpaRepository simpleJpaRepository;

    @Mock
    EntityManager entityManager;

    @Mock
    ImageEntity imageEntity;

    @BeforeEach
    public void beforeEachTest() {
        // for initialization of mocks
        MockitoAnnotations.initMocks(this);

        simpleJpaRepository = new SimpleJpaRepository(imageEntity, entityManager);
        imageRepository = new ImageRepositoryImpl(entityManager);

    }

    @Test
    public void getRequired() {

        imageRepository.getRequired("du", "trys");

    }
}

