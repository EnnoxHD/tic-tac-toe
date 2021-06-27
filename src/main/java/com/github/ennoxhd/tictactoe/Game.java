package com.github.ennoxhd.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Game {

	private Board board = null;
	private PlayerSign currentPlayer = null;
	private Map<PlayerSign, Player> players = null;
	
	public Game() {
		this("", "");
	}
	
	public Game(String nameX, String nameO) {
		board = new Board();
		currentPlayer = PlayerSign.X;
		players = new HashMap<>();
		players.put(PlayerSign.X, new Player(PlayerSign.X, nameX));
		players.put(PlayerSign.O, new Player(PlayerSign.O, nameO));
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player getPlayerBySign(PlayerSign sign) {
		return players.get(sign);
	}
	
	public PlayerSign getCurrentPlayerSign() {
		return currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return getPlayerBySign(getCurrentPlayerSign());
	}
		
	public void nextTurn(Turn turn) {
		if(turn.sign().equals(getCurrentPlayerSign()) && board.nextTurnValid(turn)) {
			board.nextTurn(turn);
			switchPlayers();
		}
	}
	
	private void switchPlayers() {
		switch(currentPlayer) {
		case X:
			currentPlayer = PlayerSign.O;
			break;
		case O:
			currentPlayer = PlayerSign.X;
			break;
		default:
			throw new IllegalArgumentException();
		}
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
			player = getPlayerBySign(sign);
		}
		return player;
	}
}
