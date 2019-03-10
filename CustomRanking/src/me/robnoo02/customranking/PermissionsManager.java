package me.robnoo02.customranking;

import org.bukkit.entity.Player;

public class PermissionsManager {

	public static boolean hasPermission(Player p, String perm) {
		if(p.hasPermission(perm))
			return true;
		p.sendMessage(TextManager.toColor(CustomYmlManager.getNoPermMess()));
		return false;
	}
	
}
