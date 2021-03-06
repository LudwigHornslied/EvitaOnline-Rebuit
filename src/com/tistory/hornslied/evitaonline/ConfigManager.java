package com.tistory.hornslied.evitaonline;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	
	private EvitaOnline plugin;
	
	private HashMap<ConfigType, FileConfiguration> configs;
	
	public ConfigManager(EvitaOnline plugin) {
		this.plugin = plugin;
		
		configs = new HashMap<>();
		load();
	}
	
	private void load() {
		for(ConfigType type : ConfigType.values()) {
			configs.put(type, YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), type.getFileName())));
		}
	}
	
	public void reload() {
		load();
	}
	
	public FileConfiguration getConfig(ConfigType type) {
		return configs.get(type);
	}
	
	public void saveConfig(ConfigType type) {
		try {
			configs.get(type).save(new File(plugin.getDataFolder(), type.getFileName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public enum ConfigType {
		DEFAULT("config.yml"),
		ALLOWEDPLAYERS("allowedPlayers.yml"),
		CRATES("crates.yml"),
		DYNMAP("dynmap.yml"),
		ITEMS("items.yml"),
		SHOPS("shops.yml"),
		HOLOGRAMS("holograms.yml");
		
		private String fileName;
		
		ConfigType(String fileName) {
			this.fileName = fileName;
		}
		
		public String getFileName() {
			return fileName;
		}
	}
}
