import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlouProvisoire {

    BufferedImage img;

    public FlouProvisoire(BufferedImage img) {
        this.img = img;
    }
        //enlever ligne noire

    public void flouterImage() throws IOException {
        BufferedImage copie = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        //parcourir toute l'image par groupe de 9x9 pixels
        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {
                int [][] matrice = new int[9][];
                int[] tab;
                int x = 0;
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        int pixel = img.getRGB(i + k, j + l);
                        tab = OutilCouleur.getTabColor(pixel);
                        matrice[x] = tab;
                        x++;
                    }
                }
                int moyenneR = (matrice[0][0] + matrice[1][0] + matrice[2][0] + matrice[3][0] + matrice[4][0] + matrice[5][0] + matrice[6][0] + matrice[7][0] + matrice[8][0]) / 9;
                int moyenneG = (matrice[0][1] + matrice[1][1] + matrice[2][1] + matrice[3][1] + matrice[4][1] + matrice[5][1] + matrice[6][1] + matrice[7][1] + matrice[8][1]) / 9;
                int moyenneB = (matrice[0][2] + matrice[1][2] + matrice[2][2] + matrice[3][2] + matrice[4][2] + matrice[5][2] + matrice[6][2] + matrice[7][2] + matrice[8][2]) / 9;
                int pixel = new Color(moyenneR, moyenneG, moyenneB).getRGB();
                copie.setRGB(i, j, pixel);

            }
        }
        File copieImg = new File("copieFlou.jpg");
        ImageIO.write(copie, "jpg", copieImg);
    }
}
