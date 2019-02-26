package com.insoft.practice.bl.repositories;


import com.insoft.practice.model.ImageEntity;
import com.insoft.practice.model.ImageEntity_;
import com.insoft.practice.model.ImageTagEntity;
import com.insoft.practice.model.ImageTagEntity_;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ImageRepositoryImpl extends SimpleJpaRepository<ImageEntity, Long> implements ImageRepository {

    private final EntityManager entityManager;

    public ImageRepositoryImpl(EntityManager em) {
        super(ImageEntity.class, em);
        this.entityManager = em;
    }

    @Override
    public List<ImageEntity> getRequired(String text, String keyword) {
        Long id;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> cr = cb.createQuery(ImageEntity.class);
        Root<ImageEntity> root = cr.from(ImageEntity.class);
        cr.select(root);
        if (keyword.equals("File Name")) {
            cr.select(root).where(cb.like(root.get(ImageEntity_.IMAGE_NAME), "%" + text.toLowerCase() + "%"));
        } else if (keyword.equals("Type")) {
            cr.select(root).where(cb.like(root.get(ImageEntity_.IMAGE_TYPE), "%" + text.toLowerCase() + "%"));
        } else if (keyword.equals("Size")) {
            cr.select(root).where(cb.like(root.get(ImageEntity_.IMAGE_SIZE), "%" + text.toLowerCase() + "%"));
        } else if (keyword.equals("ID")) {
            try {
                id = Long.valueOf(text);
            } catch (NumberFormatException e) {
                id = 0L;
            }
            cr.select(root).where(cb.equal(root.get(ImageEntity_.IMAGE_ID), id));
        }
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<ImageEntity> getByTagsName(String text) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImageEntity> cr = cb.createQuery(ImageEntity.class);
        Root<ImageEntity> root = cr.from(ImageEntity.class);
        Join<ImageEntity, ImageTagEntity> join = root.join(ImageEntity_.tags);

        cr.select(root).where(cb.like(cb.lower(join.get(ImageTagEntity_.TAG_NAME)), "%" + text.toLowerCase() + "%"));
        return entityManager.createQuery(cr).getResultList();
    }
}