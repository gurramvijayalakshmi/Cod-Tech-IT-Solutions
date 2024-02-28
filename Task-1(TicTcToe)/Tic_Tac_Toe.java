import java.util.*;
public class Tic_Tac_Toe {
	static String[] board;
	static String turn;
	
	static String CheckWinner(){
		for(int a=0;a<8;a++){
			String line= null;
			switch(a){
				case 0:
					line=board[0]+board[1]+board[2];
					break;
				case 1:
					line=board[3]+board[4]+board[5];
					break;
				case 2:
					line=board[6]+board[7]+board[8];
					break;
				case 3:
					line=board[0]+board[4]+board[8];
					break;
				case 4:
					line=board[0]+board[3]+board[6];
					break;
				case 5:
					line=board[1]+board[4]+board[7];
					break;
				case 6:
					line=board[2]+board[4]+board[6];
					break;
				case 7:
					line=board[2]+board[5]+board[8];
					break;
			}
			if (line.equals("XXX")){
				return "X";
			}
			else if(line.equals("OOO")){
				return "O";
			}
		}
		for(int a=0;a<9;a++){
			if(Arrays.asList(board).contains(String.valueOf(a+1))){
				break;
			}
			else if(a==8){
				return "draw";
			}
		}
		
		System.out.println(turn+" 's turn; Enter a slot number to place "+turn+" in: ");
		return null;
		
	}
	
	static void printBoard(){
		System.out.println("|---|---|---|");
		System.out.println("| "+board[0]+" | "+board[1]+" | "+board[2]+" |");
		System.out.println("|---|---|---|");
		System.out.println("| "+board[3]+" | "+board[4]+" | "+board[5]+" |");
		System.out.println("|---|---|---|");
		System.out.println("| "+board[6]+" | "+board[7]+" | "+board[8]+" |");
		System.out.println("|---|---|---|");
		
	}
	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		board = new String[9];
		turn = "X";
		String winner = null;
		
		for(int a=0;a<9;a++){
			board[a]=String.valueOf(a+1);
		}
		
		System.out.print(" Welcome to Tic Tac Toe");
		
		printBoard();
		
		System.out.println("X will play first,Enter slot number to place X in : ");
		
		while(winner == null){
			int input;
			try{
				input=s.nextInt();
				if(!(input >0 && input <= 9)){
					System.out.println("Invalid input (input must be in between 1-9) Re-Enter the slot number :");
					continue;
				}
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input (input must be in between 1-9) Re-Enter the slot number :");
				continue;
			}
			if(board[input - 1].equals(String.valueOf(input))){
				board[input-1]= turn;
				
				if (turn.equals("X")){
					turn ="O";
				}
				else{
					turn="X";
				}
				printBoard();
				winner=CheckWinner();
			}
			else {
				System.out.println("Invalid input (input must be in between 1-9) Re-Enter the slot number :");
				
			}
		}
		if (winner.equalsIgnoreCase("draw")){
			System.out.println("It's a draw ! Thanks For Playing");
		}
		else{
			System.out.println("Congratulations! "+winner+" 's have won! the Match");
		}
		
		s.close();
	}

}
