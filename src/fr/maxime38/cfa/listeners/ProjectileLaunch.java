package fr.maxime38.cfa.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.maxime38.cfa.Main;
import fr.maxime38.cfa.utils.RandomBetween;

public class ProjectileLaunch implements Listener {
	
	static Main main;
	public ProjectileLaunch(Main main) {
		ProjectileLaunch.main = main;
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		Projectile projectile = e.getEntity();
		
		if(!(projectile.getShooter() instanceof Entity)) return;
		Entity shooter = (Entity) projectile.getShooter();
		
		Random rdm = new Random();
		
		
		switch(projectile.getType()) {
		case FIREBALL:

			if(shooter instanceof Ghast) {
				
				//Im lazy now
				// also don't forget #multishot
				
			}
			
			break;
		default:
			break;
		}
	}
	
	public void multishot(Entity shooter, Projectile projectile, Random rdm) {
		int amount = 2 + rdm.nextInt(4);
		List<Fireball> fbs = new ArrayList<>();
		Location loc = projectile.getLocation().clone();
		for(int i = 0; i < amount; i++) {
			fbs.add((Fireball) shooter.getWorld().spawnEntity(loc.clone().add(new Vector(0, 0.27*(i+1), 0)), EntityType.FIREBALL));
		}
		
		new BukkitRunnable() {
			
			int counter = 0;

			@Override
			public void run() {
				if(counter >= amount) {
					cancel();
					return;
				}
				Fireball f = fbs.get(counter);
				Vector v = new Vector(RandomBetween.randomDouble(-0.12, 0.12), RandomBetween.randomDouble(-0.26, 0.18), RandomBetween.randomDouble(-0.12, 0.12));
				f.setVelocity(projectile.getVelocity().clone().add(v).multiply(1 + (amount - counter)));
				
				counter++;
			}
			
		}.runTaskTimerAsynchronously(main, 0, 1);
	}
	
	public void rapidFire() {
		
	}

}
