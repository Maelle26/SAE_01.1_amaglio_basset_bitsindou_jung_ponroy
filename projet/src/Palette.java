import java.awt.*;
import java.util.List;

public class Palette {

    /**
     * Liste des couleurs de la palette
     */
    List<Color> colors;

    /**
     * Norme de distance entre deux couleurs
     */
    NormeCouleurs distanceCouleur;

    /**
     * Constructeur de la classe Palette
     *
     * @param colors          liste des couleurs
     * @param distanceCouleur norme de distance entre deux couleurs
     */
    public Palette(List<Color> colors, NormeCouleurs distanceCouleur) {
        this.colors = colors;
        this.distanceCouleur = distanceCouleur;
    }

    /**
     * Retourne la couleur la plus proche de la couleur c
     *
     * @param c couleur
     * @return couleur la plus proche
     */
    public Color getPlusProche(Color c) {
        double distance = Double.MAX_VALUE;
        Color plusProche = null;
        for (Color color : colors) {
            double d = distanceCouleur.distanceCouleur(c, color);
            if (d < distance) {
                distance = d;
                plusProche = color;
            }
        }
        return plusProche;
    }
}
