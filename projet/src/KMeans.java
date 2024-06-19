import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KMeans implements Algo{

    private int maxIterations;

    //private List<List<Pixel>> clusters;
    List<Color> couleurs;
    NormeCouleurs distanceCouleur;
    private int[]  clusters;

    public KMeans(int maxIterations,List<Color> couleurs, NormeCouleurs normeCouleurs) {
        this.maxIterations = maxIterations;
        this.couleurs = couleurs;
        this.distanceCouleur = normeCouleurs;

    }
    public int[] algo(int[][] pixels) {
        //initialisation
        int taillepix = pixels.length;

        boolean end = false;
        int iteration = 0;
        while(!end){
            Palette palette = new Palette(couleurs, distanceCouleur); //comme couleurs change on le reinitialise

            //initialisation des groupes
            clusters = new int[taillepix];

            iteration++;
            System.out.println(iteration);
            ArrayList<ArrayList<Color>> clustersColor = new ArrayList<>();
            for (int i = 0; i< couleurs.size(); i++){
                clustersColor.add(new ArrayList<Color>());
            }
            for (int i =0; i<pixels.length;i++) {
                int[] pixel = pixels[i];
                Color colorPixel= new Color(pixel[0],pixel[1],pixel[2]);
                Color proche = palette.getPlusProche(colorPixel);
                //on mets la bonne valeur
                
                int indexCouleur = couleurs.indexOf(proche);
                clusters[i] = indexCouleur;
                clustersColor.get(indexCouleur).add(colorPixel);
            }
            //on recalcule les centroides
            List<Color> nvCouleurs= new ArrayList<Color>();
            for (List<Color>  list : clustersColor ) {
                nvCouleurs.add(moyenneColor(list));
            }
            //si les anciens centoides = nouveau end = true 
            if(nvCouleurs.equals(couleurs))
                end = true;
            else
                couleurs = nvCouleurs;
            //si iteration = maxIterations end = true
            if(iteration == maxIterations)
                end = true;

        }
        return clusters;
    }
    public Color moyenneColor(List<Color> liste) {
        if(liste.size()==0){
            return null;
        }
        int[] moyen = {0,0,0};
        for (Color color : liste) {
            int[] tab = OutilCouleur.getTabColor(color.getRGB());
            moyen[0] += tab[0];
            moyen[1] += tab[1];
            moyen[2] += tab[2];   
        }
        int tailleliste = liste.size();
        
        return new Color(moyen[0]/tailleliste,moyen[1]/tailleliste,moyen[2]/tailleliste);
    }

}
