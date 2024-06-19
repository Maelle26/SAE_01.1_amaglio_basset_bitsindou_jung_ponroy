import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class MainTest2 {

    public static void main(String[] args) {
        
        try {
            BufferedImage image = ImageIO.read(new File("/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/Planete 1.jpg"));
            int maxIterations = 100; // nombre maximum d'itérations
            List<Color> couleurs = PaletteBiome.DEFAULT.biome;
            System.out.println(PaletteBiome.DEFAULT.biome.size());


            KMeans kMeans = new KMeans(maxIterations,PaletteBiome.DEFAULT.biome,new NormeCIELAB());
            
            System.out.println(kMeans.moyenneColor(couleurs));            

            int[][] pixels = new int[image.getWidth()*image.getHeight()][3];

            int o =0;
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int[] rgb = OutilCouleur.getTabColor(image.getRGB(i, j));
                    pixels[o][0]=rgb[0];
                    pixels[o][1]=rgb[1];
                    pixels[o][2]=rgb[2];
                    o++;  
                }
            }
            int[] res = kMeans.algo(pixels);

            int width = image.getWidth();
            int height =  image.getHeight();
    
            BufferedImage image2 = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
            int x=0;
            for(int i=0;i<width;i++){
                for(int j = 0 ; j<height;j++){
                    image2.setRGB(i, j, couleurs.get(res[x]).getRGB());
                    x++;
                }
            }
            ImageIO.write(image2, "JPG", new File("./resultats/clusterTEST.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

         
    }
}
