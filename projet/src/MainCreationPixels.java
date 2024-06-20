import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainCreationPixels {
 public static void main(String[] args) throws IOException {
    BufferedImage image2 = new BufferedImage(50, 50,BufferedImage.TYPE_3BYTE_BGR);

    for(int i=0;i<50;i++){
        for(int j = 0 ; j<50;j++){
            image2.setRGB(i, j, new java.awt.Color(255,255,255).getRGB());
        }
    }
    for(int i=5;i<30;i++){
        for(int j = 5 ; j<10;j++){
            image2.setRGB(i, j, new java.awt.Color(255,0,0).getRGB());
        }
    }
    for(int i=5;i<30;i++){
        for(int j = 20 ; j<30;j++){
            image2.setRGB(i, j, new java.awt.Color(0,255,0).getRGB());
        }
    }
    ImageIO.write(image2, "JPG", new File("./img/rect.jpg"));
 }   
}
