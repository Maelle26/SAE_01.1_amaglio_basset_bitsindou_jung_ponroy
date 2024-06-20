public class Pixel {
    int x, y,id;
    boolean traite;
    /**
     * constructeur de Pixel
     * @param X coordonnee x du pixel
     * @param Y coordonnee y du pixel
     * @param id id du pixel dans le tableau reultat (y*largeurImage +x)
     */
    Pixel(int X, int Y,int id){
        this.x = X;
        this.y = Y; 
        this.id = id;
        this.traite =false;
    }
    
    /**
     * passe traite à true
     */
    void traiter(){
        this.traite = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (this.x == ((Pixel)obj).x && this.y == ((Pixel)obj).y);
    }

    /**
     * calcul la distance entre this et le pixel p
     * @param p pixel a partir duquel on calcul la distance
     * @return
     */
    public double calculDistance(Pixel p){
        return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
        //√((x_B - x_A)² + (y_B - y_A)²)
    }
    /**
     * toString
     */
    @Override
    public String toString() {
        return "("+this.x +";"+this.y+" )";
    }

    
}
