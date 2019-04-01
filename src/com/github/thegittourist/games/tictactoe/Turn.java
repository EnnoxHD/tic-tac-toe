package com.github.thegittourist.games.tictactoe;

public class Turn {
	private EPlayerColor playerColor = null;
	private int fieldX = -1, fieldY = -1;
	
	public Turn(EPlayerColor playerColor, int fieldX, int fieldY) {
		this.playerColor = playerColor;
		this.fieldX = fieldX;
		this.fieldY = fieldY;
	}
	
	public EPlayerColor getPlayerColor() {
		return playerColor;
	}
		
	public int getX() {
		return fieldX;
	}
	
	public int getY() {
		return fieldY;
	}
}
