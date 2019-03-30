package me.primemc.rankup.Manager;

import java.util.ArrayList;
import java.util.List;

public class Rank {
	
	private int position;
	private double price;
	private String name;
	private String name_id;
	private String tag;
	private List<String> commands = new ArrayList<String>();
	
	public Rank(String name_id, String name, String tag, double price, List<String> commands, int position) {
		this.name_id = name_id;
		this.price = price;
		this.name = name;
		this.position = position;
		this.commands = commands;
		this.tag = tag;
	}
	
	public int getPosition() {
		return position;
	}

	public String getRankName() {
		String name = this.name.replace("&", "§");
		return name;
	}
	
	public String getRankId() {
		return name_id;
	}
	
	public String getTag() {
		return tag;
	}
	
	public double getPreco() {
		return price;
	}
	
	public List<String> getCommands() {
		return commands;
	}

}
