import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class flouMoyenne implements InterfaceFlou {

    BufferedImage img;


    public flouMoyenne() {
    }

    public void flou(String path) throws IOException {
        BufferedImage copie = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);


        File copieImg = new File("copieFlou.jpg");
        ImageIO.write(copie, "jpg", copieImg);
    }

}