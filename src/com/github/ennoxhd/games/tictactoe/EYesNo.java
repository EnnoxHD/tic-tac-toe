package com.github.ennoxhd.games.tictactoe;

public enum EYesNo {
	JA, NEIN;
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
