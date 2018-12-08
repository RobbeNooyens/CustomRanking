package me.robnoo02.customranking;

import org.bukkit.ChatColor;

public class TextManager {

	public static String toColor(String input) {
		String output = input;
		output = (ChatColor.translateAlternateColorCodes('&', output));
		return output;
	}

	public static String removeColor(String input) {
		String output = input;
		output = (ChatColor.stripColor(output));
		return output;
	}
}
