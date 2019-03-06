package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

@CommonsLog
public class ViewImageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    @Getter
    private Long imageId;

    @Init
    @NotifyChange("imageId")
    public void init(@QueryParam("id") Long id) {
        imageId = id;
    }

    public void doShowLargeImage() {
        HttpServletResponse response = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
        ImageEntity item = imageService.getImageById(imageId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try (OutputStream os = response.getOutputStream()) {
            os.write(item.getImage());
        } catch (IOException e) {
            log.error(e);
        }
    }
}
