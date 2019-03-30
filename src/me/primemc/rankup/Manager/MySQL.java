package me.primemc.rankup.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class MySQL {
	
	public static Connection con = null;
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();
	
	public static void openConnection() {
		
		String url = "jdbc:mysql://localhost:3306/rankup";
		String user = "root";
		String password = "";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			sc.sendMessage("�a[pRankUP] Conex�o com MySQL aberta com sucesso.");
		} catch (SQLException e) {
			sc.sendMessage("�c[pRankUP] N�o foi poss�vel abrir a conex�o com MySQL.");
		}
	}
	
	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
				sc.sendMessage("�c[pRankUP] Conex�o fechada com sucesso.");
			} catch (SQLException e) {
				sc.sendMessage("�c[pRankUP] N�o foi poss�vel fechar a conex�o.");
			}
		}
	}
	
	public static void createTable() {
		if (con != null) {
			PreparedStatement stm = null;
			try {
				stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `rankup` (`nome` TEXT NULL DEFAULT NULL,`rank` TEXT NULL DEFAULT NULL)");
				stm.executeUpdate();
				sc.sendMessage("�a[pRankUP] Tabela criada com sucesso.");
			} catch (SQLException e) {
				sc.sendMessage("�c[pRankUP] N�o foi poss�vel criar a tabela.");
			}
		}
	}
	

}
