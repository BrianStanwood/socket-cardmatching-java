// 3166 Final Project (Server) by Brian Stanwood

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Server {
	public ServerSocket ss1 ;
	public Socket s1 ;
	
	public ServerSocket ss2 ;
	public Socket s2;
	
	public DataInputStream din ;
	public DataOutputStream dout ;
	public Gameboard game;
	// accept check number passed by player 1
	
	
	public Server() {
		try {
			ss1 = new ServerSocket(1234);  // server socket with same port number as player 1
			s1 = ss1.accept(); // wait for check number from player1
			
			ss2 = new ServerSocket(2345);  // server socket with same port number as player 1
			s2 = ss2.accept(); // wait for check number from player1
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		game = new Gameboard();
	}
	
	public void createStreams(Socket s)  {
		try {
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int readINTFromStream(Socket s) {
		try {
			this.createStreams(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int temp = 0;
		try {
			temp = din.readInt();
			dout.writeInt(temp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.closeStreams();
		return temp;
	}
	
	public void closeStreams() {
		try {
			din.close();
			dout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeSockets(Socket s, ServerSocket ss)  {
		try {
			s.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean testConnection() {
		//if 
		//this.restablishConnection()
		return true;
	}
	
	public void restablishConnection() {
		//re open socket
	}
	
	
	// Main server code
	public static void main(String[] args) {// throws IOException {
		int check1 = 0;
	
		Server server;
		try {
			server = new Server();
			server.gameLogic();
			System.out.println("Server");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Server Catch");
		}
		
		System.out.println("Server");
		
		//server.methodTest();
		//server.createConnection();
		
		/**
		int[][] answerBoard = new int[4][4];   // 2D array of board that is the answer key for the playing board (4x4)
		String[][] playBoard = new String[4][4];   // 2D array of board to be manipulated and displayed (4x4)
		Random rand = new Random();   // populates the answer board and display board arrays for the start of a game
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				answerBoard[j][i] = rand.nextInt(8)+1;
				playBoard[j][i] = "-";
			}
		}
		
		//System.out.println(toString(playBoard));
		
		ServerSocket ss1 = new ServerSocket(1234);  // server socket with same port number as player 1
		Socket s1 = ss1.accept(); // wait for check number from player1
		DataInputStream din = new DataInputStream(s1.getInputStream());
		DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
		// accept check number passed by player 1
		check1 = din.readInt();
		// pass over the check result
		dout.writeInt(check1);
		din.close();
		dout.close();
		s1.close();
		ss1.close();
		
		
		ServerSocket ss2 = new ServerSocket(2345);  // server socket with same port number as player 2
		Socket s2 = ss2.accept(); // wait for check number from player2
		DataInputStream d2in = new DataInputStream(s2.getInputStream());
		DataOutputStream d2out = new DataOutputStream(s2.getOutputStream());
		// accept check number passed by player 2
		check2 = d2in.readInt();
		// pass over the check result
		d2out.writeInt(check2);
		d2in.close();
		d2out.close();
	    s2.close();
		ss2.close();
		**/
		
	}
	
	public void gameLogic() {
		
		int player1Score = 0;
		int player2Score = 0;
		int x1;
		int y1;
		int x2;
		int y2;
		int check1;
		int check2;
		int loop = 1;
		
		System.out.println("Server");
		
		try {
			System.out.println(this.readINTFromStream(s1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("End");
		
		
		
		
		
		
	}
}
		/**
		while (player1Score + player2Score != 8) {                         // game begins
			//ArrayList<Integer> coordxy = new ArrayList<Integer>();
			int temp = 0;
					
			ss1 = new ServerSocket(1234);   // open socket
			s1 = ss1.accept();
			dout = new DataOutputStream(s1.getOutputStream());
			din = new DataInputStream(s1.getInputStream());
			dout.writeUTF(this.game.playBoard.toString());              // give current playboard
			din.close();
			dout.close();   
			s1.close();   
			ss1.close(); // close socket
			
			
			//for(int i = 0; i < 2; i++) {     
				ss1 = new ServerSocket(1234);   // open socket
				s1 = ss1.accept();
				din = new DataInputStream(s1.getInputStream());
				dout = new DataOutputStream(s1.getOutputStream());
				temp = din.readInt();
				x1 = temp/10;
				y1 =temp - x1;
				din.close();
				dout.close();
				s1.close();   
				ss1.close();
				
				game.playBoard = game.updateBoard(game.playBoard, game.answerBoard, x1, y1);
				
				ss1 = new ServerSocket(1234);   // open socket
				s1 = ss1.accept();
				din = new DataInputStream(s1.getInputStream());
				dout = new DataOutputStream(s1.getOutputStream());
				//dout.writeUTF(toString(playBoard));              // give current playboard
				din.close();
				dout.close();    
				s1.close();   
				ss1.close();// close socket
				
				ss1 = new ServerSocket(1234);   // open socket
				s1 = ss1.accept();
				din = new DataInputStream(s1.getInputStream());
				dout = new DataOutputStream(s1.getOutputStream());
				temp = din.readInt();
				x2 = temp/10;
				y2 =temp - x2;
				din.close();
				dout.close();
				s1.close();   
				ss1.close();
				
				//playBoard = updateBoard(playBoard, answerBoard, x2, y2);
				
				
				// sends off if there is a match
				ss1 = new ServerSocket(1234);   // open socket
				s1 = ss1.accept();
				din = new DataInputStream(s1.getInputStream());
				dout = new DataOutputStream(s1.getOutputStream());
				dout.writeInt(0); //guess(playBoard, answerBoard, x1, y1, x2, y2));              // tells if match is true/false
				din.close();
				dout.close();   
			    s1.close();   
				ss1.close(); // close socket
				
				// reset board if need be
				
			//}
			   
				/**               Player 2
				temp = 0;
				
				ss2 = new ServerSocket(2345);   // open socket
				s2 = ss2.accept();
				dout = new DataOutputStream(s2.getOutputStream());
				din = new DataInputStream(s2.getInputStream());
				dout.writeUTF(toString(playBoard));              // give current playboard
				din.close();
				dout.close();    
				s2.close();   
				ss2.close();// close socket
				
				//for(int i = 0; i < 2; i++) {     
					ss2 = new ServerSocket(2345);   // open socket
					s2 = ss2.accept();
					din = new DataInputStream(s2.getInputStream());
					dout = new DataOutputStream(s2.getOutputStream());
					temp = din.readInt();
					x1 = temp/10;
					y1 =temp - x1;
					din.close();
					dout.close();
					s2.close();   
					ss2.close();
					
					//playBoard = updateBoard(playBoard, answerBoard, x1, y1);
					
					ss2 = new ServerSocket(1234);   // open socket
					s2 = ss1.accept();
					din = new DataInputStream(s2.getInputStream());
					dout = new DataOutputStream(s2.getOutputStream());
					dout.writeUTF(toString(playBoard));              // give current playboard
					din.close();
					dout.close();   
					s2.close();   
					ss2.close(); // close socket
					
					ss2 = new ServerSocket(2345);   // open socket
					s2 = ss2.accept();
					din = new DataInputStream(s2.getInputStream());
					dout = new DataOutputStream(s2.getOutputStream());
					temp = din.readInt();
					x2 = temp/10;
					y2 =temp - x2;
					din.close();
					dout.close();
					s2.close();   
					ss2.close();
					
					//playBoard = updateBoard(playBoard, answerBoard, x2, y2);
					
					
					// sends off if there is a match
					ss2 = new ServerSocket(2345);   // open socket
					s2 = ss2.accept();
					din = new DataInputStream(s2.getInputStream());
					dout = new DataOutputStream(s2.getOutputStream());
					dout.writeInt(0); //guess(playBoard, answerBoard, x1, y1, x2, y2));              // tells if match is true/false
					din.close();
					dout.close();    
				    s2.close();   
					ss2.close();// close socket
					**/
			    /**
				ss2 = new ServerSocket(2345);   // open socket
				s2 = ss2.accept();
				din = new DataInputStream(s2.getInputStream());
				temp = din.readInt();
				x1 = temp/10;
				y1 =temp - x1;
				s2.close();   
				ss2.close();
				din.close();
				
				ss2 = new ServerSocket(2345);   // open socket
				s2 = ss1.accept();
				din = new DataInputStream(s2.getInputStream());
				temp = din.readInt();
				x2 = temp/10;
				y2 =temp - x2;
				s2.close();   
				ss2.close();
				din.close();
				**/
			
		 // end game if both scores = 8
			
		// send ending scores to both
		
		/**
			ss1 = new ServerSocket(1234);   // open socket
			s1 = ss1.accept();
			din = new DataInputStream(s1.getInputStream());
			dout = new DataOutputStream(s1.getOutputStream());
			
			menuSelection = din.readInt();  //  read selection
			
			switch (menuSelection) {
				case 1: dout.writeDouble(balance);    // send balance
				
						s1.close();   // close socket
						ss1.close();
						din.close();
						dout.close();
						break;
				case 2: s1.close();   // close socket
						ss1.close();
						din.close();
						dout.close();
						
						ss1 = new ServerSocket(1234);   // open socket
						s1 = ss1.accept();
						din = new DataInputStream(s1.getInputStream());
						dout = new DataOutputStream(s1.getOutputStream());
						
						temp = din.readDouble();
						balance = temp + balance;  // read deposit and add to balance
						
						s1.close();   // close socket
						ss1.close();
						din.close();
						dout.close();
						break;
						
				case 3: dout.writeDouble(balance);    // send balance
						
						s1.close();   // close socket
						ss1.close();
						din.close();
						dout.close();
						
						ss1 = new ServerSocket(1234);   // open socket
						s1 = ss1.accept();
						din = new DataInputStream(s1.getInputStream());
						dout = new DataOutputStream(s1.getOutputStream());
				
						temp = din.readDouble();
						balance = balance - temp;  // read the withdraw amount and subtract from balance
				
						s1.close();   // close socket
						ss1.close();
						din.close();
						dout.close();
						break;
						
				case 4: loop = 0;
						break;
				default: break;
			}
			
		} 
		
		
		
	
	
	public void methodTest() throws IOException {
		ss1 = new ServerSocket(1234);   // open socket
		s1 = ss1.accept();
		dout = new DataOutputStream(s1.getOutputStream());
		din = new DataInputStream(s1.getInputStream());
		dout.writeUTF(toString(playBoard));              // give current playboard
		din.close();
		dout.close();   
		s1.close();   
		ss1.close(); // close socket
	}
} **/
