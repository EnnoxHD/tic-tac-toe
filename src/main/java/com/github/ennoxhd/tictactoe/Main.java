package com.github.ennoxhd.tictactoe;

public class Main {

	public static void main(String[] args) {
		Game game = Print.preGame();
		Turn nextTurn = null;
		while(!game.getBoard().hasWon()) {
			Print.printBoard(game.getBoard());
			do {
				nextTurn = Print.getTurnFromInput(game.getCurrentPlayer());
			} while(!game.getBoard().nextTurnValid(nextTurn));
			game.nextTurn(nextTurn);
		}
		Print.postGame(game.getWinner());
	}
}
