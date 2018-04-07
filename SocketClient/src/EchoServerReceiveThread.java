import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket;
	
	public EchoServerReceiveThread( Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		InetSocketAddress remoteSocketAddress = 
				(InetSocketAddress)socket.getRemoteSocketAddress();
		int remoteHostPort = remoteSocketAddress.getPort();
		String remoteHostAddress = 
				remoteSocketAddress.getAddress().getHostAddress();
		consoleLog("connected from" + remoteHostAddress + ":" + remoteHostPort);
		try {
			BufferedReader br =
					new BufferedReader( new InputStreamReader(socket.getInputStream(),"UTF-8"));
			PrintWriter pw = 
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			while(true) {
				String message = br.readLine();
				if(message == null) {
					consoleLog("disconnection by client");
					break;
				}
				consoleLog("received: " + message);
				pw.println(message);
			}
			
		} catch(SocketException e) {
			consoleLog("sudden closed by client");
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
				
			}
		}
	}	
	
	private void consoleLog(String log) {
		System.out.println("[server:" + getId() + "]" + log);
	}
}
