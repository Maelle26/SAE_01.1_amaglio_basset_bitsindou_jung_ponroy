import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.List;

public class MainTest2 {

    public static void main(String[] args) {
        
        try {
            String path = "/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/Planete 5.jpeg";
            BufferedImage image = ImageIO.read(new File(path));
            int maxIterations = 100; // nombre maximum d'itérations
            List<Color> couleurs = PaletteBiome.DEFAULT.biome;
            KMeans kMeans = new KMeans(maxIterations,PaletteBiome.DEFAULT.biome,new NormeCIELAB());
            int[][] pixels = new int[image.getWidth()*image.getHeight()][3];
            int o = 0;
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
            ClusterToImage.clusterToImage(res,"/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/clusterTEST6.png",image.getWidth(),image.getHeight(),couleurs);
            ClusterToImage.clusterToImage(res,"/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/clusterTEST61.png",image.getWidth(),image.getHeight(),couleurs,1,path);
            ClusterToImage.clusterToImage(res,"/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/clusterTEST62.png",image.getWidth(),image.getHeight(),couleurs,9,path);

        } catch (Exception e) {
            e.printStackTrace();
        }

         
    }
}