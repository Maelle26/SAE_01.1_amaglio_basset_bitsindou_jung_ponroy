import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans{

    private int k;
    private int maxIterations;

    //private int[][] data;
    private List<List<Position>> centroids;



    public KMeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.centroids = new ArrayList<List<Position>>();
    }


    public void algo(List<Color> couleurs, NormeCouleurs distanceCouleur, String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        centroids = new ArrayList<List<Position>>();
        k = couleurs.size();
        for (int i = 0; i< k; i++){
            centroids.add(new ArrayList<Position>());
        }
        boolean end = false;
        Palette palette = new Palette(couleurs, distanceCouleur);
        while(!end){
            for(int i = 0; i < image
        }
    }



    /*
    private void initializeCentroids() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int randomIndex = random.nextInt(data.length);
            centroids[i] = data[randomIndex];
        }
    }

    private int closestCentroid(int[] pixel) {
        int closestIndex = 0;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            double distance = euclideanDistance(pixel, centroids[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    private void recomputeCentroids(int[] labels) {
        int[][] newCentroids = new int[k][3];
        int[] counts = new int[k];

        for (int i = 0; i < labels.length; i++) {
            int cluster = labels[i];
            newCentroids[cluster][0] += data[i][0];
            newCentroids[cluster][1] += data[i][1];
            newCentroids[cluster][2] += data[i][2];
            counts[cluster]++;
        }

        for (int i = 0; i < k; i++) {
            if (counts[i] != 0) {
                centroids[i][0] = newCentroids[i][0] / counts[i];
                centroids[i][1] = newCentroids[i][1] / counts[i];
                centroids[i][2] = newCentroids[i][2] / counts[i];
            }
        }
    }

    private double euclideanDistance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2) + Math.pow(a[2] - b[2], 2));
    }

    public int[][] run() {
        initializeCentroids();
        int[] labels = new int[data.length];

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Assign clusters
            for (int i = 0; i < data.length; i++) {
                labels[i] = closestCentroid(data[i]);
            }

            // Recompute centroids
            recomputeCentroids(labels);

            // Convergence check can be added here
        }

        // Assign final cluster colors to pixels
        int[][] clusteredPixels = new int[data.length][3];
        for (int i = 0; i < data.length; i++) {
            clusteredPixels[i] = centroids[labels[i]];
        }
        return clusteredPixels;
    }

     */



}
