import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaletteBiome {
    List<Color> biome;
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


    final static PaletteBiome DEFAULT = new PaletteBiome(List.of(
            TUNDRA,
            TAIGA,
            FORET_TEMPEREE,
            FORET_TROPICALE,
            SAVANE,
            PRAIRIE,
            DESERT,
            GLACIER,
            EAU_PEU_PROFONDE,
            EAU_PROFONDE
    ));
    public static List<Color> creerPaletteDeCouleurs() {
        List<Color> palette = new ArrayList<>();
        int step = 51; // Un pas de 51 génère environ 6^3 = 216 couleurs distinctes

        for (int r = 0; r < 256; r += step) {
            for (int g = 0; g < 256; g += step) {
                for (int b = 0; b < 256; b += step) {
                    palette.add(new Color(r, g, b));
                }
            }
        }

        return palette;
    }

    public PaletteBiome(List<Color> biomes) {
        this.biome = biomes;
    }
}
