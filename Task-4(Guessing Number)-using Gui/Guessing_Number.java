import java.util.*;
public class Guessing_Number {
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in);
		
		Random ran= new Random();
		 int lb=1;
		 int ub=100;
		 int numToGuess= ran.nextInt(ub-lb+1)+lb;
		 int maxAttempts =10;
		 int attempts=0;
		 int guess;
		 
		 System.out.println("Welcome to the Number Guessing Game :");
		 System.out.println(" I have selected a number between "+ lb+ "and "+ub+". try to Guess it :>");
		 System.out.println("Guessing Game Starts Now :>");
		 System.out.println(" ComeOn Have a  :>");
		 while (attempts < maxAttempts){
			
			 System.out.println("Guess Which number I had Selected");
			 
			 guess=sc.nextInt();
			 attempts++;
			 
			 if(guess < numToGuess){
				 System.out.println(" Awucchhhh...!!! Your Guess is Too Low..! :< comeon Take Another Dude :");
				 
			 }
			 else if(guess > numToGuess)
			 {
				 System.out.println(" Awucchhhh...!!! Your Guess is Too High Bro..! :<  comeon Take Another Dude :");
				 
			 }
			 else {
				 System.out.println("Hhhuuurrrraaayyyy Finallyy The Guess is correctt Man ...!!! You WON The Game :>");
				 break;
			 }
		 }
		 sc.close();
	}

}
