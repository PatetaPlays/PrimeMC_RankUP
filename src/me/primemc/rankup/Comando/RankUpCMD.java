package me.primemc.rankup.Comando;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.primemc.rankup.PrimeRankUP;
import me.primemc.rankup.Manager.ManagerRank;
import me.primemc.rankup.Manager.Rank;
import me.primemc.rankup.Utils.PlayerInfo;
import me.primemc.rankup.Utils.PlayerRankupEvent;

public class RankUpCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cVocê não pode executar este comando pelo console.");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("rankup")) {
			if (!ManagerRank.PLAYER_RANKS.containsKey(p.getUniqueId())) {
				p.sendMessage("§cSeu rank não foi encontrado, por favor, relogue.");
				return true;
			}
			PlayerInfo info = new PlayerInfo(p.getUniqueId());
			Rank pRank = info.getPlayerRank();
			Rank pNRank = info.getNextRank();
			if (pRank == null) {
				p.sendMessage("§cNão foi possível encontrar seu rank, por favor, relogue.");
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
				return true;
			}

			if (pNRank == null) {
				p.sendMessage("§aVocê já atingiu o rank máximo!");
				p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
				return true;
			}
			if (uparRank(p, info, pRank, pNRank)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private boolean uparRank(Player p, PlayerInfo pInfo, Rank pRank, Rank pNewRank) {
		double rankPreco = pNewRank.getPreco();
		String oldRank = pRank.getRankName();
		if (PrimeRankUP.economy.has(p.getName(), rankPreco)) {
			PrimeRankUP.economy.withdrawPlayer(p.getName(), rankPreco);
		} else {
			p.sendMessage("§cVocê precisa de §f" + rankPreco + " §c para upar de rank.");
			return true;
		}
		p.sendMessage("§aParabéns! Você upou para o rank §f" + pNewRank.getRankName());
		new ManagerRank().updateRank(p, pNewRank.getRankId());
		Bukkit.getPluginManager().callEvent(new PlayerRankupEvent(p, pInfo.getPlayerRank().getRankName(), oldRank,
				pInfo.getPlayerRank().getTag(), rankPreco));
		return false;
	}

}
