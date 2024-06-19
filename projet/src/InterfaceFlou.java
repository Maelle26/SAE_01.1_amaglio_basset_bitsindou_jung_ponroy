import java.io.IOException;
import java.awt.image.BufferedImage;


interface InterfaceFlou{

    //Méthode pour appliquer un flou (Gaussien ou par moyenne)
    public BufferedImage flou(String path) throws IOException ;
}