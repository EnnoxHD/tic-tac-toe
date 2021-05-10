package com.github.ennoxhd.tictactoe;

public class Turn {
	private PlayerSign playerSign = null;
	private int fieldX = -1, fieldY = -1;
	
	public Turn(PlayerSign playerSign, int fieldX, int fieldY) {
		this.playerSign = playerSign;
		this.fieldX = fieldX;
		this.fieldY = fieldY;
	}
	
	public PlayerSign getSign() {
		return playerSign;
	}
		
	public int getX() {
		return fieldX;
	}
	
	public int getY() {
		return fieldY;
	}
}
