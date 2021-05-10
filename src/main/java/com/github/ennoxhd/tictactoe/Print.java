package com.github.ennoxhd.tictactoe;

import java.util.Scanner;

public class Print {

	public static final Scanner input = new Scanner(System.in);
	
	public static final String YES = "ja";
	public static final String NO = "nein";
	
	public static boolean preGame() {
		System.out.print("--- Willkommen bei TicTacToe ---\n"
				+ "Möchten Sie für die Spieler Namen vergeben (" + concatOptions(YES, NO) + ")? ");
		String a = "";
		int i = 0;
		do {
			if(i != 0) {
				System.out.print("Falsche Eingabe, bitte " + concatOptionsOr(YES, NO) + " eingeben: ");
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
	
	public static String concatOptions(Object... objects) {
		String options = "";
		for(int i = 0; i < objects.length; i++) {
			options += objects[i].toString();
			if(i < objects.length-1)
				options += " / ";
		}
		return options;
	}
	
	public static String concatOptionsOr(Object... objects) {
		String options = "";
		for(int i = 0; i < objects.length; i++) {
			options += "'" + objects[i].toString() + "'";
			if(i < objects.length-1)
				options += " oder ";
		}
		return options;
	}	
	
	public static String[] getNames() {
		String[] names = new String[2];
		System.out.print("Geben Sie den Namen des ersten Spielers an: ");
		names[0] = input.next();
		System.out.print("Geben Sie den Namen des zweiten Spielers an: ");
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
				System.out.println("Zug von " + currentPlayer.getPlayerName() + ": ");
			} else {
				System.out.println("Zug von " + currentPlayer.getSignString() + ": ");
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
		System.out.println("\nDer Gewinner des Spiels ist " + wStr);
		System.out.println("--- --- --- --- ---");
	}
}
