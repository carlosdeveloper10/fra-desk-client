/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmsoft.fra.deskclient.util;

import org.cmsoft.fra.deskclient.generic.service.IlegalImageExtesion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.IllegalFormatException;
import java.io.IOException;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

/**
 *
 * @author carlos
 */
public class ImageUtilTest {

    @Test
    public void whenImageIsOkThenReturnBase64ImageRepresentedAsString() throws IOException {
        
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("image-for-test/CR7-x64photo.txt").getFile());
        Scanner fileIn = new Scanner(file);
        String exceptedBase64Image = fileIn.nextLine();
        File initialImage = new File(classLoader.getResource("image-for-test/CR7.jpg").getFile());
        
        
        String actualBase64Image = ImageUtil.convertToBase64(initialImage);
        
        assertThat(exceptedBase64Image).isEqualTo(actualBase64Image);
    }
    
    @Test
    public void whenImageOrFileDoesNotHaveCorrectExtensionThrowImageFormaException() throws FileNotFoundException{
        
        ClassLoader classLoader = getClass().getClassLoader();
        File initialImage = new File(classLoader.getResource("image-for-test/kiwi.svg").getFile());
        
        assertThatThrownBy(() -> {
            ImageUtil.convertToBase64(initialImage);
        }).isExactlyInstanceOf(IlegalImageExtesion.class)
			.hasMessage("The image extension is not valid. The permitted extensions are: jpg, png and jpeg.");
    }
}
