package com.github.thegittourist.games.tictactoe;

public class Player implements Cloneable {

	private EPlayerColor playerColor = null;
	private String playerName = null;
	
	public Player(EPlayerColor playerColor) {
		this.playerColor = playerColor;
		this.playerName = "";
	}
	
	public Player(EPlayerColor playerColor, String playerName) {
		this.playerColor = playerColor;
		this.playerName = playerName;
	}
	
	public EPlayerColor getPlayerColor() {
		return playerColor;
	}
	
	public String getPlayerColorString() {
		return playerColor.getColorAsString();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
