package me.primemc.rankup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.primemc.rankup.APIs.config_yml;
import me.primemc.rankup.Comando.RankUpCMD;
import me.primemc.rankup.Comando.RanksCMD;
import me.primemc.rankup.Eventos.onJoin;
import me.primemc.rankup.Eventos.onQuit;
import me.primemc.rankup.Manager.MySQL;
import me.primemc.rankup.Manager.Rank;
import me.primemc.rankup.Utils.LoadRanks;
import net.milkbowl.vault.economy.Economy;

public class PrimeRankUP extends JavaPlugin {
	
	private static PrimeRankUP instance;
	public static Map<Integer, Rank> RANKS_ORDERED = new HashMap<>();
	public static Economy economy = null;
	
	public void onEnable() {
		instance = this;
		
		config_yml.criarArquivo();
		
		MySQL.openConnection();
		MySQL.createTable();
		
		setupEconomy();
		
		
		Bukkit.getPluginManager().registerEvents(new onJoin(), this);
		Bukkit.getPluginManager().registerEvents(new onQuit(), this);
		
		getCommand("rankup").setExecutor(new RankUpCMD());
		getCommand("ranks").setExecutor(new RanksCMD());
		
		Bukkit.getConsoleSender().sendMessage("§a[PrimeRankUP] Plugin iniciado com sucesso.");
		LoadRanks.loadRanks();
	}
	
	public void onDisable() {
		MySQL.closeConnection();
		try {
			config_yml.getConfig().save(config_yml.f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
	
	public static PrimeRankUP getInstance() {
		return instance;
	}
}
