package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.exception.FileStorageException;
import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Objects;

@CommonsLog
public class HomePageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    @Getter
    private List<ImageEntity> entityList;


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
//            if (!fileType.startsWith("image/") || file.getSize() < 5) {
////                md.addAttribute("wrongType", "Sorry, not an image File");
////                md.addAttribute("users", imageService.getfive());
////                return "homePageTemplate";
////            }
            fileType = fileType.replace("image/", ".");
            try {
                // Check if the file's name contains invalid characters
                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }
//                byte[] data = media.getByteData();
                byte[] data = IOUtils.toByteArray(media.getStreamData());

                long fileSizeinKb = data.length;
                fileSizeinKb /= 1024;
                String fileSize = Objects.toString(fileSizeinKb, null);
                fileSize += " Kb";
                ImageEntity imageEntity = imageService.storeFile(data, fileName, fileType, fileSize);

            } catch (Exception ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }
    }

    public AImage getLargeImage (Long id) throws IOException {
        return new AImage(imageService.getImageById(id).getImageName(), imageService.getImageById(id).getImage());
    }

    public AImage getSmallImage (Long id) throws IOException {
        byte[] imageByte = imageService.getImageById(id).getImage();
        InputStream is = new ByteArrayInputStream(imageByte);
        BufferedImage srcImage = ImageIO.read(is);
        BufferedImage scaledImage = Scalr.resize(srcImage, 150);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(scaledImage, "png", os);
        is = new ByteArrayInputStream(os.toByteArray());
        return new AImage(imageService.getImageById(id).getImageName(), is);
    }

    @Command
    public void showImageNewZul(Long id) {
        doShowLargeImage(id);
        Executions.createComponents("showImage.zul", null, null);
    }

    @Command
    public void doShowLargeImage(@BindingParam("imageId") Long imageId) {
        HttpServletResponse response = (HttpServletResponse)Executions.getCurrent().getNativeResponse();
        ImageEntity item = imageService.getImageById(imageId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try (OutputStream os = response.getOutputStream()) {
            os.write(item.getImage());
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void showSmallPicture(Long imageId, HttpServletResponse response) {
        ImageEntity item = imageService.getImageById(imageId);
        InputStream is = new ByteArrayInputStream(item.getImage());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        try {
            BufferedImage srcImage = ImageIO.read(is);
            try {
                BufferedImage scaledImage = Scalr.resize(srcImage, 150);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(scaledImage, "jpg", baos);
                baos.flush();
                byte[] imageInByte = baos.toByteArray();
                baos.close();
                OutputStream os = response.getOutputStream();
                os.write(imageInByte);
                os.close();
            } catch (IllegalArgumentException e) {
                log.error(e);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }
}
