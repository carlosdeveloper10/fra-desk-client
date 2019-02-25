/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmsoft.fra.deskclient.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.cmsoft.fra.deskclient.generic.service.IlegalImageExtesion;
import sun.misc.BASE64Encoder;

/**
 *
 * @author carlos
 */
public class ImageUtil {

    private ImageUtil() {
        //Avoid to create an instance of this class.
    }

    public static String convertToBase64(File photoFile) throws IOException {

        if (!isExtesionImageOk(photoFile)) {
            throw new IlegalImageExtesion("The image extension is not valid. The permitted extensions are: jpg, png and jpeg.");
        }

        String imageExtension = getImageExtesion(photoFile);
        BufferedImage image = ImageIO.read(photoFile);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, imageExtension, bos);

        byte[] imageBytes = bos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Image = Base64.getEncoder().encodeToString(bos.toByteArray());
        bos.close();

        return ("data:image/" + imageExtension + ";base64,") + base64Image;
    }

    public static boolean isExtesionImageOk(File photoFile) {
        String imageExtension = getImageExtesion(photoFile);
        return (imageExtension == "png" || imageExtension == "jpg" || imageExtension == "jpeg");
    }

    public static String getImageExtesion(File photoFile) {
        String fileName = photoFile.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
