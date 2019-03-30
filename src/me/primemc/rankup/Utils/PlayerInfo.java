package me.primemc.rankup.Utils;

import java.util.UUID;

import me.primemc.rankup.PrimeRankUP;
import me.primemc.rankup.Manager.ManagerRank;
import me.primemc.rankup.Manager.Rank;

public class PlayerInfo {
	
	private UUID player;
	
	public PlayerInfo(UUID player) {
		this.player = player;
	}
	
	public UUID getPlayer() {
		return this.player;
	}
	
	public void removeRank() {
		if (ManagerRank.PLAYER_RANKS.containsKey(player)) {
			ManagerRank.PLAYER_RANKS.remove(player);
		}
	}

	public Rank getPlayerRank() {
		return ManagerRank.PLAYER_RANKS.containsKey(player) ? ManagerRank.PLAYER_RANKS.get(player) : null;
	}

	public Rank getNextRank() {
		return PrimeRankUP.RANKS_ORDERED.get(ManagerRank.PLAYER_RANKS.get(player).getPosition() + 1);
	}

}
