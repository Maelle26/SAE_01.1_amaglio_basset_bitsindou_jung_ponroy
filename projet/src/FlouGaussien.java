import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FlouGaussien implements InterfaceFlou {

    /**
     * Taille de la matrice de flou
     */
    public int taille;

    /**
     * Ecart type de la formule de Gauss
     */
    public double sigma;

    /**
     * Boolean pour sauvegarder l'image
     */
    private boolean save;

    /**
     * Matrice de flou 3x3
     */
    final int[][] matrice3x3 = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};//sigma = 0.2 //taille = 9

    /**
     * Matrice de flou 5x5
     */
    final int[][] matrice5x5 = {{1, 4, 7, 4, 1}, {4, 16, 26, 16, 4}, {7, 26, 41, 26, 7}, {4, 16, 26, 16, 4}, {1, 4, 7, 4, 1}};// sigma = 0.062 taille = 25

    /**
     * Constructeur de la classe FlouGaussien
     *
     * @param taillematrice taille de la matrice de flou
     * @param sigma         ecart type de la formule de Gauss
     * @param save          boolean pour sauvegarder l'image
     */
    public FlouGaussien(int taillematrice, double sigma, boolean save) {
        this.taille = taillematrice;
        this.sigma = sigma;
        this.save = save;
    }

    /**
     * Applique un flou sur une image
     *
     * @param path chemin de l'image
     * @return image floutée
     * @throws IOException exception
     */
    @Override
    public BufferedImage flou(String path) throws IOException {
        int tailleLargeurMatrice = (int) Math.sqrt(taille);
        System.out.println("TAILLE LARGEUR MATRICE = " + tailleLargeurMatrice);
        BufferedImage img = ImageIO.read(new File(path));
        int pixelNonCalcules = (tailleLargeurMatrice - 1) / 2;
        System.out.println("TAILLE PIXEL NON CALCULe = " + pixelNonCalcules);
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage copie = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //pour chaque pixel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j < pixelNonCalcules || j >= (height - pixelNonCalcules) || i < pixelNonCalcules || (i >= width - pixelNonCalcules)) {
                    copie.setRGB(i, j, img.getRGB(i, j));
                } else {
                    int[][] matrice = new int[taille][];
                    int[] tab;
                    double[] gauss = new double[taille];
                    int x = 0;
                    double sommegauss = 0;
                    //pour chaque pixel de la matrice autour du pixel centre(courrant)
                    for (int k = i - pixelNonCalcules; k < i + pixelNonCalcules + 1; k++) {
                        for (int l = j - pixelNonCalcules; l < j + pixelNonCalcules + 1; l++) {
                            int pixel = img.getRGB(k, l);
                            tab = OutilCouleur.getTabColor(pixel);
                            matrice[x] = tab;//on garde les couleurs du pixel en (k,l)
                            //calcul des distances entre i,j et k,l
                            int distX = i - k;
                            int distY = j - l;
                            //calcul de gauss par rapport aux distances
                            gauss[x] = Gauss(distX, distY, sigma);
                            sommegauss += gauss[x];
                            x++;
                        }
                    }
                    int resR = 0;
                    int resG = 0;
                    int resB = 0;

                    for (int z = 0; z < taille; z++) {//calcul final des couleurs du pixel en fonction des coef calculé par gauss
                        resR += (int) (matrice[z][0] * gauss[z]);
                        resG += (int) (matrice[z][1] * gauss[z]);
                        resB += (int) (matrice[z][2] * gauss[z]);
                    }

                    resR = (int) (resR / sommegauss);
                    resG = (int) (resG / sommegauss);
                    resB = (int) (resB / sommegauss);
                    int pixel = new Color(resR, resG, resB).getRGB();
                    copie.setRGB(i, j, pixel);

                }
            }
        }
        if (save) {
            File copieImg = new File("./projet/resultats/copieFlou.jpg");
            ImageIO.write(copie, "jpg", copieImg);
        }
        return copie;
    }

    /**
     * Formule de Gauss
     *
     * @param x
     * @param y
     * @param sigma
     * @return
     */
    public double Gauss(double x, double y, double sigma) {
        double puissanceE = (Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sigma, 2));
        double division = 2 * Math.PI * (Math.pow(sigma, 2));
        return (1 / division) * Math.exp(-puissanceE);
    }

}
