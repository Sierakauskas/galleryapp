package com.insoft.practice.viewmodel;

import com.insoft.practice.bl.services.ImageService;
import com.insoft.practice.model.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import org.imgscalr.Scalr;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Getter
public class SearchPageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable
    private ImageService imageService;

    private String searchText;

    private String keyword;

    @Setter
    private List<ImageEntity> entityList;

    @Init
    @NotifyChange({"searchText", "keyword", "entityList"})
    public void init(@QueryParam("optradio") String keyword, @QueryParam("searchtext") String searchText) {
        this.keyword = keyword;
        this.searchText = searchText;
        if(searchText.equals("")) {
            this.searchText = "All";
            entityList = imageService.getAll();
        } else if(keyword.equals("Tag")) {
            entityList = imageService.getByTagsName(searchText);
        } else {
            entityList = imageService.getrequired(searchText, keyword);
        }
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
}