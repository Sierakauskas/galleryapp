package com.insoft.practice.model;

import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ImageTable")
@NoArgsConstructor
@Getter
@Setter
@Proxy(lazy=false)
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long imageId;

    @Column(name = "Image")
    @Lob
    private byte[] image;

    @Column(name = "Name")
    private String imageName;

    @Column(name = "Type")
    private String imageType;

    @Column(name = "Size")
    private String imageSize;

    public ImageEntity(byte[] image, String imageName, String imageType, String imageSize) {
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, fetch=FetchType.EAGER)
    @JoinTable(name = "image_tag",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<ImageTagEntity> tags = new HashSet<>();
}

