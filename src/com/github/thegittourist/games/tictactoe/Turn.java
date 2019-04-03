package com.github.thegittourist.games.tictactoe;

public class Turn {
	private EPlayerSign playerSign = null;
	private int fieldX = -1, fieldY = -1;
	
	public Turn(EPlayerSign playerSign, int fieldX, int fieldY) {
		this.playerSign = playerSign;
		this.fieldX = fieldX;
		this.fieldY = fieldY;
	}
	
	public EPlayerSign getSign() {
		return playerSign;
	}
		
	public int getX() {
		return fieldX;
	}
	
	public int getY() {
		return fieldY;
	}
}
