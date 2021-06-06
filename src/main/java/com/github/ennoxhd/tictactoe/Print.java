package com.github.ennoxhd.tictactoe;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Print {
	
	private static final String YES = "yes";
	private static final String NO = "no";
	
	private Scanner input;
	private PrintStream output;
	
	private Scanner getInput() {
		return input;
	}

	private void setInput(Scanner input) {
		this.input = input;
	}

	private PrintStream getOutput() {
		return output;
	}

	private void setOutput(PrintStream output) {
		this.output = output;
	}

	public Print(InputStream in, OutputStream out) {
		setInput(new Scanner(in));
		setOutput(new PrintStream(out));
	}
	
	public Game preGame() {
		final String options = giveOptions(YES, NO);
		getOutput().print("--- Welcome to TicTacToe ---\n"
				+ "Do you want to give the players some names (" + options + ")? ");
		String a = "";
		int i = 0;
		do {
			if(i != 0) {
				getOutput().print("Wrong input, please write " + options + ": ");
			}
			a = getInput().nextLine();
			i++;
		} while((!YES.equals(a)) && (!NO.equals(a)));
		if(YES.equals(a)) {
			getOutput().print("Enter the name for the first player: ");
			String firstPlayerName = "";
			firstPlayerName = getInput().next();
			getOutput().print("Enter the name for the second player: ");
			String secondPlayerName = "";
			secondPlayerName = getInput().next();
			return new Game(firstPlayerName, secondPlayerName);
		} else {
			return new Game();
		}
	}
	
	public String giveOptions(String... string) {
		String options = "";
		for(int i = 0; i < string.length; i++) {
			options += "'" + string[i].toString() + "'";
			if(i < string.length - 1) options += " or ";
		}
		return options;
	}
	
	public Turn getTurnFromInput(Player currentPlayer) {
		getOutput().println();
		int[] turnCoords = new int[2];
		boolean gotInvalidInput = false;
		do {
			if(gotInvalidInput) {
				getOutput().println("Please enter coordinates 0 or 1 or 2 for x and y direction!");
			} else {
				gotInvalidInput = true;
			}
			if(!currentPlayer.getPlayerName().equals("")) {
				getOutput().println("Move of " + currentPlayer.getPlayerName()
					+ " (" + currentPlayer.getSignString() + "): ");
			} else {
				getOutput().println("Move of " + currentPlayer.getSignString() + ": ");
			}
			getOutput().print("x - ");
			turnCoords[0] = getInput().nextInt();
			getOutput().print("y - ");
			turnCoords[1] = getInput().nextInt();
		} while(turnCoords[0] < 0 || turnCoords[0] > 2 || turnCoords[1] < 0 || turnCoords[1] > 2);
		return new Turn(currentPlayer.getSign(), turnCoords[0], turnCoords[1]);
	}

	public void postGame(Player winner) {
		String wStr = "";
		if(!winner.getPlayerName().equals("")) {
			wStr = winner.getPlayerName();
		} else {
			wStr = winner.getSignString();
		}
		getOutput().println("\nThe winner of the game is: " + wStr);
		getOutput().println("--- --- --- --- ---");
	}
	
	public void print(String string) {
		getOutput().print(string);
	}

	public void invalidTurn(Turn nextTurn) {
		getOutput().println("The turn " + nextTurn.toString() + " is not valid!");
	}
}
