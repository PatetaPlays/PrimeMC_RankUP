package me.primemc.rankup.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PlayerRankupEvent extends Event {
	
	
   private Player player;
   private String rank;
   private String oldrank;
   private String tag;
   private double price;
   private static final HandlerList handlers = new HandlerList();
  
   public PlayerRankupEvent(Player player, String rank, String oldRank, String tag, double price) {
       this.player = player;
       this.rank = rank;
       this.oldrank = oldRank;
       this.tag = tag;
       this.price = price;
    
   }
  
   public HandlerList getHandlers() {
	   return handlers;
   }
  
   public static HandlerList getHandlerList() {
	   return handlers;
   }
  
   public Player getPlayer() {
	   return player;
   }
  
   public String getRank() {
       return rank;
   }
  
   public String getRankName() {
	   return oldrank;
   }
  
   public String oldRank() {
       return oldrank;
   }
  
   public String getTag() {
	   return tag;
   }
  
   public double getPreco() {
       return price;
   }
}
