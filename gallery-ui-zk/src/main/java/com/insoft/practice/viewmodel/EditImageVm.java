package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.bl.services.ImageTagService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import java.io.IOException;
import java.io.Serializable;

@CommonsLog
@Getter
public class EditImageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    @WireVariable
    private ImageTagService imageTagService;

    private Long imageId;

    @Setter
    private String imageName;

    @Setter
    private String tagName;

    @Setter
    private ImageEntity imageEntity;

    @Init
    @NotifyChange("entityList")
    public void init(@QueryParam("edit") Long id) {
        imageId = id;
        imageEntity = imageService.getImageById(id);
    }

    public AImage getLargeImage() throws IOException {
        return new AImage(imageService.getImageById(imageId).getImageName(), imageService.getImageById(imageId).getImage());
    }

    @Command
    @NotifyChange("imageEntity")
    public void doChangeName() {
        imageService.setImageName(imageId, imageName);
        Executions.getCurrent().sendRedirect("");
    }

    @Command
    @NotifyChange("imageEntity")
    public void doChangeTag() {
        imageTagService.storeTag(tagName, imageId);
        Executions.getCurrent().sendRedirect("");
    }

    @Command
    @NotifyChange("imageEntity")
    public void doDeleteTag() {
        imageTagService.deleteStoredTags(imageEntity);
        Executions.getCurrent().sendRedirect("");
    }

    @Command
    public void doDeleteImage() {
        imageService.deleteImage(imageId);
        Executions.getCurrent().sendRedirect("/zul/delete.zul?id=" + imageEntity.getImageId());
    }

}
