package com.insoft.practice.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TagName")
@Table(name = "tag")
@EqualsAndHashCode
@Proxy(lazy=false)
public class ImageTagEntity {

    @Id
    @GeneratedValue
    private Long id;

//    @NaturalId
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<ImageEntity> image = new HashSet<>();

    public ImageTagEntity(String name) {
        this.tagName = name;
    }

}
