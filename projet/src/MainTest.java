import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        
        FlouGaussien flouGaussien = new FlouGaussien(49,0.062, true);
        double h = (0.062);
        System.out.println(h);
        System.out.println(flouGaussien.Gauss(0, 0, h));
        flouGaussien.flou("/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/ImageTEST.jpg");
        
        FlouMoyenne flouProvisoire = new FlouMoyenne(49, true);
        flouProvisoire.flou("/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/ImageTEST.jpg");
        
    }
}
