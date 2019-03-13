package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.IOUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@CommonsLog
public class HomePageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    @Getter
    private List<ImageEntity> entityList;

    private Checkbox tosCheckbox;


    @Init
    @NotifyChange("entityList")
    public void init() {
        entityList = imageService.getfive();
    }

    @Command
    public void doUploadImage(@BindingParam("media") Media media) throws IOException {
        if (media != null) {
            String fileName = media.getName();
            String fileType = media.getContentType();
            if (!fileType.startsWith("image/") || media.getByteData().length<5 || fileType.startsWith("image/webp")) {
                Clients.showNotification("Invalid type format!","warning",tosCheckbox,"end_before",3000);
            } else {
                fileType = fileType.replace("image/", ".");
                try {
//                byte[] data = media.getByteData();
                    byte[] data = IOUtils.toByteArray(media.getStreamData());
                    long fileSizeinKb = data.length;
                    fileSizeinKb /= 1024;
                    String fileSize = Objects.toString(fileSizeinKb, null);
                    fileSize += " Kb";
                    ImageEntity imageEntity = imageService.storeFile(data, fileName, fileType, fileSize);
                    Executions.getCurrent().sendRedirect("/zul/editImage.zul?edit=" + imageEntity.getImageId());
                } catch (Exception ex) {
                    throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
                }
            }
        }
    }
}
