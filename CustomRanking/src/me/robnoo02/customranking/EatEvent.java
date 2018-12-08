package me.robnoo02.customranking;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class EatEvent implements Listener {

	@EventHandler
	public void onEat(PlayerItemConsumeEvent e) {
		Player p = e.getPlayer();
		if(e.getItem().getType().equals(Material.ROTTEN_FLESH)) {
			PointsManager.addPoint(p);
			PointsManager.checkStage(p);
		}
	}
	
}
