package com.github.ennoxhd.tictactoe;

public class Board implements Cloneable {

	// fields[row][column]
	private EPlayerSign[][] fields = new EPlayerSign[3][3];
	
	public Board() {
		initBoard();
	}
	
	public Board(EPlayerSign[][] fields) throws IllegalArgumentException {
		if(fields.length == 3 && fields[0].length == 3 && fields[1].length == 3 && fields[2].length == 3) {
			this.fields = fields.clone();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private void initBoard() {
		for(int i = 0; i < fields.length; i++) {
			for(int k = 0; k < fields[i].length; k++) {
				fields[i][k] = null;
			}
		}
	}
	
	public EPlayerSign[] getRow(int rowNumber) {
		return fields[rowNumber];
	}
	
	public EPlayerSign[] getColumn(int columnNumber) {
		EPlayerSign[] column = {getRow(0)[columnNumber], getRow(1)[columnNumber], getRow(2)[columnNumber]};
		return column;
	}
	
	public boolean nextTurnValid(Turn turn) {
		return (turn.getX() >= 0 && turn.getX() <= 2
			&& turn.getY() >= 0 && turn.getY() <= 2
			&& fields[turn.getY()][turn.getX()] == null) ? true : false;
	}
	
	public void nextTurn(Turn turn) {
		if(nextTurnValid(turn)) {
			fields[turn.getY()][turn.getX()] = turn.getSign();
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public boolean hasWon() {
		boolean hasWon = false;
		EPlayerSign[] hasWonRow = new EPlayerSign[3], hasWonColumn = new EPlayerSign[3], hasWonX = new EPlayerSign[2];
		for(int i = 0; i < 3; i++) {
			//Rows
			if(getRow(i)[0] == EPlayerSign.X && getRow(i)[1] == EPlayerSign.X && getRow(i)[2] == EPlayerSign.X) {
				hasWonRow[i] = EPlayerSign.X;
			}
			if(getRow(i)[0] == EPlayerSign.O && getRow(i)[1] == EPlayerSign.O && getRow(i)[2] == EPlayerSign.O) {
				hasWonRow[i] = EPlayerSign.O;
			}
			//Columns
			if(getColumn(i)[0] == EPlayerSign.X && getColumn(i)[1] == EPlayerSign.X && getColumn(i)[2] == EPlayerSign.X) {
				hasWonColumn[i] = EPlayerSign.X;
			}
			if(getColumn(i)[0] == EPlayerSign.O && getColumn(i)[1] == EPlayerSign.O && getColumn(i)[2] == EPlayerSign.O) {
				hasWonColumn[i] = EPlayerSign.O;
			}
		}
		//X fuer \
		if(getRow(0)[0] == EPlayerSign.X && getRow(1)[1] == EPlayerSign.X && getRow(2)[2] == EPlayerSign.X) {
			hasWonX[0] = EPlayerSign.X;
		}
		if(getRow(0)[0] == EPlayerSign.O && getRow(1)[1] == EPlayerSign.O && getRow(2)[2] == EPlayerSign.O) {
			hasWonX[0] = EPlayerSign.O;
		}
		//X fuer /
		if(getRow(0)[2] == EPlayerSign.X && getRow(1)[1] == EPlayerSign.X && getRow(2)[0] == EPlayerSign.X) {
			hasWonX[0] = EPlayerSign.X;
		}
		if(getRow(0)[2] == EPlayerSign.O && getRow(1)[1] == EPlayerSign.O && getRow(2)[0] == EPlayerSign.O) {
			hasWonX[0] = EPlayerSign.O;
		}
		
		//Abfragen
		for(int i = 0; i < 3; i++) {
			if(hasWonRow[i] == EPlayerSign.X || hasWonColumn[i] == EPlayerSign.X) {
				hasWon = true;
			}
			if(hasWonRow[i] == EPlayerSign.O || hasWonColumn[i] == EPlayerSign.O) {
				hasWon = true;
			}
			if(i < 2 && (hasWonX[i] == EPlayerSign.X || hasWonX[i] == EPlayerSign.O)) {
				hasWon = true;
			}
		}
		return hasWon;
	}
}
