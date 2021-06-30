package com.github.ennoxhd.tictactoe.game;

import com.github.ennoxhd.tictactoe.model.PlayerSign;
import com.github.ennoxhd.tictactoe.model.Turn;

public class Board {

	/**
	 * fields[row][column]
	 */
	private PlayerSign[][] fields;
	
	public Board() {
		this(new PlayerSign[3][3]);
	}
	
	private Board(PlayerSign[][] fields) throws IllegalArgumentException {
		if(!checkArrayDimensions(fields))
			throw new IllegalArgumentException("Incorrect array dimensions. Must be 3-by-3.");
		this.fields = new PlayerSign[3][3];
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				this.fields[y][x] = fields[y][x];
			}
		}
	}
	
	private boolean checkArrayDimensions(PlayerSign[][] fields) {
		if(fields == null) return false;
		if(fields.length != 3) return false;
		for(int i = 0; i < 3; i++) {
			if(fields[i] == null) return false;
			if(fields[i].length != 3) return false;
		}
		return true;
	}
	
	PlayerSign[] getRow(int rowNumber) {
		return fields[rowNumber];
	}
	
	PlayerSign[] getColumn(int columnNumber) {
		return new PlayerSign[] {
			getRow(0)[columnNumber],
			getRow(1)[columnNumber],
			getRow(2)[columnNumber]
		};
	}
	
	public boolean nextTurnValid(Turn turn) {
		return fields[turn.y()][turn.x()] == null;
	}
	
	void nextTurn(Turn turn) {
		if(nextTurnValid(turn)) {
			fields[turn.y()][turn.x()] = turn.sign();
		}
	}
	
	public PlayerSign getWinner() {
		for(PlayerSign sign : PlayerSign.values()) {
			for(int i = 0; i < 3; i++) {
				// Rows |
				if(getRow(i)[0] == sign && getRow(i)[1] == sign && getRow(i)[2] == sign) {
					return sign;
				}
				// Columns ---
				if(getColumn(i)[0] == sign && getColumn(i)[1] == sign && getColumn(i)[2] == sign) {
					return sign;
				}
			}
			// Diagonals X
			if(getRow(1)[1] == sign) {
				// Falling diagonal \
				if(getRow(0)[0] == sign && getRow(2)[2] == sign) {
					return sign;
				}
				// Rising diagonal /
				if(getRow(0)[2] == sign && getRow(2)[0] == sign) {
					return sign;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += "-------\n";
		for(int i = 0; i < 3; i++) {
			out += "|";
			for(int k = 0; k < 3; k++) {
				PlayerSign field = getRow(i)[k];
				String boardSign = field != null ? field.toString() : " ";
				out += boardSign + "|";
			}
			out += "\n";
		}
		return out += "-------\n";
	}
}
