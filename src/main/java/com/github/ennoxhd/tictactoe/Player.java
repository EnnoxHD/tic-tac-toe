package com.github.ennoxhd.tictactoe;

public class Player implements Cloneable {

	private PlayerSign sign = null;
	private String name = null;
	
	public Player(PlayerSign sign) {
		this(sign, "");
	}
	
	public Player(PlayerSign sign, String name) {
		this.sign = sign;
		this.name = name;
	}
	
	public Player(Player player) {
		this(player.getSign(), player.getPlayerName());
	}
	
	public PlayerSign getSign() {
		return sign;
	}
	
	public String getSignString() {
		return sign.toString();
	}
	
	public String getPlayerName() {
		return name;
	}
}
