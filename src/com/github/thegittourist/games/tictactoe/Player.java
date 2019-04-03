package com.github.thegittourist.games.tictactoe;

public class Player implements Cloneable {

	private EPlayerSign playerColor = null;
	private String playerName = null;
	
	public Player(EPlayerSign playerColor) {
		this.playerColor = playerColor;
		this.playerName = "";
	}
	
	public Player(EPlayerSign playerColor, String playerName) {
		this.playerColor = playerColor;
		this.playerName = playerName;
	}
	
	public EPlayerSign getPlayerColor() {
		return playerColor;
	}
	
	public String getPlayerColorString() {
		return playerColor.toString();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
