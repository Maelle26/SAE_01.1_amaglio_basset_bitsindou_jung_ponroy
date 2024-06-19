import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlouMoyenne implements InterfaceFlou {

    int taille;

    public FlouMoyenne(int taillematrice) {        
        this.taille = taillematrice;
    }
        //enlever ligne noire

    public BufferedImage flou(String path) throws IOException {
        int tailleLargeurMatrice = (int)Math.sqrt(taille);
        BufferedImage img = ImageIO.read(new File(path));

        int width = img.getWidth();
        int height =  img.getHeight();
        int pixelNonCalcules= (tailleLargeurMatrice-1)/2;
        BufferedImage copie = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //parcourir toute l'image par groupe de taille x taille pixels
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(j<pixelNonCalcules||j>=(height-pixelNonCalcules)||i<pixelNonCalcules||(i>=width-pixelNonCalcules)){
                    copie.setRGB(i, j, img.getRGB(i, j));
                }else{
                    int [][] matrice = new int[taille][];
                    int[] tab;
                    int x = 0;
                    //pour chaque pixel de la matrice autour du pixel centre(courrant)
                    for (int k = i-pixelNonCalcules; k < i+pixelNonCalcules+1; k++) {
                        for (int l = j-pixelNonCalcules; l < j+pixelNonCalcules+1; l++) {
                            int pixel = img.getRGB(k, l);
                            tab = OutilCouleur.getTabColor(pixel);
                            matrice[x] = tab;
                            x++;
                        }
                    }

                    int resR = 0;
                    int resG = 0;
                    int resB = 0;
                    
                    for(int z=0;z<9;z++){//calcul des moyennes
                        resR += matrice[z][0];
                        resG += matrice[z][1];
                        resB += matrice[z][2];
                    }

                    resR = (int) (resR / 9);
                    resG = (int) (resG / 9);
                    resB = (int) (resB / 9);
            
                    int pixel = new Color(resR, resG, resB).getRGB();
                    copie.setRGB(i, j, pixel);
                }
            }
        }
        File copieImg = new File("/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/copieFlou.jpg");
        ImageIO.write(copie, "jpg", copieImg);
        return copie;
    }
}
