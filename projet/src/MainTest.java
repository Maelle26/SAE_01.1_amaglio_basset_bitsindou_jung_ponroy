public class MainTest {
    public static void main(String[] args) {
        FlouGaussien flouGaussien = new FlouGaussien();
        double h = (0.062);
        System.out.println(h);
        System.out.println(flouGaussien.Gauss(0, 0, h));
    }
}
