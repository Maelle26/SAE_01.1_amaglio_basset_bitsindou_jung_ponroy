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

    public PaletteBiome(List<Color> biomes) {
        this.biome = biomes;
    }
}
