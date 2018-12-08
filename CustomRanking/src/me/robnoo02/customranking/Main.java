package me.robnoo02.customranking;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static File pointsTracker = null;
	public static YamlConfiguration pointsTrackerConfig = new YamlConfiguration();
	public static Main plugin;
	
	public void onEnable() {
		Bukkit.getLogger().info("[CustomRanking] Enabling plugin ...");
		
		Bukkit.getPluginManager().registerEvents(new EatEvent(), this);
		
		getCommand("progress").setExecutor(new Command());
		
		saveDefaultConfig();
		CustomYmlManager.registerCustomYmls(this);
		
		
		Bukkit.getLogger().info("[CustomRanking] Plugin has been enabled");
		
		plugin = this;
	}
	
	public void onDisable() {
		
	}
	
}
