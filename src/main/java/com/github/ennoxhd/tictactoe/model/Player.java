package com.github.ennoxhd.tictactoe.model;

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
	
	@Override
	public String toString() {
		return isNamed() ? name() : sign().toString();
	}
}
