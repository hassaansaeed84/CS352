
// A Java program for a Client 
import java.net.*; 
import java.io.*; 
  
public class WebClient 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public WebClient(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
	// takes input from socket 
            BufferedReader fromServer = new BufferedReader(new InputStreamReader((socket.getInputStream()))); //INPUT STREAM 
  	
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  	
        // string to read message from input 
        String line = ""; 
  	// takes input from terminal 
        BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in)); //INPUT STREAM 
        // keep reading until "Over" is input 
            try
            { 
                line = fromUser.readLine(); 
                out.writeBytes(line); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
  
        // close the connection 
        try
        { 
            fromUser.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
    	System.out.println(args[0]);
    	System.out.println(args[1]);
    	int port = Integer.parseInt(args[1]);
    	System.out.println(port);
        WebClient client = new WebClient(args[0], port); 
    } 
} 

/*import java.io.*;
import java.net.*;

class WebClient{
	public static void main (String[] args) throws Exception
	{
		System.out.println(args[0]);
		System.out.println(args[1]);
		String Request;
		String Response;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		int port = Integer.parseInt(args[1]); // READ PORT
		Socket c = new Socket(args[0], port);
		DataOutputStream toServer = new DataOutputStream(c.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(c.getInputStream()));
		toServer.writeBytes("Get /index.html");
	}
}*/
