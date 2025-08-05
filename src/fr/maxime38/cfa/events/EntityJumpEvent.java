package fr.maxime38.cfa.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityJumpEvent extends Event implements Cancellable{
	
	private static final HandlerList HANDLERS = new HandlerList();
	private boolean isCancelled;
	private final Entity entity;
	
	public EntityJumpEvent(Entity entity) {
		this.entity = entity;
	}

	public static HandlerList getHandlerList() {
        return HANDLERS;
    }
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	public Entity getEntity() {
		return entity;
	}

}
