import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
class PartialHTTP1Server extends Thread {
	public void run() {
		
	}
	
	public static void main (String[] args) throws Exception
	{
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<6;i++) {
			threads.add(new Thread());
		}
		int port = Integer.parseInt(args[0]);
		ServerSocket server = new ServerSocket(port);
		System.out.println("Listening for connection on port" + port);
		while(true){
			Socket connect = server.accept();
			BufferedReader fromClient = new BufferedReader(newInputStreamReader(connect.getInputStream()));
			DataOutputStream toClient = new DataOutputStream(connectionSocket.getOuptuStream());
			if(threads.get(0).isAlive()== false)      {threads.get(0).start();}
			else if(threads.get(1).isAlive()== false) {threads.get(1).start();}
			else if(threads.get(2).isAlive()== false) {threads.get(2).start();}
			else if(threads.get(3).isAlive()== false) {threads.get(3).start();}
			else if(threads.get(4).isAlive()== false) {threads.get(4).start();}
			else if(threads.size()<=50) {
				Thread temp = new Thread();
				threads.add(temp);
				temp.start();
			}
			else{ toClient.writeBytes("505 Service Unavailable and terminate connection");}
		}
		
	}
}
