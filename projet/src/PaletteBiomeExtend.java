import java.awt.Color;
import java.util.List;

public class PaletteBiomeExtend {
    List<Color> biome;
    
    // Définition des couleurs des biomes
    private static final Color TUNDRA = new Color(71, 70, 61);
    private static final Color TAIGA = new Color(43, 50, 35);
    private static final Color FORET_TEMPEREE = new Color(59, 66, 43);
    private static final Color FORET_TROPICALE = new Color(46, 64, 34);
    private static final Color SAVANE = new Color(84, 106, 70);
    private static final Color PRAIRIE = new Color(104, 95, 82);
    private static final Color DESERT = new Color(152, 140, 120);
    private static final Color GLACIER = new Color(200, 200, 200);
    private static final Color EAU_PEU_PROFONDE = new Color(49, 83, 100);
    private static final Color EAU_PROFONDE = new Color(12, 31, 47);
    private static final Color PLAGE = new Color(240, 230, 140);
    private static final Color MANGROVE = new Color(34, 139, 34);
    private static final Color MARAIS = new Color(85, 107, 47);
    private static final Color FORET_TROPICALE_HUMIDE = new Color(60, 179, 113);
    private static final Color FORET_BOREALE = new Color(173, 216, 230);
    private static final Color BOIS = new Color(165, 42, 42);
    private static final Color CULTURE = new Color(255, 255, 0);
    private static final Color VILLE = new Color(128, 0, 0);

    // Palette par défaut avec toutes les couleurs définies
    final static PaletteBiomeExtend DEFAULT_EXTEND = new PaletteBiomeExtend(List.of(
            TUNDRA,
            TAIGA,
            FORET_TEMPEREE,
            FORET_TROPICALE,
            SAVANE,
            PRAIRIE,
            DESERT,
            GLACIER,
            EAU_PEU_PROFONDE,
            EAU_PROFONDE,
            PLAGE,
            MANGROVE,
            MARAIS,
            FORET_TROPICALE_HUMIDE,
            FORET_BOREALE,
            BOIS,
            CULTURE,
            VILLE
    ));
    public static String couleurToName(Color c) {
        if (c.equals(TUNDRA)) {
            return "TUNDRA";
        } else if (c.equals(TAIGA)) {
            return "TAIGA";
        } else if (c.equals(FORET_TEMPEREE)) {
            return "FORET TEMPEREE";
        } else if (c.equals(FORET_TROPICALE)) {
            return "FORET TROPICALE";
        } else if (c.equals(SAVANE)) {
            return "SAVANE";
        } else if (c.equals(PRAIRIE)) {
            return "PRAIRIE";
        } else if (c.equals(DESERT)) {
            return "DESERT";
        } else if (c.equals(GLACIER)) {
            return "GLACIER";
        } else if (c.equals(EAU_PEU_PROFONDE)) {
            return "EAU PEU PROFONDE";
        } else if (c.equals(EAU_PROFONDE)) {
            return "EAU PROFONDE";
        } else if (c.equals(PLAGE)) {
            return "PLAGE";
        } else if (c.equals(MANGROVE)) {
            return "MANGROVE";
        } else if (c.equals(MARAIS)) {
            return "MARAIS";
        } else if (c.equals(FORET_TROPICALE_HUMIDE)) {
            return "FORET TROPICALE HUMIDE";
        } else if (c.equals(FORET_BOREALE)) {
            return "FORET BOREALE";
        } else if (c.equals(BOIS)) {
            return "BOIS";
        } else if (c.equals(CULTURE)) {
            return "CULTURE";
        } else if (c.equals(VILLE)) {
            return "VILLE";
        } else {
            return "";
        }
    }


    public PaletteBiomeExtend(List<Color> biomes) {
        this.biome = biomes;
    }
}
