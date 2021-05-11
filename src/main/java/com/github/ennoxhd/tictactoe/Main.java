package com.github.ennoxhd.tictactoe;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try(final Scanner input = new Scanner(System.in)) {
			Print print = new Print(input);
			Game game = print.preGame();
			Turn nextTurn = null;
			while(!game.getBoard().hasWon()) {
				print.printBoard(game.getBoard());
				do {
					nextTurn = print.getTurnFromInput(game.getCurrentPlayer());
				} while(!game.getBoard().nextTurnValid(nextTurn));
				game.nextTurn(nextTurn);
			}
			print.postGame(game.getWinner());
		}
	}
}
