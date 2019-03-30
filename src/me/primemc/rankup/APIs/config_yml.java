package me.primemc.rankup.APIs;


import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.primemc.rankup.PrimeRankUP;

public class config_yml {
	
	public static File f;
	private static FileConfiguration config_yaml;
	
	 public static void criarArquivo() {
	   PrimeRankUP.getInstance().saveDefaultConfig();
	    f = new File(PrimeRankUP.getInstance().getDataFolder(), "config.yml");
	    if (!f.exists()) {
	      try {
	        f.createNewFile();
	      } catch (IOException var2) {
	        Bukkit.getConsoleSender().sendMessage("§cErro ao criar o arquivo config.yml");
	      }
	    }
	    config_yaml = YamlConfiguration.loadConfiguration(f);
	  }
	  
	  public static FileConfiguration getConfig() {
	    return config_yaml;
	  }
}
