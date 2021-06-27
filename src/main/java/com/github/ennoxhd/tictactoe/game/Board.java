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
		PlayerSign[] column = {getRow(0)[columnNumber], getRow(1)[columnNumber], getRow(2)[columnNumber]};
		return column;
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
		PlayerSign[] hasWonRow = new PlayerSign[3], hasWonColumn = new PlayerSign[3], hasWonX = new PlayerSign[2];
		for(int i = 0; i < 3; i++) {
			//Rows
			if(getRow(i)[0] == PlayerSign.X && getRow(i)[1] == PlayerSign.X && getRow(i)[2] == PlayerSign.X) {
				hasWonRow[i] = PlayerSign.X;
			}
			if(getRow(i)[0] == PlayerSign.O && getRow(i)[1] == PlayerSign.O && getRow(i)[2] == PlayerSign.O) {
				hasWonRow[i] = PlayerSign.O;
			}
			//Columns
			if(getColumn(i)[0] == PlayerSign.X && getColumn(i)[1] == PlayerSign.X && getColumn(i)[2] == PlayerSign.X) {
				hasWonColumn[i] = PlayerSign.X;
			}
			if(getColumn(i)[0] == PlayerSign.O && getColumn(i)[1] == PlayerSign.O && getColumn(i)[2] == PlayerSign.O) {
				hasWonColumn[i] = PlayerSign.O;
			}
		}
		//X fuer \
		if(getRow(0)[0] == PlayerSign.X && getRow(1)[1] == PlayerSign.X && getRow(2)[2] == PlayerSign.X) {
			hasWonX[0] = PlayerSign.X;
		}
		if(getRow(0)[0] == PlayerSign.O && getRow(1)[1] == PlayerSign.O && getRow(2)[2] == PlayerSign.O) {
			hasWonX[0] = PlayerSign.O;
		}
		//X fuer /
		if(getRow(0)[2] == PlayerSign.X && getRow(1)[1] == PlayerSign.X && getRow(2)[0] == PlayerSign.X) {
			hasWonX[0] = PlayerSign.X;
		}
		if(getRow(0)[2] == PlayerSign.O && getRow(1)[1] == PlayerSign.O && getRow(2)[0] == PlayerSign.O) {
			hasWonX[0] = PlayerSign.O;
		}
		
		//Abfragen
		PlayerSign sign = null;
		for(int i = 0; i < 3; i++) {
			if(hasWonRow[i] == PlayerSign.X || hasWonColumn[i] == PlayerSign.X) {
				sign = PlayerSign.X;
			}
			if(hasWonRow[i] == PlayerSign.O || hasWonColumn[i] == PlayerSign.O) {
				sign = PlayerSign.O;
			}
			if(i < 2) {
				if(hasWonX[i] == PlayerSign.X) {
					sign = PlayerSign.X;
				}
				if(hasWonX[i] == PlayerSign.O) {
					sign = PlayerSign.O;
				}
			}
		}
		return sign;
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
