/** 
 * Class RockPaperScissors.  Plays repeated games of Rock Paper Scissors with a user 
 * @author Your Name
 * @date The date
 * */

/* Name: Sabrina Lee
 * PID: A91066880
 * LogIn: SAL040@ucsd.edu
 * Due Date: October 6, 2017
 * File: RockPaperScissors.java
 * 
 * Description: A computer game that plays
 * Rock-Paper-Scissors depending on what the
 * user inputs determines the winner of the game 
 * 
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors
{
  
  public static void main( String[] args )
  {
	//initial length of the user input  
    int initialCapacity = 5;
    //player moves count
    int movesCount = 0;
    //number of times player won
    int playerWin = 0;
    //number of times system won
    int systemWin = 0;
    //number of times tied
    int tiedGames = 0;
    //winner interger to add to score
    int winnerInt = 0;
    
    // Store the user's move history
    String[] userMoves = new String[initialCapacity];  
    // Store the System's move history
    LinkedList<String> systemMoves = new LinkedList<String>();
    
    //Scanner to read the input of the user
    Scanner scanner = new Scanner(System.in);
    //playerMove string that reads the player input
    String userMove = new String();
    
    //Random number generator to determine the system move
    Random randomNumber = new Random();
    //System play string
    String systemMove = new String();

   
  //intro message to the game
	 System.out.println("Let's play! What's your move? " +'\n'
			 + "(r=rock, p=paper, s=scissors or q to quit)");
    //Read what the user input
    userMove = scanner.nextLine();

   
    //While the user did not input q
    while(!"q".equals(userMove)){
    // TODO: Write the code to play the game as specified by the writeup

        //valid input to play the game
        if ("r".equals(userMove) || "p".equals(userMove) ||
    			"s".equals(userMove)){
    	//Creating the system play number constraint (0R, 1P, 2S)
        int systemPlayInt = randomNumber.nextInt(3);
        //turning system move into a string
        systemMove = systemMoveString(systemPlayInt);
    
		    //adding the user's move into the string array
        	userMoves[movesCount] = userMove;
		    movesCount++;
		    
		    for (int i=0; i < initialCapacity; i++){
			    //checking if userMoves Array needs to be modified
			    if((movesCount+1) > initialCapacity){
			    	//doubling the array size
			    	initialCapacity = initialCapacity*2;
			    	//returning new string array with the old moves and more room
			    	userMoves = newUserMoves(userMoves, initialCapacity);
			    }
		     }
		    
		    //adding move to the systemMoves LinkedList
		    systemMoves.addLast(systemMove);
		 
		    //adding score to the winner of one game
		    //printing who won from the method
		    winnerInt = winnerInt(userMove,systemMove);
		    if(winnerInt == 0)
		    	playerWin++;
		    else if (winnerInt == 1)
		    	systemWin++;
		    else
		    	tiedGames++;
		    		 	    
	    
		//intro message to the game
		System.out.println("Let's play! What's your move? " +'\n'
		    + "(r=rock, p=paper, s=scissors or q to quit)");
		//Read what the user input
         userMove = scanner.nextLine();
       }
        
      	//checking specifically for an r, s, or p input by the user
    	//by checking the valid 3 inputs
        else{
        	//warning message
        	System.out.println("That is not a valid move! "
        			+ "Please try again." +'\n'
        			+ "(r=rock, p=paper, s=scissors or q to quit)");
        	
            //Read what the user input
            userMove = scanner.nextLine();
        	
        }
    
    }
    
    //When the user inputs q or Q then it will return the
    //statistics of the game
        // TODO: When the game is done, write the 
    	//code to print the move history
        //  and statistics
    	
    	//close scanner (not needed anymore)
    	scanner.close();
    	//Print the statistics
        System.out.println("Thank you for playing with me!" +'\n'
        		+ "Our most recent games (in reverse order) were: ");
        
        //Printing the last 10 games of less in reverse order
        //if there are less than 10 moves, print the length of the
        //arrays (movescount)
        if((movesCount-10) < 0){
	        for(int i = movesCount-1; i >= 0; i--){   	
	        	//print what move system played last loop
	        	System.out.println("Me: " + moveString(systemMoves.get(i)) +
	        	//print what move the player played last loop
	        		'\t'+ " You: " + moveString(userMoves[i]));
	        	
	        }
        }
        //if there are more than 10 moves, the last game
        //printed will be the end -10 since you are going in reverse
        //and printing only 10 games
        else{
        	for(int i = movesCount-1; i >= (movesCount-10); i--){   	
	        	//print what move system played last loop
	        	System.out.println("Me: " + moveString(systemMoves.get(i)) +
	        	//print what move the player played last loop
	        		'\t' + " You: " + moveString(userMoves[i]));
	        }
        }
        
        
        //Print the statistics 
        System.out.println("Our overall stats are: ");
        System.out.println("I won:"
        		+percentWon(systemWin,movesCount)+"% ");
        System.out.println("You won:"
        		+percentWon(playerWin,movesCount)+"% ");
        System.out.println("We tied:"
        		+percentWon(tiedGames,movesCount)+"%");

    }
   
  
  
  //***** HELPER METHODS*****//
        
  /*converting single string into a word*/
  private static String moveString(String move){
	  //string to be returned 
	  String finalMoveString = new String();
	  //turning r into rock
	  if("r".equals(move))
		  finalMoveString = "rock";
	  //turning p into paper
	  else if("p".equals(move))
		  finalMoveString = "paper";
	  //turning s into scissors
	  else
		  finalMoveString = "scissors";
	  //return the final string
	  return finalMoveString;   
  }
  
  
  
  /*Determining system move*/
  private static String systemMoveString(int systemMove){
	  //new string to return
	  String systemMoveString = new String();
	  //if the random number generator=0 give rock
	  if(systemMove == 0)
		  systemMoveString = "r";
	  //if the random number generator =1 give paper
	  else if(systemMove == 1)
		  systemMoveString = "p";
	  //if the random number generator =2 give scissors
	  else 
		  systemMoveString = "s";
	  
	  //return the system move string
	  return systemMoveString;
  }
  
  
  //*** calculating percent won or tied ***//
  private static int percentWon(int wins, int totalGames){
	  wins = 100*wins;
	  //divide the players wins by the totalgames played
	  int percentWon = (wins/totalGames);
	  //return the percent of the games won
	  return percentWon;
  }
  
  
  /*Determine which player won 
  * If PLAYER win return 0
  * If COMPUTER win return 1
  * If TIE return 2
  */
  private static int winnerInt(String userMove, String systemMove){
	  //integer to take hold of who wins
	  int determineWinner = 0;
	  
	  //PLAYER wins
	  if("r".equals(userMove) && "s".equals(systemMove)){
		  System.out.println("I chose scissors. You win. ");
		  determineWinner= 0;  
	  }
	  else if("p".equals(userMove) && "r".equals(systemMove)){
		  System.out.println("I chose rock. You win. ");
		  determineWinner= 0;
	  }
	  else if("s".equals(userMove) && "p".equals(systemMove)){
		  System.out.println("I chose paper. You win. ");
		  determineWinner= 0;
	  }
		 
	  
	  //COMPUTER wins
	  else if("r".equals(systemMove) && "s".equals(userMove)){
		  System.out.println("I chose rock. I win!! ");
		  determineWinner= 1;
	  }
	  else if("p".equals(systemMove) && "r".equals(userMove)){
		  System.out.println("I chose paper. I win!! ");
		  determineWinner= 1;
	  }
	  else if("s".equals(systemMove) && "p".equals(userMove)){
		  System.out.println("I chose scissors. I win!! ");
		  determineWinner= 1;
	  }
	  
	  // TIE
	  else if("r".equals(systemMove) && "r".equals(userMove)){
		  System.out.println("I chose rock. It's a tie. ");
		  determineWinner= 2;
	  }
	  else if("p".equals(systemMove) && "p".equals(userMove)){
		  System.out.println("I chose paper. It's a tie. ");
		  determineWinner= 2;
	  }
	  else if("s".equals(systemMove) && "s".equals(userMove)){
		  System.out.println("I chose scissors. It's a tie. ");
		  determineWinner= 2;
	  }
	  
	  return determineWinner;
	  
  }
  

  //***copying user moves onto larger string array***//
  private static String[] newUserMoves(String[] oldUserMovesArray, 
		  int newArrayLength){
	//creating new string array with double the size  
  	String[] newUserMovesArray = new String[newArrayLength];
  	
  	//copying the information from old array
  	for(int i=0; i<(newArrayLength/2); i++){
  		newUserMovesArray[i] = oldUserMovesArray[i];
  	}
  	
  	//returning the new array with more spots
  	return newUserMovesArray;
  }
  
  
  
  
  
}


