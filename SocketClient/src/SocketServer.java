import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	
	private static final int SERVER_PORT = 6000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			
			serverSocket.bind( new InetSocketAddress( localhostAddress, SERVER_PORT));
			consoleLog("binding " + localhostAddress + ":" + SERVER_PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				new EchoServerReceiveThread( socket ).start();
			}
		} catch(IOException e) {
			e.printStackTrace();			
		} finally {
			try {
				if(serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	private static void consoleLog(String log) {
		System.out.println("[server:" + Thread.currentThread().getId() + "]" +log);
	}
}
