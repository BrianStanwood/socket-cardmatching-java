// 3166 Final Project (Player 1) by Brian Stanwood

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PlayerOne {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		int player1Score = 0;
		int player2Score = 0;
		int check = 1;
		int gameLoop = 1;
		int temp =0;
		Scanner sc= new Scanner(System.in);      // create scanner for input  
		Socket s = new Socket("127.0.0.1",1234);   // create socket for communication with local IP and a specified port number
		DataInputStream din = new DataInputStream(s.getInputStream());  
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		
		// test Player 1/server connection
		System.out.println("Connecting to Server...");
		dout.writeInt(check);        			 // pass over check number to the server
		if (din.readInt() == 1){
			System.out.println("Connection Successful. \n");
		}
		else {System.out.println("Connection Unsuccessful. \n");}
		s.close();
		din.close();
		dout.close();
		
		System.out.println("You are Player 1.");
		System.out.println("Waiting for other player...");
		
		System.out.println("Both players connected!");
		
		while (gameLoop != 0) {   // Game loop begins
			int[] xy = new int[2];
			int xIsValid = 0;
			int yIsValid = 0;
			int foundMatch = 0;
			
			System.out.println("It's your turn!.");
			System.out.println("Player 1: " + player1Score);
			System.out.println("Player 2: " + player2Score);
				
			//while (checkX < 2) {  // turn loop begins
				
			    for(int xloop = 0; xloop < 2; xloop++) { // runs twice to get both coordinates (xy, xy)
			    	xIsValid = 0;
			    	yIsValid = 0;
			    
			    	System.out.println("showing board...");
			    	s = new Socket("127.0.0.1",1235);               // takes in the current playboard and prints
					din = new DataInputStream(s.getInputStream());  
					dout = new DataOutputStream(s.getOutputStream());
					//String arrayString = din.toString();   
					//System.out.println(arrayString);
					System.out.println("din.readUTF()");                  
					s.close();
					din.close();
					dout.close();
					
			        while (xIsValid == 0) {   
			        	System.out.println("Flip over a tile and see if you can find a match! ");
			        	System.out.println("Enter the row of tile to flip(1-4): ");
			        	System.out.print(">");
			        	String selectStrX = sc.next();
			        	if (selectStrX.matches("[1-4]") && selectStrX != null) {
			        		xy[0] = Integer.parseInt(selectStrX);
			        		xIsValid = 1;
				
				/**
				s = new Socket("127.0.0.1",1234);               // sends out x value  
				dout = new DataOutputStream(s.getOutputStream());
				dout.writeInt(row);
				s.close();
				dout.close();  **/
				
			        		
			        		while (yIsValid == 0) {
			        			System.out.println("Enter the column of the tile to flip(1-4): ");
			        			System.out.print(">");
			        			String selectStrY = sc.next();
			        			if (selectStrY.matches("[1-4]") && selectStrY != null) {
			        				xy[1] = Integer.parseInt(selectStrY);
			        				yIsValid = 1;
						/**
						s = new Socket("127.0.0.1",1234);               // sends out y value 
						dout = new DataOutputStream(s.getOutputStream());
						dout.writeInt(column);
						s.close();
						dout.close();   **/
						
			        			} else {System.out.println("Invalid input.");}
			        		}
			        	} else {System.out.println("Invalid input.");}  // end of single coordinate gathering
			        	
			        	// send off coordinates from array[0,1]
			        	System.out.println("sending coords");
			        	s = new Socket("127.0.0.1",1234);               // sends out xy value 
						dout = new DataOutputStream(s.getOutputStream());
						din = new DataInputStream(s.getInputStream());
						temp = xy[0]*10+xy[1]; //  joining ints into one number for transmission
						dout.writeInt(temp);
						s.close();
						dout.close(); 
						din.close();
			        	
			        }// End of While check loop 
			    }// End of For check loop 
			
			    System.out.println("out of for loop");
			   //try { 
			    s = new Socket("127.0.0.1",1234);               // sees if match is valid from server side
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				foundMatch = din.readInt();
				s.close();
				din.close();
				dout.close();
			   //} catch ( Exception e) {System.out.println("something is wrong");}
			   
				if(foundMatch == 1) {
					System.out.println("You found a match!");
				}else {System.out.println("Those tiles didn't match or were already used.");}
			
			
			// update score
			
			
			System.out.println("It's Player 2's turn.");
			System.out.println("Waiting on Player 2");

			
		} // End of game loop
		
		sc.close();
		System.out.println("Game Over, final score: ");
		System.out.println("Player 1: " + player1Score);
		System.out.println("Player 2: " + player2Score);
		
		
	}// End of main()

}// End of class
