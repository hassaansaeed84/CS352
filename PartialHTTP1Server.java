import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

class Threads extends Thread{
	public void run() {
		/* This is where threads will communicate with clients*/
		
	}
}
class TCPServer {
	
	public static void main (String argv[]) throws Exception
	{
		String error = "503 service Unavailable";
		
		int port = Integer.parseInt(args[0]);
		int thread_count=0;
		ServerSocket s  = new ServerSocket(port);
		ExecutorService pool = new ExecutorService();
		pool = Executors.newFixedThreadPool(5);
		while(true) { // Make sure not more than 50 threads running already
			if(thread_count>50) {
				//Creates Output stream of socket
				DataOutputStream toClient = new DataOutputStream(connect.getOutputStream())	
				toClient.writeBytes(error); //Write out "503 Service Unavailable" to client
				s.close(); // Immediately close
			}
				Socket connect = s.accept();
				Threads temp = new Thread (); //Constructor
				temp.start(); //Starts new thread in run method
				thread_count++; // Increments number of threads running 
			}
		//	BufferedReader fromClient = new BufferedReader (new InputStreamReader(connect.getInputStream()))
	}
}
