import java.awt.Color;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ClusterToImage {
    static void clusterToImage(int[] res, String path,int width ,int height,List<Color> couleurs) throws IOException{
    
        BufferedImage image2 = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
        int x=0;
        for(int i=0;i<width;i++){
            for(int j = 0 ; j<height;j++){
                if(res[x]!=-1)//si le pixel n'est pas un bruit
                image2.setRGB(i, j, couleurs.get(res[x]).getRGB());
                x++;
            }
        }
        ImageIO.write(image2, "JPG", new File(path));
    }
    static void clusterToImage(int[] res, String path,int width ,int height,List<Color> couleurs,int clusterChoisi,String pathImageDepart) throws IOException{
        BufferedImage image2 = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
        appliquerFond(image2,pathImageDepart);
        int x=0;
        for(int i=0;i<width;i++){
            for(int j = 0 ; j<height;j++){
                if(res[x]==clusterChoisi)
                    image2.setRGB(i, j, couleurs.get(res[x]).getRGB());
                
                x++;
            }
        }
        ImageIO.write(image2, "JPG", new File(path));
    }
    static void appliquerFond(BufferedImage image2,String path) throws IOException{
        BufferedImage image1 = ImageIO.read(new File(path)); 
        //pour chaque pixel
        for(int i=0;i<image1.getWidth();i++){
            for(int j = 0 ; j<image1.getHeight();j++){
                int[] tab = OutilCouleur.getTabColor(image1.getRGB(i, j));
                int nouveauR = (int) Math.round(tab[0] + 0.75*(255-tab[0]));
                int nouveauG = (int) Math.round(tab[1] + 0.75*(255-tab[1]));
                int nouveauB = (int) Math.round(tab[2] + 0.75*(255-tab[2]));
                
                Color nouvelleCouleur = new Color(nouveauR,nouveauG, nouveauB);
                //on ecrit le nouoveau pixel
                image2.setRGB(i, j, nouvelleCouleur.getRGB());
            }
        }        

    }
    
}
