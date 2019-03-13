package com.insoft.practice.components;

import com.insoft.practice.bl.services.ImageService;
import org.imgscalr.Scalr;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TableComp extends HtmlMacroComponent {
    private static final long serialVersionUID = 7935967626121317003L;

    @WireVariable
    private ImageService imageService;

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