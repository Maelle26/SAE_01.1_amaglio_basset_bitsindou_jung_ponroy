import java.awt.*;
import java.util.List;

public class Palette {
    List<Color> colors;
    NormeCouleurs distanceCouleur;

    public Palette(List<Color> colors, NormeCouleurs distanceCouleur) {
        this.colors = colors;
        this.distanceCouleur = distanceCouleur;
    }

    public Color getPlusProche(Color c){
        double distance = Double.MAX_VALUE;
        Color plusProche = null;
        for (Color color : colors) {
            double d = distanceCouleur.distanceCouleur(c, color);
            if (d < distance){
                distance = d;
                plusProche = color;
            }
        }
        return plusProche;
    }
}
