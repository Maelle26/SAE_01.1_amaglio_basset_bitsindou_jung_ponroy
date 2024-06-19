import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FlouGaussien implements InterfaceFlou{
    public int taille;
    public double sigma;
    //flitres 
    final int[][] matrice3x3 = {{1,2,1},{2,4,2},{1,2,1}};//sigma = 0.2 //taille = 9
    final int[][] matrice5x5 = {{1,4,7,4,1},{4,16,26,16,4},{7,26,41,26,7},{4,16,26,16,4},{1,4,7,4,1}};// sigma = 0.062 taille = 25
    //constructeur
    public FlouGaussien(int taillematrice,double sigma){
        this.taille = taillematrice;
        this.sigma = sigma;

    }

    //tableau
        //calcul de gauss pour chaque pixel de la matrice 
        //Gauss(x,y,sigma))
        //le mettre dans le tableau

        //faire la "moyenne" de chaque couleurs de chaque pixel en prennant en compte le gaussien 
        //moy = Gauss(x,y,sigma)*pixel(x,y) + Gauss(x,y,sigma)*pixel(x,y) + Gauss(x,y,sigma)*pixel(x,y).../taillematrice(25 pour 5)
        // pixel 1                      pixel2                      pixel3
        
        //parcourir toute l'image par groupe de nxn pixels


    //Méthode pour appliquer un flou Gaussien
    @Override
    public ImageIO flou(String path) throws IOException {
        int tailleLargeurMatrice = (int)Math.sqrt(taille);
        System.out.println("TAILLE LARGEUR MATRICE = "+tailleLargeurMatrice);
        BufferedImage img = ImageIO.read(new File(path));

        int width = img.getWidth();
        int height =  img.getHeight();

        BufferedImage copie = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //pour chaque pixel 
        for (int i = 0; i < width ; i++) { 
            for (int j = 0; j < height ; j++) {
                if(j==0||j==height-1||i==0||i==width-1){
                    copie.setRGB(i, j, img.getRGB(i, j));
                }else{
                    int [][] matrice = new int[taille][];
                    int[] tab;
                    double[] gauss = new double[taille];
                    int x = 0;
                    double sommegauss = 0;
                    //pour chaque pixel de la matrice autour du pixel centre(courrant)
                    for (int k = i-1; k < i+tailleLargeurMatrice-1; k++) {
                        for (int l = j-1; l < j+tailleLargeurMatrice-1; l++) {
                            int pixel = img.getRGB(k,l);
                            tab = OutilCouleur.getTabColor(pixel);
                            matrice[x] = tab;//on garde les couleurs du pixel en (k,l)
                            //calcul des distances entre i,j et k,l
                            int distX = i-k;
                            int distY =j-l;
                            //calcul de gauss par rapport aux distances
                            gauss[x] = Gauss(distX, distY, sigma);
                            sommegauss += gauss[x];
                            
                            x++;
                        }
                    }
                    int resR = 0;
                    int resG = 0;
                    int resB = 0;
                    
                    for(int z=0;z<taille;z++){//calcul final des couleurs du pixel en fonction des coef calculé par gauss
                        resR += matrice[z][0]*gauss[z];
                        resG += matrice[z][1]*gauss[z];
                        resB += matrice[z][2]*gauss[z];
                    }

                    resR = (int) (resR / sommegauss);
                    resG = (int) (resG / sommegauss);
                    resB = (int) (resB / sommegauss);
                    int pixel = new Color(resR, resG, resB).getRGB();
                    copie.setRGB(i, j, pixel);

                }
            }
        }
                
        File copieImg = new File("copieFlouGauss.jpg");
        ImageIO.write(copie, "jpg", copieImg);
        return null;
    }

    //faire return
    //faire pour toutes le matrices

    //Formule de Gauss
    public double Gauss(double x ,double y, double sigma){
        double puissanceE = (Math.pow(x,2)+Math.pow(y,2))/(2*Math.pow(sigma,2));
        double division = 2*Math.PI*(Math.pow(sigma, 2));
        return (1/division)*Math.exp(-puissanceE);
    }
    
}
