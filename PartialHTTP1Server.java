import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
public class PartialHTTP1Server
{ 
 //initialize socket and input stream 
 private Socket          socket   = null; 
 private ServerSocket    server   = null; 
 private DataInputStream in       =  null; 
  
    // constructor with port 
	public PartialHTTP1Server(int port) 
	{ 
        // starts server and waits for a connection 
	        try
	        { 
		    //ARRAY LIST FOR 5 THREADs
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<6;i++) 
		{
			threads.add(new Thread());
		}
	        server = new ServerSocket(port); 
	        System.out.println("Server started"); 
	  
	        System.out.println("Waiting for a client ..."); 
	  
	        socket = server.accept(); 
	        System.out.println("Client accepted"); 
	        BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream())); //INPUT STREAM
	 	DataOutputStream toClient = new DataOutputStream(socket.getOutputStream()); // OUTPUT STREAM
	        // takes input from the client socket 
	        String Request = ""; 
	        //CHECKS IF EACH OF THE 5 THREADS IDLE, AND IF IDLE USE THEM
			if(threads.get(0).isAlive()== false) 
			{
				threads.set(0, new ClientHandler(socket, fromClient, toClient));
				threads.get(0).start();
			}
			else if(threads.get(1).isAlive()== false) 
			{
				threads.set(1, new ClientHandler(socket, fromClient, toClient));
				threads.get(1).start();
			}
			else if(threads.get(2).isAlive()== false) 
			{
				threads.set(2, new ClientHandler(socket, fromClient, toClient));
				threads.get(2).start();
			}
			else if(threads.get(3).isAlive()== false) 
			{
				threads.set(3, new ClientHandler(socket, fromClient, toClient));
				threads.get(3).start();
			}
			else if(threads.get(4).isAlive()== false) 
			{
				threads.set(4, new ClientHandler(socket, fromClient, toClient));
				threads.get(4).start();
			}
			// MAKE SURE THERE IS NOT MORE THAN 50 REQUESTS, 
			else if(threads.size()<=50) 
			{
				Thread temp = new ClientHandler(socket, fromClient, toClient);
				threads.add(temp);
				temp.start();
			}
			else{ toClient.writeBytes("505 Service Unavailable and terminate connection");}	
		}
		catch (IOException i)
		{
			System.out.println(i);
		}
		
	        // reads message from client until "Over" is sent
	       
	      } 
	        
	        public static void main(String args[]) 
	    	{ 
	    	int port = Integer.parseInt(args[0]);
	    	PartialHTTP1Server server = new PartialHTTP1Server(port); 
	    	} 
	    } 
	  

class ClientHandler extends Thread
{
	final Socket s;
	final BufferedReader fromClient;
	final DataOutputStream toClient;
	
	public ClientHandler(Socket s, BufferedReader fromClient, DataOutputStream toClient)
	{
		this.s = s;
		this.fromClient = fromClient;
		this.toClient = toClient;
	}
	public void run() {
		String Request="";
		try
		{

		 while(Request!=null)
	        {
	                try
	                { 
	                    Request = fromClient.readLine(); 
	                    System.out.println(Request); 
	  
	                } 
	                catch(IOException i) 
	                { 
	                    System.out.println(i); 
	                }  
	  	}
	  	    System.out.println("Closing connection");
	            // close connection 
	            s.close(); 
	            fromClient.close();
	      	}
	      	catch (IOException i)
		{
			System.out.println(i);
		}
	}
}
