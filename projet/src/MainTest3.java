import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest3 {

    public static void main(String[] args) {
        
        try {
            String path = "img/SSUUsmall.png";
            BufferedImage image = ImageIO.read(new File(path));
            int maxIterations = 100; // nombre maximum d'itérations
            //List<Color> couleurs = PaletteBiome.DEFAULT.biome;
            List<Color> couleurs = PaletteBiome.creerPaletteDeCouleurs();
            List<Color> couleur2 = new ArrayList<Color>();
            couleur2.add(Color.RED);
            couleur2.add(Color.WHITE);
            //couleur2.add(Color.GREEN);
            KMeans kMeans = new KMeans(maxIterations,couleur2,new NormeCIELAB());
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
            DBSCAN dbscan = new DBSCAN(1, 2, couleurs, kMeans, new int[]{image.getWidth(), image.getHeight()});

            int[] res = dbscan.algo(pixels);
            int maxIndex = Arrays.stream(res).max().getAsInt();
            for (int i = 0; i <= maxIndex; i++) {
                ClusterToImage.clusterToImage(
                    res,
                    "resultats/DBSCAN/cluster9"+i+".png",
                    image.getWidth(),
                    image.getHeight(),
                    couleurs,
                    i,
                    path);

            }
            ClusterToImage.clusterToImage(
                    res,
                    "resultats/DBSCAN/cluster9.png",
                    image.getWidth(),
                    image.getHeight(),
                    couleurs);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

         
    }
}
