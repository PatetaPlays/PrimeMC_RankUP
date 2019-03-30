package me.primemc.rankup.Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.primemc.rankup.Manager.ManagerRank;

public class onQuit implements Listener {
	
	@EventHandler
	public static void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ManagerRank.PLAYER_RANKS.containsKey(p.getUniqueId())) {
			ManagerRank.PLAYER_RANKS.remove(p.getUniqueId());
		}
	}

}
