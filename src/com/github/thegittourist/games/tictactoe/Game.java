package com.github.thegittourist.games.tictactoe;

public class Game {

	private Board board = null;
	private int currentPlayerIndex = 0;
	private Player[] players = null;
	
	public Game() {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(EPlayerSign.X);
		players[1] = new Player(EPlayerSign.O);
	}
	
	public Game(String namePlayerRed, String namePlayerBlue) {
		board = new Board();
		currentPlayerIndex = 0;
		players = new Player[2];
		players[0] = new Player(EPlayerSign.X, namePlayerRed);
		players[1] = new Player(EPlayerSign.O, namePlayerBlue);
	}
	
	public EPlayerSign getCurrentPlayerColor() {
		return players[currentPlayerIndex].getSign();
	}
	
	private Player getPlayerByColor(EPlayerSign color) throws IllegalArgumentException {
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
			EPlayerSign[] hasWonRow = new EPlayerSign[3], hasWonColumn = new EPlayerSign[3], hasWonX = new EPlayerSign[2];
			for(int i = 0; i < 3; i++) {
				//Rows
				if(board.getRow(i)[0] == EPlayerSign.X && board.getRow(i)[1] == EPlayerSign.X && board.getRow(i)[2] == EPlayerSign.X) {
					hasWonRow[i] = EPlayerSign.X;
				}
				if(board.getRow(i)[0] == EPlayerSign.O && board.getRow(i)[1] == EPlayerSign.O && board.getRow(i)[2] == EPlayerSign.O) {
					hasWonRow[i] = EPlayerSign.O;
				}
				//Columns
				if(board.getColumn(i)[0] == EPlayerSign.X && board.getColumn(i)[1] == EPlayerSign.X && board.getColumn(i)[2] == EPlayerSign.X) {
					hasWonColumn[i] = EPlayerSign.X;
				}
				if(board.getColumn(i)[0] == EPlayerSign.O && board.getColumn(i)[1] == EPlayerSign.O && board.getColumn(i)[2] == EPlayerSign.O) {
					hasWonColumn[i] = EPlayerSign.O;
				}
			}
			//X fuer \
			if(board.getRow(0)[0] == EPlayerSign.X && board.getRow(1)[1] == EPlayerSign.X && board.getRow(2)[2] == EPlayerSign.X) {
				hasWonX[0] = EPlayerSign.X;
			}
			if(board.getRow(0)[0] == EPlayerSign.O && board.getRow(1)[1] == EPlayerSign.O && board.getRow(2)[2] == EPlayerSign.O) {
				hasWonX[0] = EPlayerSign.O;
			}
			//X fuer /
			if(board.getRow(0)[2] == EPlayerSign.X && board.getRow(1)[1] == EPlayerSign.X && board.getRow(2)[0] == EPlayerSign.X) {
				hasWonX[0] = EPlayerSign.X;
			}
			if(board.getRow(0)[2] == EPlayerSign.O && board.getRow(1)[1] == EPlayerSign.O && board.getRow(2)[0] == EPlayerSign.O) {
				hasWonX[0] = EPlayerSign.O;
			}
			
			//Abfragen
			EPlayerSign color = null;
			for(int i = 0; i < 3; i++) {
				if(hasWonRow[i] == EPlayerSign.X || hasWonColumn[i] == EPlayerSign.X) {
					color = EPlayerSign.X;
				}
				if(hasWonRow[i] == EPlayerSign.O || hasWonColumn[i] == EPlayerSign.O) {
					color = EPlayerSign.O;
				}
				if(i < 2) {
					if(hasWonX[i] == EPlayerSign.X) {
						color = EPlayerSign.X;
					}
					if(hasWonX[i] == EPlayerSign.O) {
						color = EPlayerSign.O;
					}
				}
			}
			player = getPlayerByColor(color);
		}
		return player;
	}
}
