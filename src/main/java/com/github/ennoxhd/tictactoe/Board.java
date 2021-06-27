package com.github.ennoxhd.tictactoe;

public class Board {

	// fields[row][column]
	private PlayerSign[][] fields;
	
	public Board() {
		this(new PlayerSign[3][3]);
	}
	
	public Board(Board board) {
		this(board.fields);
	}
	
	private Board(PlayerSign[][] fields) throws IllegalArgumentException {
		if(!checkArrayDimensions(fields)) throw new IllegalArgumentException();
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
	
	public PlayerSign[] getRow(int rowNumber) {
		return fields[rowNumber];
	}
	
	public PlayerSign[] getColumn(int columnNumber) {
		PlayerSign[] column = {getRow(0)[columnNumber], getRow(1)[columnNumber], getRow(2)[columnNumber]};
		return column;
	}
	
	public boolean nextTurnValid(Turn turn) {
		return turn.getX() >= 0 && turn.getX() <= 2
			&& turn.getY() >= 0 && turn.getY() <= 2
			&& fields[turn.getY()][turn.getX()] == null;
	}
	
	public void nextTurn(Turn turn) {
		if(nextTurnValid(turn)) {
			fields[turn.getY()][turn.getX()] = turn.getSign();
		}
	}
	
	public boolean hasWon() {
		boolean hasWon = false;
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
		for(int i = 0; i < 3; i++) {
			if(hasWonRow[i] == PlayerSign.X || hasWonColumn[i] == PlayerSign.X) {
				hasWon = true;
			}
			if(hasWonRow[i] == PlayerSign.O || hasWonColumn[i] == PlayerSign.O) {
				hasWon = true;
			}
			if(i < 2 && (hasWonX[i] == PlayerSign.X || hasWonX[i] == PlayerSign.O)) {
				hasWon = true;
			}
		}
		return hasWon;
	}
	
	@Override
	public String toString() {
		String out = "";
		out += "-------\n";
		for(int i = 0; i < 3; i++) {
			out += "|";
			for(int k = 0; k < 3; k++) {
				String boardSign = "";
				if(getRow(i)[k] == PlayerSign.X) {
					boardSign = "X";
				} else if(getRow(i)[k] == PlayerSign.O) {
					boardSign = "O";
				} else if(getRow(i)[k] == null) {
					boardSign = " ";
				} else {
					throw new IllegalArgumentException();
				}
				out += boardSign + "|";
			}
			out += "\n";
		}
		return out += "-------\n";
	}
}
