import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Algo {

    /*
    public static int[][] getPixelData(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] data = new int[width * height][3];

        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                data[index][0] = color.getRed();
                data[index][1] = color.getGreen();
                data[index][2] = color.getBlue();
                index++;
            }
        }
        return data;
    }

    public static BufferedImage createClusteredImage(BufferedImage original, int[][] clusteredPixels) {
        int width = original.getWidth();
        int height = original.getHeight();
        BufferedImage clusteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] rgb = clusteredPixels[index++];
                Color color = new Color(rgb[0], rgb[1], rgb[2]);
                clusteredImage.setRGB(x, y, color.getRGB());
            }
        }
        return clusteredImage;
    }

     */




    public abstract void algo(List<Color> pixels);

}
