package com.github.ennoxhd.tictactoe;

public record Turn(
		PlayerSign sign,
		int x, int y) {
	
	@Override
	public String toString() {
		return "(x:" + x() + ",y:" + y() + ")";
	}
}
