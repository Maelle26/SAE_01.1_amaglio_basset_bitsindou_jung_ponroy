public class Pixel {
    int x, y;
    boolean traite;

    Pixel(int X, int Y){
        this.x = X;
        this.y = Y; 
        this.traite =false;
    }
    void traiter(){
        this.traite = true;
    }
    @Override
    public boolean equals(Object obj) {
        return (this.x == ((Pixel)obj).x && this.y == ((Pixel)obj).y);
    }
    public double calculDistance(Pixel p){
        return (int) Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
        //√((x_B - x_A)² + (y_B - y_A)²)
    }

    
}
