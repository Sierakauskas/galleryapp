package com.insoft.practice.bl.repositories;

import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageEntity_;
import com.insoft.practice.model.ImageTagEntity;
import com.insoft.practice.model.ImageTagEntity_;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
class ImageTagRepositoryImpl extends SimpleJpaRepository<ImageTagEntity, Long> implements ImageTagRepository {

    private final EntityManager entityManager;

    public ImageTagRepositoryImpl(EntityManager em) {
        super(ImageTagEntity.class, em);
        this.entityManager = em;
    }

    @Override
    public List<ImageTagEntity> getTagRequired(String text) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImageTagEntity> cr = cb.createQuery(ImageTagEntity.class);
        Root<ImageTagEntity> root = cr.from(ImageTagEntity.class);
        cr.select(root).where(cb.like(root.get(ImageTagEntity_.TAG_NAME), "%" + text.toLowerCase() + "%"));
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<ImageTagEntity> getTagsByImageName(String text) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImageTagEntity> cr = cb.createQuery(ImageTagEntity.class);
        Root<ImageTagEntity> root = cr.from(ImageTagEntity.class);
        Join<ImageTagEntity, ImageEntity> join = root.join(ImageTagEntity_.image);

        cr.select(root).where(cb.equal(cb.lower(join.get(ImageEntity_.IMAGE_NAME)),text.toLowerCase()));
        return entityManager.createQuery(cr).getResultList();
    }

//    public void deleteAllTags (Long id) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaDelete<ImageTagEntity> criteriaDelete = cb.createCriteriaDelete(ImageTagEntity.class);
//        Root<ImageTagEntity> root = criteriaDelete.from(ImageTagEntity.class);
//        criteriaDelete.where(cb.like(root.get(ImageTagEntity_.TAG_NAME), "%%"));
//    }
}
