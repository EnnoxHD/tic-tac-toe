package com.github.ennoxhd.games.tictactoe;

public class Player implements Cloneable {

	private EPlayerSign sign = null;
	private String name = null;
	
	public Player(EPlayerSign sign) {
		this(sign, "");
	}
	
	public Player(EPlayerSign sign, String name) {
		this.sign = sign;
		this.name = name;
	}
	
	public EPlayerSign getSign() {
		return sign;
	}
	
	public String getSignString() {
		return sign.toString();
	}
	
	public String getPlayerName() {
		return name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
