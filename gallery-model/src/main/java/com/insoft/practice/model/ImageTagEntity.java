package com.insoft.practice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "TagName")
@Table(name = "tag")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageTagEntity tag = (ImageTagEntity) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }

}
