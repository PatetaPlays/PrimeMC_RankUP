package me.primemc.rankup.Comando;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.primemc.rankup.APIs.config_yml;

public class RanksCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command cmd, String arg2, String[] arg3) {
		if (cmd.getName().equalsIgnoreCase("ranks")) {
			List<String> ranks_list = new ArrayList<String>();
			ranks_list.addAll(config_yml.getConfig().getConfigurationSection("Ranks").getKeys(false));
			for (String s : ranks_list) {
				arg0.sendMessage("§a" + s.toString());
			}
		}
		return false;
	}

}
