package me.primemc.rankup.Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.primemc.rankup.Manager.ManagerRank;

public class onJoin implements Listener{
	
	@EventHandler
	public static void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ManagerRank rank = new ManagerRank();
		rank.setRankPlayer(p);
	}

}
