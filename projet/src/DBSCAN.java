import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBSCAN implements Algo{

    double eps; // en taille pixel
    int minPts; // nombre de points minimum pour que un pixel soit central
    Algo algoClusterisation;//algorithme pour clusteriser les pixels en couleurs
    int[] tailleImage; // taille de l'image
    List<Pixel> listePixelCluster;//liste des pixels du cluster de départ
    int[] clusters ;//tableau d'affectation des pixels aux clusters qui est retourné

    /**
     * Constructeur de la classe DBSCAN
     * @param eps
     * @param minPts
     * @param couleurs
     * @param algo algorithme pour clusteriser les pixels en couleurs
     * @param tailleImage
     */
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

        int x = 0;
        int y = 0;
        int j = 0; //indice du pixel dans res
        for (int numroCluster : res) {
            Pixel tmpPixel = new Pixel(x, y,j);
            listePixels.get(numroCluster).add(tmpPixel);
            x++;
            if (x >= tailleImage[0]) {
                x = 0;
                y++;
            }
            j++;
        }


        //pour le premier cluster : clusteriser:
        clusters = new int[res.length];//le retour a une taille du nombre de pixels de l'image
        Arrays.fill(clusters, -1); // -1 pour les noise points, ils ne seront pas comptés

        // Clusteriser le premier cluster
        listePixelCluster = listePixels.get(0); // Cluster initial (liste des pixels du cluster 0)
        int currentCluster = -1;
        for (Pixel pixel : listePixelCluster) {//pour chaque pixel du cluster
            if (!pixel.traite) {//si le pixel n'est pas traité
                pixel.traiter();
                List<Pixel> voisinage = regionQuery(pixel);//on récupère les voisins du pixel
                if (voisinage.size() >= minPts) { // si le noeud est central
                    currentCluster++;//on crée un nouveau cluster
                    expandCluster(pixel, voisinage, currentCluster);//on clusterise les pixels autour du pixel
                }
                // on ne fait rien pour les noise points
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
    void expandCluster(Pixel pixel ,List<Pixel> voisinage,int idcluster){
        this.clusters[pixel.id] = idcluster;
        for (Pixel p : voisinage) {
            if(!p.traite){
                p.traiter();
                List<Pixel> voisinage2 = regionQuery(p);
                if(voisinage2.size()>=minPts){//si le cluster est central
                    expandCluster(p,voisinage2,idcluster);
                }
            }
            this.clusters[p.id] = idcluster;
        }
    }
    

    
    /**
     * methode qui retourne les pixels qui sont dans le voisinage de p selon esp
     * @param p Pixel
     * @return List<Pixel> les pixels voisins de p
     */
    List<Pixel> regionQuery(Pixel p){
        List<Pixel> res = new ArrayList<Pixel>();
        for (Pixel pixel : listePixelCluster) {//pour chaque pixel du cluster , on regarde si il est dans le voisinage de p
            if(pixel!=p && pixel.calculDistance(p)<=eps)
            res.add(pixel);
        }
        return res;
    }   
    

}