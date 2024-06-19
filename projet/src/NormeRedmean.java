import java.awt.*;

public class NormeRedmean implements NormeCouleurs{

    @Override
    public double distanceCouleur(Color color1, Color color2) {
        int rN = 1/2 * (color1.getRed() + color2.getRed());
        int deltaR = color1.getRed() - color2.getRed();
        int deltaG = color1.getGreen() - color2.getGreen();
        int deltaB = color1.getBlue() - color2.getBlue();
        return Math.sqrt((2 + rN/256) * deltaR * deltaR + 4 * deltaG * deltaG + (2 + (255 - rN)/256) * deltaB * deltaB);
    }
}
