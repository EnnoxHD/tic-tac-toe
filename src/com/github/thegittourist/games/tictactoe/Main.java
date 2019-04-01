package com.github.thegittourist.games.tictactoe;

public class Main {

	public static void main(String[] args) {
		Game game = null;
		boolean playerNames = Print.preGame();
		if(playerNames) {
			String[] names = Print.getNames();
			game = new Game(names[0], names[1]);
		} else {
			game = new Game();
		}
		Turn nextTurn = null;
		while(!game.getBoard().hasWon()) {
			Print.printBoard(game.getBoard());
			do {
				nextTurn = Print.getTurnFromInput(game.getCurrentPlayer());
			} while(!game.getBoard().nextTurnValid(nextTurn));
			game.nextTurn(nextTurn);
		}
		Print.endOfGame(game.getWinner());
	}
}
