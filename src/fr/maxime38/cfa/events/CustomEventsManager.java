package fr.maxime38.cfa.events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.maxime38.cfa.Main;

public class CustomEventsManager {
	
	public static void run(Main main) {
		new BukkitRunnable() {

			@Override
			public void run() {
				for(World w : Bukkit.getWorlds()) {
					for(Entity entity : w.getEntities()) {
					if(entity.getVelocity().getY() > 0 && !entity.isOnGround() && !entity.getType().equals(EntityType.BEE)) {
						EntityJumpEvent event = new EntityJumpEvent(entity);
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
				}
				}
				
			}
			
		}.runTaskTimer(main, 0, 1);
	}
}
