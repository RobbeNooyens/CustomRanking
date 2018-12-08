package me.robnoo02.customranking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CustomYmlManager {

	public static Main plugin;

	// Ymls Files
	public static void registerCustomYmls(Main plugin) {
		CustomYmlManager.plugin = plugin;
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
		Main.pointsTracker = new File(plugin.getDataFolder(), "points.yml");
		createFile(Main.pointsTracker, Main.pointsTrackerConfig, "points.yml");
	}

	public static void mkdir(File file, String path) {
		if (!file.exists()) {
			plugin.saveResource(path, false);
		}
	}

	public static void createFile(File file, YamlConfiguration ymlConfig, String pathName) {
		mkdir(file, pathName);
		loadYml(file, ymlConfig);
		saveCustomYml(file, ymlConfig);

	}

	public static void saveCustomYml(File file, YamlConfiguration ymlConfig) {
		try {
			ymlConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadYml(File file, YamlConfiguration yamlConfig) {
		try {
			yamlConfig.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static int getPoints(Player p) {
		alreadyRegistered(p);
		return Main.pointsTrackerConfig.getInt(String.valueOf(p.getUniqueId()) + ".points");
	}

	public static void setPoint(Player p, int amount) {
		alreadyRegistered(p);
		Main.pointsTrackerConfig.set(String.valueOf(p.getUniqueId()) + ".points", getPoints(p) + amount);
		saveCustomYml(Main.pointsTracker, Main.pointsTrackerConfig);
	}

	public static void alreadyRegistered(Player p) {
		if (!Main.pointsTrackerConfig.getKeys(false).contains(String.valueOf(p.getUniqueId()))) {
			Main.pointsTrackerConfig.set(String.valueOf(p.getUniqueId()) + ".name", p.getName());
			Main.pointsTrackerConfig.set(String.valueOf(p.getUniqueId()) + ".points", 0);
			saveCustomYml(Main.pointsTracker, Main.pointsTrackerConfig);
		}
	}

	public static void addPoint(Player p) {
		setPoint(p, 1);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getCommands(int points) {
		try {
			return (ArrayList<String>) Main.plugin.getConfig()
					.getList("stages." + String.valueOf(points) + ".cmds-to-execute");
		} catch (Exception e) {
			Bukkit.getLogger().warning(
					"[CustomRanking] Commands not found in config.yml for " + String.valueOf(points) + " points!");
			return new ArrayList<String>();
		}
	}

	public static String getYourPointsMess() {
		try {
			return Main.plugin.getConfig().getString("your-points-message");
		} catch (Exception e) {
			Bukkit.getLogger().warning("[CustomRanking] your-points-message not found in config.yml!");
			return "none";
		}
	}

	public static String getOtherPointsMess() {
		try {
			return Main.plugin.getConfig().getString("other-points-message");
		} catch (Exception e) {
			Bukkit.getLogger().warning("[CustomRanking] other-points-message not found in config.yml!");
			return "none";
		}
	}

	public static String getNoPermMess() {
		try {
			return Main.plugin.getConfig().getString("player-no-permission");
		} catch (Exception e) {
			Bukkit.getLogger().warning("[CustomRanking] player-no-permission not found in config.yml!");
			return "none";
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> getStageMessage(int points) {
		try {
			return (ArrayList<String>) Main.plugin.getConfig()
					.getList("stages." + String.valueOf(points) + ".messages-to-player");
		} catch (Exception e) {
			Bukkit.getLogger().warning("[CustomRanking] messages-to-player not found in config.yml!");
			return new ArrayList<String>();
		}
	}

	public static String playerNotFoundMess() {
		try {
			return Main.plugin.getConfig().getString("player-not-found");
		} catch (Exception e) {
			Bukkit.getLogger().warning("[CustomRanking] player-not-found not found in config.yml!");
			return "none";
		}
	}
}
