package me.primemc.rankup.Manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.primemc.rankup.PrimeRankUP;

public class ManagerRank {

	public static HashMap<UUID, Rank> PLAYER_RANKS = new HashMap<UUID, Rank>();

	private String getRankPlayer(Player p) {
		String pRank = null;
		Connection conn = MySQL.con;
		try {
			ResultSet rs = conn.prepareStatement("SELECT * FROM `rankup` WHERE `nome` = '" + p.getName() + "'")
					.executeQuery();
			if (rs.next()) {
				pRank = rs.getString("rank");
			} else {
				setDefaultRank(p);
				pRank = getDefaultRank();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pRank;
	}

	private String getDefaultRank() {
		return PrimeRankUP.RANKS_ORDERED.get(1).getRankId();
	}

	public void setRankPlayer(Player p) {
		String rankid = getRankPlayer(p);
		int pos = getPositionByRank(rankid);
		Rank pRank = new Rank(PrimeRankUP.RANKS_ORDERED.get(pos).getRankId(),
				PrimeRankUP.RANKS_ORDERED.get(pos).getRankName(), PrimeRankUP.RANKS_ORDERED.get(pos).getTag(),
				PrimeRankUP.RANKS_ORDERED.get(pos).getPreco(), PrimeRankUP.RANKS_ORDERED.get(pos).getCommands(), pos);
		PLAYER_RANKS.put(p.getUniqueId(), pRank);
	}

	public void updateRank(Player p, String rank) {
		Connection conn = MySQL.con;
		try {
			String sql;
			sql = "UPDATE `rankup` SET rank = '" + rank + "' WHERE nome = '" + p.getName() + "'";
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int pos = getPositionByRank(rank);
		Rank pRank = new Rank(PrimeRankUP.RANKS_ORDERED.get(pos).getRankId(),
				PrimeRankUP.RANKS_ORDERED.get(pos).getRankName(), PrimeRankUP.RANKS_ORDERED.get(pos).getTag(),
				PrimeRankUP.RANKS_ORDERED.get(pos).getPreco(), PrimeRankUP.RANKS_ORDERED.get(pos).getCommands(), pos);
		PLAYER_RANKS.put(p.getUniqueId(), pRank);
		pRank.getCommands()
				.forEach(c -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c.replace("@player", p.getName())));
	}

	public void setDefaultRank(Player p) {
		Connection conn = MySQL.con;
		try {
			String sql;
			sql = "INSERT INTO `rankup` (nome, rank) VALUES ('" + p.getName() + "', '" + getDefaultRank() + "')";
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (String cmd : PrimeRankUP.RANKS_ORDERED.get(1).getCommands()) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
		}
	}

	public void resetDefaultRank(Player p) {
		Connection conn = MySQL.con;
		try {
			String sql;
			sql = "UPDATE `rankup` SET rank='" + getDefaultRank() + " WHERE nome=" + p.getName();
			conn.prepareStatement(sql).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PLAYER_RANKS.remove(p.getUniqueId());
		Rank rank = new Rank(PrimeRankUP.RANKS_ORDERED.get(1).getRankId(),
				PrimeRankUP.RANKS_ORDERED.get(1).getRankName(), PrimeRankUP.RANKS_ORDERED.get(1).getTag(),
				PrimeRankUP.RANKS_ORDERED.get(1).getPreco(), PrimeRankUP.RANKS_ORDERED.get(1).getCommands(), 1);
		PLAYER_RANKS.put(p.getUniqueId(), rank);
		for (String cmd : PrimeRankUP.RANKS_ORDERED.get(1).getCommands()) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("@player", p.getName()));
		}
	}

	public int getPositionByRank(String nome) {
		return PrimeRankUP.RANKS_ORDERED.values().stream().filter(r -> r.getRankId().equalsIgnoreCase(nome)).findFirst()
				.get().getPosition();
	}

	public Rank getRankById(String nome) {
		return PrimeRankUP.RANKS_ORDERED.values().stream().filter(r -> r.getRankId().equalsIgnoreCase(nome)).findFirst()
				.get();
	}

}
