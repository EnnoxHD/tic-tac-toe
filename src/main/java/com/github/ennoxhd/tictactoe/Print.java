package com.github.ennoxhd.tictactoe;

import java.util.Scanner;

public class Print {
	
	private static final String YES = "yes";
	private static final String NO = "no";
	
	private Scanner input;
	
	public Print(Scanner input) {
		this.input = input;
	}
	
	public Game preGame() {
		final String options = giveOptions(YES, NO);
		System.out.print("--- Welcome to TicTacToe ---\n"
				+ "Do you want to give the players some names (" + options + ")? ");
		String a = "";
		int i = 0;
		do {
			if(i != 0) {
				System.out.print("Wrong input, please write " + options + ": ");
			}
			a = input.nextLine();
			i++;
		} while((!YES.equals(a)) && (!NO.equals(a)));
		if(YES.equals(a)) {
			System.out.print("Enter the name for the first player: ");
			String firstPlayerName = "";
			firstPlayerName = input.next();
			System.out.print("Enter the name for the second player: ");
			String secondPlayerName = "";
			secondPlayerName = input.next();
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
		System.out.println();
		int[] turnCoords = new int[2];
		do {
			if(!currentPlayer.getPlayerName().equals("")) {
				System.out.println("Move of " + currentPlayer.getPlayerName() + ": ");
			} else {
				System.out.println("Move of " + currentPlayer.getSignString() + ": ");
			}
			System.out.print("X - ");
			turnCoords[0] = input.nextInt();
			System.out.print("Y - ");
			turnCoords[1] = input.nextInt();
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
		System.out.println("\nThe winner of the game is: " + wStr);
		System.out.println("--- --- --- --- ---");
	}
	
	public void print(String string) {
		System.out.print(string);
	}
}
