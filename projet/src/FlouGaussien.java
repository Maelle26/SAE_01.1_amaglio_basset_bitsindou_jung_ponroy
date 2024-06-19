import javax.imageio.ImageIO;

public class FlouGaussien implements InterfaceFlou{
    //flitres 
    final int[][] matrice3x3 = {{1,2,1},{2,4,2},{1,2,1}};//sigma = 0.2
    final int[][] matrice5x5 = {{1,4,7,4,1},{4,16,26,16,4},{7,26,41,26,7},{4,16,26,16,4},{1,4,7,4,1}};// sigma = 0.062
    //constructeur
    public FlouGaussien(){
        
    }

    //Méthode pour appliquer un flou Gaussien
    @Override
    public ImageIO flou(String path) {
        //tableau
        //calcul de gauss pour chaque pixel
        //Gauss(x,y,sigma))
        //le mettre dans le tableau

        //faire la "moyenne" de chaque couleurs de chaque pixel en prennant en compte le gaussien 
        //moy = Gauss(x,y,sigma)*pixel(x,y) + Gauss(x,y,sigma)*pixel(x,y) + Gauss(x,y,sigma)*pixel(x,y).../taillematrice(25 pour 5)
        // pixel 1                      pixel2                      pixel3

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'flou'");
    }

    //Formule de Gauss
    public double Gauss(double x ,double y, double sigma){
        double puissanceE = (Math.pow(x,2)+Math.pow(y,2))/(2*Math.pow(sigma,2));
        double division = 2*Math.PI*(Math.pow(sigma, 2));
        return (1/division)*Math.exp(-puissanceE);
    }
    
}
