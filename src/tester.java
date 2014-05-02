import java.util.Scanner;

public class tester {
	/*
	 * This java file contains tester and all methods that are required
	 */
	// Scanner scan = new Scanner(System.in);

	public static void main(String args[]) {
		Square move = new Square();
		int players = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input the size of board:");
		int size = scan.nextInt();
		char[][] board = new char[size * 4 + 1][size * 4 + 1];
		int chips = size * size;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++) {
				if (i % 4 == 0)
					board[i][j] = '*';
				if (j % 4 == 0)
					board[i][j] = '*';
			}

		while (!hasWon(board, move) && !draw(chips)) {
			// while (chips != 2) {
			display(board);
			getMove(players, move);
			if (isLegal(board, move)) {
				players += 1;
				makeMove(board, move);
				notify(board, move);
				chips -= 1;
			} else {
				move.reset();
				System.out
						.print("============\nIllegal movement!\n==========\n");
			}

		}
		display(board);

	}

	static void getMove(int player, Square square) {

		Scanner movescan = new Scanner(System.in);
		if (player % 2 == 0) {
			System.out.println("White color: ");
			square.color = 'w';
		} else {
			System.out.println("Black color:");
			square.color = 'b';
		}
		System.out.println("Specify the square");
		System.out.println("colum?");
		square.colum = movescan.nextInt();
		System.out.println("row?");
		square.row = movescan.nextInt();

	}

	static boolean isLegal(char[][] board, Square square) {

		if (square.row <= (board.length - 1) / 4 && square.row > 0
				&& square.colum <= (board.length - 1) / 4 && square.colum > 0
				&& board[square.row * 4 - 2][square.colum * 4 - 2] != 'w'
				&& board[square.row * 4 - 2][square.colum * 4 - 2] != 'b')
			return true;

		return false;
	}

	static void makeMove(char[][] board, Square square) {
		board[square.row * 4 - 2][square.colum * 4 - 2] = square.color;
	}

	static boolean draw(int chips) {
		if (chips == 0) {
			System.out.println("**********\nDraw!\n**********\n");
			return true;
		}
		return false;
	}

	static boolean hasWon(char[][] board, Square square) {

		int row = square.row;
		int colum = square.colum;
		int counter = 0;
		// if ((board.length - 1 / 4) >= 9) {
		char checkWin[] = new char[9];

		// ////////////////////// CASE1 "\" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0 && colum > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
			colum--;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4
					&& colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
			colum++;

		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter >= 5) {
				System.out.println("**********\n Player:" + square.color
						+ " Win!\n**********\n");
				return true;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// //////////////////////CASE2 "/" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0 && colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
			colum++;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4 && colum > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
			colum--;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter >= 5) {
				System.out.println("**********\n Player:" + square.color
						+ " Win!\n**********\n");
				return true;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// ////////////////////// CASE3 "-" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (colum > 0) {
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			}
			colum--;
		}

		colum = square.colum;
		for (int i = 4; i < 9; i++) {
			if (colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			colum++;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter >= 5) {
				System.out.println("**********\n Player:" + square.color
						+ " Win!\n**********\n");
				return true;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// //////////////////// CASE4 "|" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
		}
		row = square.row;

		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter >= 5) {
				System.out.println("**********\n Player:" + square.color
						+ " Win!\n**********\n");
				return true;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;

		// }
		return false;
	}

	static void display(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}

	}

	static void notify(char[][] board, Square square) {
		int row = square.row;
		int colum = square.colum;
		int counter = 0;
		// if ((board.length - 1 / 4) >= 9) {
		char checkWin[] = new char[9];

		// ////////////////////// CASE1 "\" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0 && colum > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
			colum--;
		}
		row = square.row;
		colum = square.colum;
		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4
					&& colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
			colum++;

		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter == 4)
			{
				System.out.println("3###################\n Player:"
						+ square.color
						+ " is going to Win!\n###################\n");
				break;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		counter = 0;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// //////////////////////CASE2 "/" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0 && colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
			colum++;
		}
		row = square.row;
		colum = square.colum;
		counter = 0;
		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4 && colum > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
			colum--;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter == 4)
			{
				System.out.println("3###################\n Player:"
						+ square.color
						+ " is going to Win!\n###################\n");
				break;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		counter = 0;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// ////////////////////// CASE3 "-" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (colum > 0) {
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			}
			colum--;
		}

		colum = square.colum;
		for (int i = 4; i < 9; i++) {
			if (colum <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			colum++;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter == 4) {
				
				System.out.println("3###################\n Player:"
						+ square.color
						+ " is going to Win!\n###################\n");
				System.out.print(counter);
				break;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;
		counter = 0;
		for (int i = 0; i < 9; i++)
			checkWin[i] = ' ';
		// //////////////////// CASE4 "|" ///////////////
		// copy the chips on board into a char array
		for (int i = 4; i >= 0; i--) {
			if (row > 0)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row--;
		}
		row = square.row;

		for (int i = 4; i < 9; i++) {
			if (row <= (board.length - 1) / 4)
				checkWin[i] = board[row * 4 - 2][colum * 4 - 2];
			row++;
		}
		// check if there is a series of five
		for (int i = 0; i < 9; i++) {
			if (checkWin[i] == square.color)
				counter++;
			else if (counter == 4 )
			{
				System.out.println("4###################\n Player:"
						+ square.color
						+ " is going to Win!\n###################\n");
				break;
			} else
				counter = 0;
		}
		row = square.row;
		colum = square.colum;

		// }

	}

}
