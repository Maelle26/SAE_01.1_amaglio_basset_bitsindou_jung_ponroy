import java.awt.*;

public class NormeRedmean implements NormeCouleurs {

    /**
     * Calcul de la distance entre deux couleurs
     *
     * @param color1 couleur 1
     * @param color2 couleur 2
     * @return distance entre les deux couleurs (double)
     */
    @Override
    public double distanceCouleur(Color color1, Color color2) {
        int rN = 1 / 2 * (color1.getRed() + color2.getRed());
        int deltaR = color1.getRed() - color2.getRed();
        int deltaG = color1.getGreen() - color2.getGreen();
        int deltaB = color1.getBlue() - color2.getBlue();
        return Math.sqrt((2 + rN / 256) * deltaR * deltaR + 4 * deltaG * deltaG + (2 + (255 - rN) / 256) * deltaB * deltaB);
    }
}
