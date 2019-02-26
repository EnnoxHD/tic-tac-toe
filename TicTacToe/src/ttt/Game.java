package ttt;

public class Game {

	private Board board = null;
	private int currentPlayerIndex = 0;
	private Player[] players = null;
	
	public Game() {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(EPlayerColor.RED);
		players[1] = new Player(EPlayerColor.BLUE);
	}
	
	public Game(String namePlayerRed, String namePlayerBlue) {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(EPlayerColor.RED, namePlayerRed);
		players[1] = new Player(EPlayerColor.BLUE, namePlayerBlue);
	}
	
	public EPlayerColor getCurrentPlayerColor() {
		return players[currentPlayerIndex].getPlayerColor();
	}
	
	private Player getPlayerByColor(EPlayerColor color) throws IllegalArgumentException {
		switch(color) {
		case RED:
			try {
				return (Player) players[0].clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		case BLUE:
			try {
				return (Player) players[1].clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public Player getCurrentPlayer() {
		return getPlayerByColor(getCurrentPlayerColor());
	}
		
	public void nextTurn(Turn turn) {
		if(turn.getPlayerColor().equals(players[currentPlayerIndex].getPlayerColor()) && board.nextTurnValid(turn)) {
			board.nextTurn(turn);
			switchPlayers();
		}
	}
	
	private void switchPlayers() {
		switch(currentPlayerIndex) {
		case 0:
			currentPlayerIndex = 1;
			break;
		case 1:
			currentPlayerIndex = 0;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public Board getBoard() {
		Board board = null;
		try {
			board = (Board) this.board.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return board;
	}
	
	public Player getWinner() {
		Player player = null;
		if(board.hasWon()) {
			EPlayerColor[] hasWonRow = new EPlayerColor[3], hasWonColumn = new EPlayerColor[3], hasWonX = new EPlayerColor[2];
			for(int i = 0; i < 3; i++) {
				//Rows
				if(board.getRow(i)[0] == EPlayerColor.RED && board.getRow(i)[1] == EPlayerColor.RED && board.getRow(i)[2] == EPlayerColor.RED) {
					hasWonRow[i] = EPlayerColor.RED;
				}
				if(board.getRow(i)[0] == EPlayerColor.BLUE && board.getRow(i)[1] == EPlayerColor.BLUE && board.getRow(i)[2] == EPlayerColor.BLUE) {
					hasWonRow[i] = EPlayerColor.BLUE;
				}
				//Columns
				if(board.getColumn(i)[0] == EPlayerColor.RED && board.getColumn(i)[1] == EPlayerColor.RED && board.getColumn(i)[2] == EPlayerColor.RED) {
					hasWonColumn[i] = EPlayerColor.RED;
				}
				if(board.getColumn(i)[0] == EPlayerColor.BLUE && board.getColumn(i)[1] == EPlayerColor.BLUE && board.getColumn(i)[2] == EPlayerColor.BLUE) {
					hasWonColumn[i] = EPlayerColor.BLUE;
				}
			}
			//X für \
			if(board.getRow(0)[0] == EPlayerColor.RED && board.getRow(1)[1] == EPlayerColor.RED && board.getRow(2)[2] == EPlayerColor.RED) {
				hasWonX[0] = EPlayerColor.RED;
			}
			if(board.getRow(0)[0] == EPlayerColor.BLUE && board.getRow(1)[1] == EPlayerColor.BLUE && board.getRow(2)[2] == EPlayerColor.BLUE) {
				hasWonX[0] = EPlayerColor.BLUE;
			}
			//X für /
			if(board.getRow(0)[2] == EPlayerColor.RED && board.getRow(1)[1] == EPlayerColor.RED && board.getRow(2)[0] == EPlayerColor.RED) {
				hasWonX[0] = EPlayerColor.RED;
			}
			if(board.getRow(0)[2] == EPlayerColor.BLUE && board.getRow(1)[1] == EPlayerColor.BLUE && board.getRow(2)[0] == EPlayerColor.BLUE) {
				hasWonX[0] = EPlayerColor.BLUE;
			}
			
			//Abfragen
			EPlayerColor color = null;
			for(int i = 0; i < 3; i++) {
				if(hasWonRow[i] == EPlayerColor.RED || hasWonColumn[i] == EPlayerColor.RED) {
					color = EPlayerColor.RED;
				}
				if(hasWonRow[i] == EPlayerColor.BLUE || hasWonColumn[i] == EPlayerColor.BLUE) {
					color = EPlayerColor.BLUE;
				}
				if(i < 2) {
					if(hasWonX[i] == EPlayerColor.RED) {
						color = EPlayerColor.RED;
					}
					if(hasWonX[i] == EPlayerColor.BLUE) {
						color = EPlayerColor.BLUE;
					}
				}
			}
			player = getPlayerByColor(color);
		}
		return player;
	}
}
