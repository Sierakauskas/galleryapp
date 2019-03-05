package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.zkoss.bind.annotation.*;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javax.persistence.Query;
import java.io.IOException;
import java.io.Serializable;

@CommonsLog
public class EditImageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    @Getter
    private Long imageId;

    @Getter @Setter
    private String imageName;

    @Getter @Setter
    private ImageEntity imageEntity;

    @Init
    @NotifyChange("entityList")
    public void init(@QueryParam("edit") Long id) {
        imageId = id;
        imageEntity = imageService.getImageById(id);
    }

    public AImage getLargeImage () throws IOException {
        return new AImage(imageService.getImageById(imageId).getImageName(), imageService.getImageById(imageId).getImage());
    }

    @Command
    public void doChangeName () {
        imageService.setImageName(imageId, imageName);
    }


    //    @QueryParam("id") Long id
//    @Command
//    @NotifyChange("entityList")
//    public void doSearch() {
//        entityList = imageService.getrequired("id", searchKeyword);
//    }

}