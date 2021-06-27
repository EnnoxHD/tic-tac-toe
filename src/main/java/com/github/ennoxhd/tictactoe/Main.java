package com.github.ennoxhd.tictactoe;

import com.github.ennoxhd.tictactoe.model.Turn;

public class Main {

	public static void main(String[] args) {
		IOConfiguration ioConfig = new IOConfiguration(System.in, System.out);
		Print print = new Print(ioConfig);
		Game game = print.preGame();
		Turn nextTurn = null;
		while(!game.getBoard().hasWon()) {
			print.print(game.getBoard().toString());
			boolean invalidTurn = false;
			do {
				if(invalidTurn) {
					print.invalidTurn(nextTurn);
				} else {
					invalidTurn = true;
				}
				nextTurn = print.getTurnFromInput(game.getCurrentPlayer());
			} while(!game.getBoard().nextTurnValid(nextTurn));
			game.nextTurn(nextTurn);
		}
		print.postGame(game.getWinner());
	}
}
