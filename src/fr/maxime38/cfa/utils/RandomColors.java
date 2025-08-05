package fr.maxime38.cfa.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Color;
import org.bukkit.Material;

public class RandomColors {
  public static Material getRandomWoolColor() {
    List<Material> wools = Arrays.asList(new Material[] { 
          Material.RED_WOOL, Material.BLACK_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.CYAN_WOOL, Material.GRAY_WOOL, Material.GREEN_WOOL, 
          Material.LIGHT_BLUE_WOOL, Material.LIGHT_GRAY_WOOL, Material.LIME_WOOL, 
          Material.MAGENTA_WOOL, Material.ORANGE_WOOL, Material.PINK_WOOL, Material.PURPLE_WOOL, Material.WHITE_WOOL, Material.YELLOW_WOOL });
    Random rdm = new Random();
    int i = rdm.nextInt(wools.size());
    return wools.get(i);
  }
  
  public static Color getRandomColor() {
    List<Color> wools = Arrays.asList(new Color[] { 
          Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, 
          Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE, Color.YELLOW });
    Random rdm = new Random();
    int i = rdm.nextInt(wools.size());
    return wools.get(i);
  }
}
