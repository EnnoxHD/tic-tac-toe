package com.github.ennoxhd.tictactoe;

import java.util.List;
import java.util.stream.Collectors;

public enum Decision {
	YES,
	NO;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	public static Decision fromString(String value) {
		for(Decision decision : values()) {
			if(decision.toString().equals(value))
				return decision;
		}
		return null;
	}
	
	public static String giveOptions() {
		return List.of(values()).stream()
				.map(Decision::toString)
				.collect(Collectors.joining(" or ", "'", "'"));
	}
}
