import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
class PartialHTTP1Server{
	
	public static void main (String[] args) throws Exception
	{
		//ARRAY LIST FOR 5 THREADS
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<6;i++) {
			threads.add(new Thread());
		}
		int port = Integer.parseInt(args[0]); // READ PORT
		ServerSocket server = new ServerSocket(port); // CREATE SOCKET
		System.out.println("Listening for connection on port" + port);
		while(true){
			Socket connect = server.accept();
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(connect.getInputStream())); //INPUT STREAM
			DataOutputStream toClient = new DataOutputStream(connect.getOutputStream()); // OUTPUT STREAM
			//CHECKS IF EACH OF THE 5 THREADS IDLE, AND IF IDLE USE THEM
			if(threads.get(0).isAlive()== false) 
			{
				threads.set(0, new ClientHandler(connect, fromClient, toClient));
				threads.get(0).start();
			}
			else if(threads.get(1).isAlive()== false) 
			{
				threads.set(1, new ClientHandler(connect, fromClient, toClient));
				threads.get(1).start();
			}
			else if(threads.get(2).isAlive()== false) 
			{
				threads.set(2, new ClientHandler(connect, fromClient, toClient));
				threads.get(2).start();
			}
			else if(threads.get(3).isAlive()== false) 
			{
				threads.set(3, new ClientHandler(connect, fromClient, toClient));
				threads.get(3).start();
			}
			else if(threads.get(4).isAlive()== false) 
			{
				threads.set(4, new ClientHandler(connect, fromClient, toClient));
				threads.get(4).start();
			}
			// MAKE SURE THERE IS NOT MORE THAN 50 REQUESTS, 
			else if(threads.size()<=50) 
			{
				Thread temp = new ClientHandler(connect, fromClient, toClient);
				threads.add(temp);
				temp.start();
			}
			else{ toClient.writeBytes("505 Service Unavailable and terminate connection");}
		}
		
	}
}
class ClientHandler extends Thread
{
	final Socket s;
	final BufferedReader fromC;
	final DataOutputStream toC;
	
	public ClientHandler(Socket s, BufferedReader fromC, DataOutputStream toC)
	{
		this.s = s;
		this.fromC = fromC;
		this.toC = toC;
	}
	public void run() {
		String Request;
		String Response;
		
		
	}
}
