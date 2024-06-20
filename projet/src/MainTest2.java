import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

import java.util.Arrays;
import java.util.List;

public class MainTest2 {

    public static void main(String[] args) {
        
        try {
            String path = "/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/ImageTEST.jpg";
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
            int maxIndex = Arrays.stream(res).max().getAsInt();
            for (int i = 0; i <= maxIndex; i++) {
                ClusterToImage.clusterToImageWithLegend(
                    res,
                    "/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/KMEANS/cluster"+i+".png",
                    image.getWidth(),
                    image.getHeight(),
                    couleurs,
                    i,
                    path);

            }
            ClusterToImage.clusterToImage(
                    res,
                    "/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/resultats/KMEANS/cluster.png",
                    image.getWidth(),
                    image.getHeight(),
                    couleurs);
        } catch (Exception e) {
            e.printStackTrace();
        }

         
    }
}
