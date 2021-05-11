package com.github.ennoxhd.tictactoe;

import java.util.Scanner;

public class Print {

	public static final Scanner input = new Scanner(System.in);
	
	public static final String YES = "yes";
	public static final String NO = "no";
	
	public static boolean preGame() {
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
			return true;
		} else {
			return false;
		}
	}
	
	public static String giveOptions(String... string) {
		String options = "";
		for(int i = 0; i < string.length; i++) {
			options += "'" + string[i].toString() + "'";
			if(i < string.length - 1) options += " or ";
		}
		return options;
	}	
	
	public static String[] getNames() {
		String[] names = new String[2];
		System.out.print("Enter the name for the first player: ");
		names[0] = input.next();
		System.out.print("Enter the name for the second player: ");
		names[1] = input.next();
		return names;
	}
	
	public static void printBoard(Board board) {
		String out = "";
		System.out.println("-------");
		for(int i = 0; i < 3; i++) {
			System.out.print("|");
			for(int k = 0; k < 3; k++) {
				if(board.getRow(i)[k] == PlayerSign.X) {
					out = "X";
				} else if(board.getRow(i)[k] == PlayerSign.O) {
					out = "O";
				} else if(board.getRow(i)[k] == null) {
					out = " ";
				} else {
					throw new IllegalArgumentException();
				}
				System.out.print(out + "|");
			}
			System.out.print("\n");
		}
		System.out.println("-------");
	}
	
	public static Turn getTurnFromInput(Player currentPlayer) {
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

	public static void postGame(Player winner) {
		String wStr = "";
		if(!winner.getPlayerName().equals("")) {
			wStr = winner.getPlayerName();
		} else {
			wStr = winner.getSignString();
		}
		System.out.println("\nThe winner of the game is: " + wStr);
		System.out.println("--- --- --- --- ---");
	}
}
