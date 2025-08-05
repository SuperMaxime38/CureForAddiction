package fr.maxime38.cfa.utils;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

public class BlockManagement {
  public static void orientBlock(Location loc, BlockFace facing) {
    if (loc.getBlock().getBlockData() instanceof Directional) {
      Directional dir = (Directional)loc.getBlock().getBlockData();
      dir.setFacing(facing);
      loc.getBlock().setBlockData((BlockData)dir);
      loc.getBlock().getState().update();
    } 
  }
}
