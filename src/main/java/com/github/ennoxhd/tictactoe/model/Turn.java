package com.github.ennoxhd.tictactoe.model;

public record Turn(
		PlayerSign sign,
		int x, int y) {
	
	public Turn {
		validateRange(x, "x");
		validateRange(y, "y");
	}
	
	private void validateRange(int value, String name) {
		if(value < 0 || value > 2)
			throw new IllegalArgumentException("%s not in valid range [0;2]".formatted(name));
	}
	
	@Override
	public String toString() {
		return "(x:" + x() + ",y:" + y() + ")";
	}
}
