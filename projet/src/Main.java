import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.module.Configuration;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Variables

        InterfaceFlou flou = new FlouMoyenne(49, true);
        Scanner scanner = new Scanner(System.in);
        boolean flouDefault = true;
        String imagePath = "./projet/img/ImageTEST.jpg";
        boolean kmeansDefault = true;
        int maxIterations = 100;
        List<Color> couleurs = PaletteBiome.DEFAULT.biome;
        Algo algo = new KMeans(maxIterations, couleurs, new NormeCIELAB());
        boolean dbscanDefault = true;
        int EPS = 10;
        int MINPTS = 4;

        // Default configuration

        if (args.length > 0) {
            imagePath = args[0];
            if (args.length > 1) {
                if (args[1].equals("gaussien")) {
                    System.out.println("Configuration par défaut du Flou Gaussien ? (true/false)");
                    flouDefault = scanner.nextBoolean();
                    if (flouDefault) {
                        flou = new FlouGaussien(49, 0.062, false);
                    } else {
                        System.out.println("Entrez la taille de la matrice pour le flou Gaussien : ");
                        int taille = scanner.nextInt();
                        System.out.println("Entrez la valeur de sigma : ");
                        double sigma = scanner.nextDouble();
                        System.out.println("Est-ce qu'on sauvegarde l'image intermédiaire ? (true/false)");
                        boolean save = scanner.nextBoolean();
                        flou = new FlouGaussien(taille, sigma, save);
                    }
                } else {
                    System.out.println("Configuration par défaut du Flou Moyenne ? (true/false)");
                    flouDefault = scanner.nextBoolean();
                    if (flouDefault) {
                        flou = new FlouMoyenne(49, false);
                    } else {
                        System.out.println("Entrez la taille de la matrice pour le flou Moyenne : ");
                        int taille = scanner.nextInt();
                        System.out.println("Est-ce qu'on sauvegarde l'image intermédiaire ? (true/false)");
                        boolean save = scanner.nextBoolean();
                        flou = new FlouMoyenne(taille, save);
                    }
                }
                if (args.length > 2) {
                    if (args[2].equals("kmeans")) {
                        System.out.println("Configuration par défaut du KMeans ? (true/false)");
                        kmeansDefault = scanner.nextBoolean();
                        if (!kmeansDefault) {
                            System.out.println("Entrez le nombre maximum d'itérations : ");
                            maxIterations = scanner.nextInt();
                            algo = new KMeans(maxIterations, couleurs, new NormeCIELAB());
                        }
                    } else if (args[2].equals("dbscan")) {
                        System.out.println("Configuration par défaut du DBSCAN ? (true/false)");
                        dbscanDefault = scanner.nextBoolean();
                        if (!dbscanDefault) {
                            System.out.println("Entrez le nombre maximum d'itérations : ");
                            maxIterations = scanner.nextInt();
                            couleurs = PaletteBiome.creerPaletteDeCouleurs();
                            System.out.println("Entrez la valeur de EPS : ");
                            EPS = scanner.nextInt();
                            System.out.println("Entrez la valeur de MINPTS : ");
                            MINPTS = scanner.nextInt();
                            algo = new KMeans(maxIterations, couleurs, new NormeCIELAB());
                        }
                    }
                }
            }
        }

        // Calcul du flou
        BufferedImage image = flou.flou(imagePath);

        // Traitement de l'image
        int[][] pixels = new int[image.getWidth() * image.getHeight()][3];
        int o = 0;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] rgb = OutilCouleur.getTabColor(image.getRGB(i, j));
                pixels[o][0] = rgb[0];
                pixels[o][1] = rgb[1];
                pixels[o][2] = rgb[2];
                o++;
            }
        }

        // Traitement de KMeans

        if (args.length > 2) {
            if (args[2].equals("kmeans")) {
                int[] res = algo.algo(pixels);
                int maxIndex = Arrays.stream(res).max().getAsInt();
                for (int i = 0; i <= maxIndex; i++) {
                    ClusterToImage.clusterToImage(
                            res,
                            "./projet/resultats/KMEANS/cluster" + i + ".png",
                            image.getWidth(),
                            image.getHeight(),
                            couleurs,
                            i,
                            "./projet/resultats/copieFlou.jpg");

                }
                ClusterToImage.clusterToImage(
                        res,
                        "./projet/resultats/KMEANS/cluster.png",
                        image.getWidth(),
                        image.getHeight(),
                        couleurs);
            }

            // Traitement de DBSCAN

            if (args[2].equals("dbscan")) {
                DBSCAN dbscan = new DBSCAN(EPS, MINPTS, couleurs, algo, new int[]{image.getWidth(), image.getHeight()});
                int[] res = dbscan.algo(pixels);
                int maxIndex = Arrays.stream(res).max().getAsInt();
                for (int i = 0; i <= maxIndex; i++) {
                    ClusterToImage.clusterToImage(
                            res,
                            "./projet/resultats/clusterTEST9" + i + ".png",
                            image.getWidth(),
                            image.getHeight(),
                            couleurs,
                            i,
                            "./projet/resultats/copieFlou.jpg");
                }
            }
        } else {
            int[] res = algo.algo(pixels);
                int maxIndex = Arrays.stream(res).max().getAsInt();
                for (int i = 0; i <= maxIndex; i++) {
                    ClusterToImage.clusterToImage(
                            res,
                            "./projet/resultats/KMEANS/cluster" + i + ".png",
                            image.getWidth(),
                            image.getHeight(),
                            couleurs,
                            i,
                            "./projet/resultats/copieFlou.jpg");

                }
                ClusterToImage.clusterToImage(
                        res,
                        "./projet/resultats/KMEANS/cluster.png",
                        image.getWidth(),
                        image.getHeight(),
                        couleurs);
        }

    }
}
