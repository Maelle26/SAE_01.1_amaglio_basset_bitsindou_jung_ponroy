import java.awt.*;

public class OutilCouleur {

    public static int[] getTabColor(int pixel) {
        Color color = new Color(pixel);
        int[] tab = new int[3];
        tab[0] = color.getRed();
        tab[1] = color.getGreen();
        tab[2] = color.getBlue();
        return tab;
    }
}