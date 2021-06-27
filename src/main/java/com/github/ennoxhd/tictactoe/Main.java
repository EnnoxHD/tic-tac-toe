package com.github.ennoxhd.tictactoe;

import com.github.ennoxhd.tictactoe.game.Game;
import com.github.ennoxhd.tictactoe.io.IOConfiguration;
import com.github.ennoxhd.tictactoe.io.Print;
import com.github.ennoxhd.tictactoe.model.Turn;

public class Main {

	public static void main(String[] args) {
		IOConfiguration ioConfig = new IOConfiguration(System.in, System.out);
		Print print = new Print(ioConfig);
		Game game = print.preGame();
		while(!game.getBoard().hasWon()) {
			Turn nextTurn = null;
			print.print(game.getBoard().toString());
			while(nextTurn == null) {
				nextTurn = print.getTurnFromInput(game.getCurrentPlayer());
				if(!game.getBoard().nextTurnValid(nextTurn)) {
					print.invalidTurn(nextTurn);
					nextTurn = null;
				}
			}
			game.nextTurn(nextTurn);
		}
		print.postGame(game.getWinner());
	}
}
