import java.io.IOException;

public class MainTest {
    public static void main(String[] args) throws IOException {
        FlouGaussien flouGaussien = new FlouGaussien(9,0.062);
        double h = (0.062);
        System.out.println(h);
        System.out.println(flouGaussien.Gauss(0, 0, h));
        flouGaussien.flou("/home/celie/Documents/s4/MethodeOptimisation/SAE_01.1_amaglio_bitsindou_ponroy/projet/img/ImageTEST.jpg");
    }
}
