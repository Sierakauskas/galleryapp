//package com.insoft;////
//////import com.insoft.practice.bl.repositories.ImageRepositoryImpl;
//////import com.insoft.practice.bl.services.impl.ImageServiceImpl;
//////import org.junit.jupiter.api.BeforeEach;
//////import org.junit.jupiter.api.Test;
//////import org.junit.jupiter.api.extension.ExtendWith;
//////import org.mockito.Mock;
//////import org.mockito.MockitoAnnotations;
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.PropertySource;
//////import org.springframework.test.context.junit.jupiter.SpringExtension;
//////
//////import javax.persistence.EntityManager;
//////
//////@ExtendWith(SpringExtension.class)
//////@PropertySource("test/resources/")
//////public class TestImageRepositoryImpl {
//////
//////    @Mock
//////    EntityManager entityManager;
//////
//////    private ImageRepositoryImpl imageRepository;
//////
//////    @BeforeEach
//////    public void beforeEachTest() {
//////        // for initialization of mocks
//////        MockitoAnnotations.initMocks(this);
//////        imageRepository = new ImageRepositoryImpl(entityManager);
//////    }
//////
//////    @Test
//////    public void testGet() {
//////
//////
//////
//////        imageRepository.getByTagsName("ID");
//////
//////    }
//////}
////
////
//import com.insoft.practice.bl.repositories.ImageRepository;
//import com.insoft.practice.bl.repositories.ImageRepositoryImpl;
//import com.insoft.practice.model.ImageEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@RunWith(SpringRunner.class)
//@EnableJpaRepositories(basePackages={"com.insoft.practice.bl.repositories"})
//@EntityScan(basePackages={"com.insoft.practice.model"})
//@TestPropertySource("classpath:applicationh2.properties")
//@ContextConfiguration(classes = {TestConfiguration.class, ImageRepository.class})
//public class TestImageRepositoryImpl {
//
////    @BeforeEach
////    public void beforeEachTest() {
////        // for initialization of mocks
////        MockitoAnnotations.initMocks(this);
////        studentRepository = new ImageRepositoryImpl(entityManager);
//
//
//    @Resource
//    ImageRepositoryImpl studentRepository;
//
//    @Test
//    public void givenStudent_whenSave_thenGetOk() {
//
//
//        ImageEntity entityForTest1 = new ImageEntity(null, "mountain", ".png", "100 Kb");
//        entityForTest1.setImageId(1L);
//        studentRepository.save(entityForTest1);
//
//        ImageEntity imageEntity = studentRepository.getOne(1L);
//        String du = imageEntity.getImageType();
////        assertEquals("john", imageEntity.getImageName());
//    }
//}