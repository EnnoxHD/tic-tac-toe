package ttt;

public class Board implements Cloneable {

	// fields[row][column]
	private EPlayerColor[][] fields = new EPlayerColor[3][3];
	
	public Board() {
		initBoard();
	}
	
	public Board(EPlayerColor[][] fields) throws IllegalArgumentException {
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
	
	public EPlayerColor[] getRow(int rowNumber) {
		return fields[rowNumber];
	}
	
	public EPlayerColor[] getColumn(int columnNumber) {
		EPlayerColor[] column = {getRow(0)[columnNumber], getRow(1)[columnNumber], getRow(2)[columnNumber]};
		return column;
	}
	
	public boolean nextTurnValid(Turn turn) {
		return (turn.getX() >= 0 && turn.getX() <= 2
			&& turn.getY() >= 0 && turn.getY() <= 2
			&& fields[turn.getY()][turn.getX()] == null) ? true : false;
	}
	
	public void nextTurn(Turn turn) {
		if(nextTurnValid(turn)) {
			fields[turn.getY()][turn.getX()] = turn.getPlayerColor();
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public boolean hasWon() {
		boolean hasWon = false;
		EPlayerColor[] hasWonRow = new EPlayerColor[3], hasWonColumn = new EPlayerColor[3], hasWonX = new EPlayerColor[2];
		for(int i = 0; i < 3; i++) {
			//Rows
			if(getRow(i)[0] == EPlayerColor.RED && getRow(i)[1] == EPlayerColor.RED && getRow(i)[2] == EPlayerColor.RED) {
				hasWonRow[i] = EPlayerColor.RED;
			}
			if(getRow(i)[0] == EPlayerColor.BLUE && getRow(i)[1] == EPlayerColor.BLUE && getRow(i)[2] == EPlayerColor.BLUE) {
				hasWonRow[i] = EPlayerColor.BLUE;
			}
			//Columns
			if(getColumn(i)[0] == EPlayerColor.RED && getColumn(i)[1] == EPlayerColor.RED && getColumn(i)[2] == EPlayerColor.RED) {
				hasWonColumn[i] = EPlayerColor.RED;
			}
			if(getColumn(i)[0] == EPlayerColor.BLUE && getColumn(i)[1] == EPlayerColor.BLUE && getColumn(i)[2] == EPlayerColor.BLUE) {
				hasWonColumn[i] = EPlayerColor.BLUE;
			}
		}
		//X für \
		if(getRow(0)[0] == EPlayerColor.RED && getRow(1)[1] == EPlayerColor.RED && getRow(2)[2] == EPlayerColor.RED) {
			hasWonX[0] = EPlayerColor.RED;
		}
		if(getRow(0)[0] == EPlayerColor.BLUE && getRow(1)[1] == EPlayerColor.BLUE && getRow(2)[2] == EPlayerColor.BLUE) {
			hasWonX[0] = EPlayerColor.BLUE;
		}
		//X für /
		if(getRow(0)[2] == EPlayerColor.RED && getRow(1)[1] == EPlayerColor.RED && getRow(2)[0] == EPlayerColor.RED) {
			hasWonX[0] = EPlayerColor.RED;
		}
		if(getRow(0)[2] == EPlayerColor.BLUE && getRow(1)[1] == EPlayerColor.BLUE && getRow(2)[0] == EPlayerColor.BLUE) {
			hasWonX[0] = EPlayerColor.BLUE;
		}
		
		//Abfragen
		for(int i = 0; i < 3; i++) {
			if(hasWonRow[i] == EPlayerColor.RED || hasWonColumn[i] == EPlayerColor.RED) {
				hasWon = true;
			}
			if(hasWonRow[i] == EPlayerColor.BLUE || hasWonColumn[i] == EPlayerColor.BLUE) {
				hasWon = true;
			}
			if(i < 2 && (hasWonX[i] == EPlayerColor.RED || hasWonX[i] == EPlayerColor.BLUE)) {
				hasWon = true;
			}
		}
		return hasWon;
	}
}
