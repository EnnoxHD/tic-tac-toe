package com.github.thegittourist.games.tictactoe;

public enum EPlayerColor {
	RED, BLUE;
	
	public String getColorAsString() throws IllegalArgumentException {
		switch(this) {
		case RED:
			return "Rot";
		case BLUE:
			return "Blau";
		default:
			throw new IllegalArgumentException();
		}
	}
}
