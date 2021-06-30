package com.github.ennoxhd.tictactoe.game;

import java.util.HashMap;
import java.util.Map;

import com.github.ennoxhd.tictactoe.model.Player;
import com.github.ennoxhd.tictactoe.model.PlayerSign;
import com.github.ennoxhd.tictactoe.model.Turn;

public class Game {

	private Board board = null;
	private PlayerSign currentPlayer = null;
	private Map<PlayerSign, Player> players = null;
	private int turnCounter = 0;
	
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
	
	private void increaseTurnCounter() {
		turnCounter++;
	}
	
	public boolean isOver() {
		return turnCounter >= 9;
	}
	
	Player getPlayerBySign(PlayerSign sign) {
		if(sign == null)
			return null;
		return players.get(sign);
	}
	
	PlayerSign getCurrentPlayerSign() {
		return currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		return getPlayerBySign(getCurrentPlayerSign());
	}
		
	public void nextTurn(Turn turn) {
		if(turn.sign().equals(getCurrentPlayerSign())
				&& getBoard().nextTurnValid(turn)) {
			if(getBoard().nextTurn(turn)) {
				increaseTurnCounter();
				switchPlayers();
			}
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
		return getPlayerBySign(getBoard().getWinner());
	}
	
	public boolean hasWon() {
		return getWinner() != null;
	}
}
