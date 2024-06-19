import java.io.IOException;

import javax.imageio.ImageIO;

interface InterfaceFlou{

    //Méthode pour appliquer un flou (Gaussien ou par moyenne)
    public ImageIO flou(String path) throws IOException ;
}