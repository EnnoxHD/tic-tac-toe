package com.github.ennoxhd.tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Print {
	
	private Scanner input;
	private PrintStream output;
	
	private Scanner fromInput() {
		return input;
	}

	private void setInput(InputStream input) {
		this.input = new Scanner(input);
	}

	private PrintStream toOutput() {
		return output;
	}

	private void setOutput(OutputStream output) {
		this.output = new PrintStream(output);
	}

	public Print(IOConfiguration config) {
		setInput(config.in());
		setOutput(config.out());
	}
	
	public Game preGame() {
		final String options = Decision.giveOptions();
		toOutput().print("""
				--- Welcome to TicTacToe ---
				Do you want to give the players some names (%s)?\s"""
				.formatted(options));
		Decision answer = null;
		while(answer == null) {
			answer = Decision.fromString(fromInput().nextLine());
			if(answer == null) {
				toOutput().print("Wrong input, please write " + options + ": ");
			}
		}
		switch(answer) {
			case YES:
				toOutput().print("Enter the name for the first player: ");
				String firstPlayerName = fromInput().next();
				toOutput().print("Enter the name for the second player: ");
				String secondPlayerName = fromInput().next();
				return new Game(firstPlayerName, secondPlayerName);
			case NO:
			default:
				return new Game();
		}
	}
	
	public Turn getTurnFromInput(Player currentPlayer) {
		toOutput().println();
		Turn nextTurn = null;
		while(nextTurn == null) {
			if(!currentPlayer.getPlayerName().equals("")) {
				toOutput().println("Move of " + currentPlayer.getPlayerName()
					+ " (" + currentPlayer.getSignString() + "): ");
			} else {
				toOutput().println("Move of " + currentPlayer.getSignString() + ": ");
			}
			toOutput().print("x - ");
			final int x = fromInput().nextInt();
			toOutput().print("y - ");
			final int y = fromInput().nextInt();
			try {
				nextTurn = new Turn(currentPlayer.getSign(), x, y);
			} catch(IllegalArgumentException e) {
				nextTurn = null;
				toOutput().println("Please enter coordinates in valid range [0;2] for x and y direction!");
			}
		}
		return nextTurn;
	}

	public void postGame(Player winner) {
		String wStr = "";
		if(!winner.getPlayerName().equals("")) {
			wStr = winner.getPlayerName();
		} else {
			wStr = winner.getSignString();
		}
		toOutput().println("\nThe winner of the game is: " + wStr);
		toOutput().println("--- --- --- --- ---");
	}
	
	public void print(String string) {
		toOutput().print(string);
	}

	public void invalidTurn(Turn nextTurn) {
		toOutput().println("The turn " + nextTurn.toString() + " is not valid!");
	}
}
