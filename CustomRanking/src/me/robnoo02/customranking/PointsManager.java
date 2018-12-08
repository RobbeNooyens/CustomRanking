package me.robnoo02.customranking;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PointsManager {

	public static void checkStage(Player p) {
		int points = CustomYmlManager.getPoints(p);
		if (Main.plugin.getConfig().getConfigurationSection("stages").contains(String.valueOf(points))) {

			if (p.hasPermission("player.ranking.rank." + String.valueOf(points))) {

				for (String cmd : CustomYmlManager.getCommands(points)) {
					if (!cmd.equalsIgnoreCase("none")) {
						cmd = cmd.replaceAll("%player%", p.getName());
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
					}
				}
				for (String message : CustomYmlManager.getStageMessage(points)) {
					if (!message.equalsIgnoreCase("none")) {
						message = message.replaceAll("%points%", String.valueOf(points));
						p.sendMessage(TextManager.toColor(message));
					}
				}

			}
		}

	}

	public static void addPoint(Player p) {
		CustomYmlManager.addPoint(p);
	}

}
