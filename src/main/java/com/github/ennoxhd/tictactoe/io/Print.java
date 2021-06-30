package com.github.ennoxhd.tictactoe.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.github.ennoxhd.tictactoe.game.Game;
import com.github.ennoxhd.tictactoe.model.Player;
import com.github.ennoxhd.tictactoe.model.Turn;

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
			if(currentPlayer.isNamed()) {
				toOutput().println("Move of " + currentPlayer.name()
					+ " (" + currentPlayer.sign() + "): ");
			} else {
				toOutput().println("Move of " + currentPlayer.sign() + ": ");
			}
			toOutput().print("x - ");
			final int x = fromInput().nextInt();
			toOutput().print("y - ");
			final int y = fromInput().nextInt();
			try {
				nextTurn = new Turn(currentPlayer.sign(), x, y);
			} catch(IllegalArgumentException e) {
				nextTurn = null;
				toOutput().println("Please enter coordinates in valid range [0;2] for x and y direction!");
			}
		}
		return nextTurn;
	}

	public void postGame(Player winner) {
		if(winner != null) {
			toOutput().println("\n--- The winner of the game is: " + winner.toString() + " ---");
		} else {
			toOutput().println("\n--- There is no winner this time! ---");
		}
	}
	
	public void print(String string) {
		toOutput().print(string);
	}

	public void invalidTurn(Turn nextTurn) {
		toOutput().println("The turn " + nextTurn.toString() + " is not valid!");
	}
}
