import java.awt.*;

public interface NormeCouleurs {

    /**
     * Calcul de la distance entre deux couleurs
     *
     * @param c1 couleur 1
     * @param c2 couleur 2
     * @return distance entre les deux couleurs (double)
     */
    double distanceCouleur(Color c1, Color c2);
}
