import java.io.IOException;
import java.awt.image.BufferedImage;


interface InterfaceFlou {

    /**
     * Applique un flou sur une image
     *
     * @param path chemin de l'image
     * @return image floutée
     */
    public BufferedImage flou(String path) throws IOException;
}