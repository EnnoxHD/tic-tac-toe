package com.github.ennoxhd.tictactoe;

public record Player(
		PlayerSign sign,
		String name) {
	
	public Player {
		if(name == null)
			name = "";
	}
	
	public boolean isNamed() {
		return !"".equals(name());
	}
}
