import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBSCAN implements Algo{

    int eps; // en taille pixel
    int minPts;
    Algo algoClusterisation;//algorithme pour clusteriser les pixels en couleurs
    int[] tailleImage;
    List<Pixel> listePixelCluster;//liste des pixels du cluster de départ
    int[] clusters ;//tableau d'affectation des pixels aux clusters qui est retourné


    public DBSCAN(int eps , int minPts, List<Color> couleurs,Algo algo,int[] tailleImage) {
        this.eps = eps;
        this.minPts = minPts;
        this.algoClusterisation=algo;
        this.tailleImage = tailleImage;
    }
    /**
     * methode qui permet de clusteriser les pixels en région
     */
    //un pixel marqué comme noise n'est pas noté dans le res
    public int[] algo(int[][] pixels) { 
        //lance Kmeans
        int[] res = algoClusterisation.algo(pixels); //taleau d'affectation des pixels aux clusters 
        //initialiser les données
        ArrayList<ArrayList<Pixel>> listePixels = new ArrayList<ArrayList<Pixel>>();
        
        int maxIndex = Arrays.stream(res).max().getAsInt(); // Trouver l'index maximum dans res pour l'initialissation
        for (int k = 0; k <= maxIndex; k++) {
            listePixels.add(new ArrayList<Pixel>());
        }

        int x=0;
        int y=0;
        for (int i : res) {
            Pixel tmpPixel = new Pixel(x, y);
            listePixels.get(i).add(tmpPixel);
            x++;
            if(x>=tailleImage[0]){
                x=0;
                y++;
            }
            System.out.println("("+x+";"+y+")");
        }
        //pour le premier cluster : clusteriser:
        clusters = new int[res.length];
        for (int i = 0; i < clusters.length; i++) {
            clusters[i] = -1;// -1 pour les noise points ils ne seront pas comptés
        }
        
        listePixelCluster = listePixels.get(2);
        int currentCluster = -1; 
        for (Pixel pixel : listePixelCluster) {
            if(!pixel.traite){
            pixel.traiter();
            List<Pixel> voisinage = regionQuery(pixel);

            if(voisinage.size()>=minPts){//si le neud est central
                currentCluster++;
                expandCluster(pixel,voisinage,currentCluster);
            }
            //on ne fait rien pour les noise points
        }

        }
        //on clusterise les autres clusters (a faire)
        return this.clusters;
    }
    /**
     * methode qui permet de clusteriser les pixels avec leurs voisins
     * @param pixel le pixel autour duquel on clusterise
     * @param voisinage les pixels voisins de pixel
     * @param cluster le cluster auquel appartient pixel
     */
    void expandCluster(Pixel pixel ,List<Pixel> voisinage,int cluster){
        this.clusters[pixel.y * tailleImage[0] + pixel.x] = cluster;
        for (Pixel p : voisinage) {
            if(!p.traite){
                p.traiter();
                List<Pixel> voisinage2 = regionQuery(p);
                if(voisinage2.size()>=minPts){
                    expandCluster(p,voisinage2,cluster);
                }
            }
        }
    }
    

    
    /**
     * methode qui retourne les pixels qui sont dans le voisinage de p selon esp
     * @param p Pixel
     * @return List<Pixel> les pixels voisins de p
     */
    List<Pixel> regionQuery(Pixel p){
        List<Pixel> res = new ArrayList<Pixel>();
        for (Pixel pixel : listePixelCluster) {
            if(pixel!=p&&p.calculDistance(pixel)<eps)
            res.add(pixel);
        }
        return res;
    }   
    

}
