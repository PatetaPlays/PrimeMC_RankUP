package me.primemc.rankup.Utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import me.primemc.rankup.PrimeRankUP;
import me.primemc.rankup.APIs.config_yml;
import me.primemc.rankup.Manager.Rank;

public class LoadRanks {
	
	public static void loadRanks() {
		int i = 1;
		for (String s: config_yml.getConfig().getConfigurationSection("Ranks").getKeys(false)) {
			FileConfiguration conf = config_yml.getConfig();
			Rank rank = new Rank(s, conf.getString("Ranks." + s + ".Nome").replaceAll("&", "§"), conf.getString("Ranks." + s + ".Tag"), conf.getDouble("Ranks." + s + ".Preco"), conf.getStringList("Ranks." + s + ".Comandos"), i);
			PrimeRankUP.RANKS_ORDERED.put(i, rank);
			i++;
		}
		Bukkit.getConsoleSender().sendMessage("§a[pRankUP] Foram carregados §f" + (i - 1) + "§a ranks.");
	}

}
