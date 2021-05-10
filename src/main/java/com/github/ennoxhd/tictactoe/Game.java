package com.github.ennoxhd.tictactoe;

public class Game {

	private Board board = null;
	private int currentPlayerIndex = 0;
	private Player[] players = null;
	
	public Game() {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(PlayerSign.X);
		players[1] = new Player(PlayerSign.O);
	}
	
	public Game(String namePlayerRed, String namePlayerBlue) {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(PlayerSign.X, namePlayerRed);
		players[1] = new Player(PlayerSign.O, namePlayerBlue);
	}
	
	public PlayerSign getCurrentPlayerColor() {
		return players[currentPlayerIndex].getSign();
	}
	
	private Player getPlayerByColor(PlayerSign color) throws IllegalArgumentException {
		switch(color) {
		case X:
			try {
				return (Player) players[0].clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		case O:
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
		if(turn.getSign().equals(players[currentPlayerIndex].getSign()) && board.nextTurnValid(turn)) {
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
		return new Board(board);
	}
	
	public Player getWinner() {
		Player player = null;
		if(board.hasWon()) {
			PlayerSign[] hasWonRow = new PlayerSign[3], hasWonColumn = new PlayerSign[3], hasWonX = new PlayerSign[2];
			for(int i = 0; i < 3; i++) {
				//Rows
				if(board.getRow(i)[0] == PlayerSign.X && board.getRow(i)[1] == PlayerSign.X && board.getRow(i)[2] == PlayerSign.X) {
					hasWonRow[i] = PlayerSign.X;
				}
				if(board.getRow(i)[0] == PlayerSign.O && board.getRow(i)[1] == PlayerSign.O && board.getRow(i)[2] == PlayerSign.O) {
					hasWonRow[i] = PlayerSign.O;
				}
				//Columns
				if(board.getColumn(i)[0] == PlayerSign.X && board.getColumn(i)[1] == PlayerSign.X && board.getColumn(i)[2] == PlayerSign.X) {
					hasWonColumn[i] = PlayerSign.X;
				}
				if(board.getColumn(i)[0] == PlayerSign.O && board.getColumn(i)[1] == PlayerSign.O && board.getColumn(i)[2] == PlayerSign.O) {
					hasWonColumn[i] = PlayerSign.O;
				}
			}
			//X fuer \
			if(board.getRow(0)[0] == PlayerSign.X && board.getRow(1)[1] == PlayerSign.X && board.getRow(2)[2] == PlayerSign.X) {
				hasWonX[0] = PlayerSign.X;
			}
			if(board.getRow(0)[0] == PlayerSign.O && board.getRow(1)[1] == PlayerSign.O && board.getRow(2)[2] == PlayerSign.O) {
				hasWonX[0] = PlayerSign.O;
			}
			//X fuer /
			if(board.getRow(0)[2] == PlayerSign.X && board.getRow(1)[1] == PlayerSign.X && board.getRow(2)[0] == PlayerSign.X) {
				hasWonX[0] = PlayerSign.X;
			}
			if(board.getRow(0)[2] == PlayerSign.O && board.getRow(1)[1] == PlayerSign.O && board.getRow(2)[0] == PlayerSign.O) {
				hasWonX[0] = PlayerSign.O;
			}
			
			//Abfragen
			PlayerSign color = null;
			for(int i = 0; i < 3; i++) {
				if(hasWonRow[i] == PlayerSign.X || hasWonColumn[i] == PlayerSign.X) {
					color = PlayerSign.X;
				}
				if(hasWonRow[i] == PlayerSign.O || hasWonColumn[i] == PlayerSign.O) {
					color = PlayerSign.O;
				}
				if(i < 2) {
					if(hasWonX[i] == PlayerSign.X) {
						color = PlayerSign.X;
					}
					if(hasWonX[i] == PlayerSign.O) {
						color = PlayerSign.O;
					}
				}
			}
			player = getPlayerByColor(color);
		}
		return player;
	}
}
