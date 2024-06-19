import javax.imageio.ImageIO;
import java.io.IOException;

interface InterfaceFlou{

    //Méthode pour appliquer un flou (Gaussien ou par moyenne)
    public void flou(String path) throws IOException;
}