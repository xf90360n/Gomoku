import java.util.Scanner;	//needed for input of the user

class Notify

	{

		public static void main(String args[]) 
		
			{
		
				final int BOARD_SIZE = 8;	// board[][] is BOARD_SIZE x BOARD_SIZE; must be > 1
				
				final int NUMBER_IN_A_ROW = 5;	// 2<= NUMBER_IN_A_ROW <= BOARD_SIZE
			
				Square move = new Square();	
			
				int players = 0;		//odd for black, white even
			
				char[][] board = new char[BOARD_SIZE * 4 + 1][BOARD_SIZE * 4 + 1];		// creates top and left side of square
			
				int chips = BOARD_SIZE*BOARD_SIZE;		//9 positions to place counter, for whole square
			
				for (int i = 0; i < board.length; i++)		//creating row, row number
				
				for (int j = 0; j < board.length; j++)		//creating column, column number 
				
					{	
						
						board[i][j] = ' '; 	//to change char default
					
						if (i % 4 == 0) 	//gives 4 spaces
					
							board[i][j] = '*';
				
						if (j % 4 == 0)		//gives 4 spaces to *
					
							board[i][j] = '*';
						
						
					}

						while(!hasWon(board,move) && ! draw(chips))		//keep continuing the game until hasWon or the game the is draw
						
							{	
						
								display(board);		//calls method in main file, initialize methods in different files (ex square)
								
								willLoose(board, move);

								getMove(players, move);		//asking for move of player
			
								if (isLegal(board, move))	//if move is legal
				
								players += 1;		//go to next player, keeps incrementing for player so player 1 is odd, player 2 even
							
						else
					
							{
						
								System.out.print("======================\nIllegal movement!\n=======================\n");
				 
								continue;		//end loop go to next loop
						
							}	
								
								makeMove(board, move);		//make the move for player 1 to put w in square
						
								chips -= 1;		//number of chips - 1 (ex 9, 1 space reserved for 'w')

							}
						
								display(board);
			
			}
		
	//end of main method
		

	//methods
		
	static void getMove(int player, Square square) 
		
		{
		
			Scanner movescan = new Scanner(System.in);		//asking for column and row
		
			if (player % 2 == 0) 	//asking which player is playing
			
				{	
			
					System.out.println("Player 1: (White color) ");	//player 1
					
					square.color = 'w';
		
				} 
			
				else
				
				{
					
					System.out.println("Player 2: (Black color)");		//player 2
					
					square.color = 'b';
				}
					System.out.println("Specify the square");
		
					System.out.println("colum?");
		
					square.colum = movescan.nextInt();
	
					System.out.println("row?");
	
					square.row = movescan.nextInt();

		}

	static boolean isLegal(char[][] board, Square square) 
	
		{
		
			if (	square.row <= (board.length-1)/4	//if row is less than or equal to the size of the board (squares) left to right (row)
					
					&& square.row >0
					
					&& square.colum <= (board.length-1)/4 		//column is less than or equal to square - 1 /4
					
					&& square.colum > 0
					
					&& board[square.row*4-2][square.colum*4-2] != 'w'	//is there already a chip in the square, checks the row you gave, then checks square
					
					&& board[square.row*4-2][square.colum*4-2] != 'b')	// inputs w/b in middle cause of method
				
				return true;
		
			return false;
		}

	static void makeMove(char[][] board, Square square)		//make move method
	
		{	
		
			board[square.row*4-2][square.colum*4-2] = square.color;		//puts color in square w or b
		}

	static boolean draw(int chips) 	//draw method
		
		{	
		
			if (chips == 0)		//checking if draw
			
			{
			
				System.out.println("Draw");
			
				return true;		//game is draw
			
			}
			
			return false;		//game is not draw
			
		}

	static boolean hasWon(char[][] board, Square square)	//haswon method
	
		{	
	
		
			return false;
		
		}

	static void display(char[][] board)		//dispays board
	
		{	

			for (int i = 0; i < board.length; i++) 
				
				{
			
					for (int j = 0; j < board.length; j++)
						
						System.out.print(board[i][j] + "  ");
			
					System.out.println();
				}
			
		}
			
	static void willLoose (char[][]board, Square square)
		
		{
		int row= square.row;
		int colum = square.colum;
		
		int counter_diagLeft=0;
		int counter_reverseDL=0;
		
		int counter_Vert=0;
		int counter_reverseV=0;
		
		int counter_Horizont=0;
		int counter_reverseH=0;
		
		int counter_diagRight=0;
		int counter_reverseDR=0;
		
			boolean willLoose = false;
			
			while(row >=1 && colum >= 1 &&		//check diagonally reverse left (\)
					
					board[row*4-2][colum*4-2]=='b' && counter_reverseDL < 4)
				
				{
				
					counter_reverseDL++;
					row--;
					colum--;
					
				}	
			row = square.row;
			colum = square.colum;
			
			while(row <=8 && colum <=8 &&			//to check diagonally to right (\)
					
					board[row*4-2][colum*4-2]=='b' && counter_diagRight < 4)
				
				{
				
					counter_diagRight++;
					row++;
					colum++;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(row <=8 && colum >= 1 &&		//check diagonally to left (/)
					
					board[row*4-2][colum*4-2]=='b' && counter_diagLeft < 4)
				
				{
				
					counter_diagLeft++;
					row++;
					colum--;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(row >=1 && colum <= 8 &&		//check diagonally to right reverse (/)
					
					board[row*4-2][colum*4-2]=='b' && counter_reverseDR < 4)
				
				{
				
					counter_reverseDR++;
					row--;
					colum++;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(row <=8 &&			//to check vertically
					
					board[row*4-2][colum*4-2]=='b' && counter_Vert < 4)
				
				{
				
					counter_Vert++;
					row++;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(row >=1 &&			//to check reverse vertically
					
					board[row*4-2][colum*4-2]=='b' && counter_reverseV < 4)
				
				{
				
					counter_reverseV++;
					row--;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(colum <=8 &&			//to check horizontally
					
					board[row*4-2][colum*4-2]=='b' && counter_Horizont < 4)
				
				{
				
					counter_Horizont++;
					colum++;
					
				}	
			
			row = square.row;
			colum = square.colum;
			
			while(colum >=1 &&			//to check reverse horizontally
					
					board[row*4-2][colum*4-2]=='b' && counter_reverseH < 4)
				
				{
				
					counter_reverseH++;
					colum--;
					
				}	
			
			
					
					if(counter_diagRight ==4 || counter_reverseDR ==4 
						|| counter_Horizont==4 || counter_reverseH==4  
						|| counter_Vert==4 || counter_reverseV==4
						|| counter_diagLeft==4 || counter_reverseDL==4)  
			
				willLoose = true;
				
			if(willLoose)
				
			System.out.print("P1 watch your next move!");
	

		}



	}
