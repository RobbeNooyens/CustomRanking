package me.robnoo02.customranking;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return true;
		if (!cmd.getName().equalsIgnoreCase("progress"))
			return true;
		Player p = (Player) sender;
		if (args.length == 0) {
			if (PermissionsManager.hasPermission(p, "player.ranking.cmd.progress.self")) {
				int points = CustomYmlManager.getPoints(p);
				String message = CustomYmlManager.getYourPointsMess();
				message = message.replaceAll("%points%", String.valueOf(points));
				p.sendMessage(TextManager.toColor(message));
			}
			return true;
		} else {
			if (PermissionsManager.hasPermission(p, "player.ranking.cmd.progress.others")) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target != null) {
					int points = CustomYmlManager.getPoints(target);
					String message = CustomYmlManager.getOtherPointsMess();
					message = message.replaceAll("%player%", target.getName());
					message = message.replaceAll("%points%", String.valueOf(points));
					p.sendMessage(TextManager.toColor(message));
				} else {
					String message = CustomYmlManager.playerNotFoundMess();
					if (!message.equalsIgnoreCase("none")) {
						message = message.replaceAll("%player%", args[0]);
						p.sendMessage(TextManager.toColor(message));
					}
				}
			}
		}
		return true;
	}
}
