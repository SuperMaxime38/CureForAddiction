package fr.maxime38.cfa.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.maxime38.cfa.events.EntityJumpEvent;

public class JumpEvent implements Listener{
	
	@EventHandler
	public void onJump(EntityJumpEvent e) {
		
		Entity entity = e.getEntity();
		if(entity instanceof MagmaCube) {
			Random rdm = new Random();
			int luck = rdm.nextInt(100);
			if(luck < 40) {
				replaceAirUnderneath(entity.getLocation(), Material.MAGMA_BLOCK, true);
			}
		}
	}
	
	private void replaceAirUnderneath(Location loc, Material type, boolean doSomethingThatLooksNice) {
		Location location = loc.clone();
		if(doSomethingThatLooksNice) {
			
			loc.add(0, -1, 0); // ca fait moche sinon
		} else {
			location.add(0, -1, 0);
		}
		
		if(location.getBlock().getType().equals(Material.AIR)) {
			location.getBlock().setType(type);
			location.getBlock().getState().update(true);
			replaceAirUnderneath(location, type, true);
		}
	}
}
