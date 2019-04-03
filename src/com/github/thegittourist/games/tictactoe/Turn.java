package com.github.thegittourist.games.tictactoe;

public class Turn {
	private EPlayerSign playerColor = null;
	private int fieldX = -1, fieldY = -1;
	
	public Turn(EPlayerSign playerColor, int fieldX, int fieldY) {
		this.playerColor = playerColor;
		this.fieldX = fieldX;
		this.fieldY = fieldY;
	}
	
	public EPlayerSign getPlayerColor() {
		return playerColor;
	}
		
	public int getX() {
		return fieldX;
	}
	
	public int getY() {
		return fieldY;
	}
}
