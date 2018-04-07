import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketClient {
	
	private static final String SERVER_IP = "45.119.147.81";
	private static final int SERVER_PORT = 9190;

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			socket = new Socket();//1.Socket 생성

			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			byte[] header = null;
			byte[] echoData = new byte[512];
			// 데이터 바디를 먼저 채운다
			byte[] byteId;
			byte[] bytePw;
			String id = "hseok5";
			String pw = "12cd";
			System.out.println(DataBody.fillPw(id));			
			byteId = DataBody.fillPw(id);
			bytePw = DataBody.fillPw(pw);
//			System.out.println(byteId.length);
//			System.out.println(bytePw.length);
//			System.out.println(new String(byteId, 0, 32, "UTF-8"));
//			System.out.println(new String(bytePw, 0, 32, "UTF-8"));
			// 데이터 헤더를 채운다.
			DataHeader dh = new DataHeader(Long.MAX_VALUE, (byte)77);
			
			OutputStream os = socket.getOutputStream();
			header = dh.getHeader().getBytes("UTF-8");

			
			os.write(header);
			os.write(byteId);
			os.write(bytePw);
			os.write(dh.getETX());
			os.flush();
			InputStream is = socket.getInputStream();
			int readByteCount = is.read(echoData);
			String message = new String(echoData, 0, readByteCount, "UTF-8");
			System.out.println("success :" + message );

			os.close();
			is.close();
			/*
			int receiveBufferSize = socket.getReceiveBufferSize();
			int sendBufferSize = socket.getSendBufferSize();
			System.out.println( receiveBufferSize + ":" + sendBufferSize );
			//1-1 Socket Buffer Size 확인
			
			socket.setReceiveBufferSize( 10 * 1024 );
			socket.setSendBufferSize( 10 * 1024 );
			//1-2 Socket Buffer Size 변경
			
			receiveBufferSize = socket.getReceiveBufferSize();
			sendBufferSize = socket.getSendBufferSize();
			System.out.println( receiveBufferSize + ":" + sendBufferSize );
			
			//socket.setTcpNoDelay( true );
			
			socket.setSoTimeout ( 3000 );
			
			socket.connect( new InetSocketAddress ( SERVER_IP, SERVER_PORT ) );
			//2. 서버 연결
			System.out.println("connected");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			String data = "hello";
			os.write( data.getBytes( "utf-8") );
			
			byte[] buffer = new byte[ 256 ];
			int readByteCount = is.read( buffer );
			if( readByteCount <= -1 ) {
				System.out.println( "[client] disconnection by server" );
				return;
			}
			
			data = new String( buffer, 0, readByteCount, "utf-8" );
			System.out.println( "[client] received:" + data );
			*/
		} catch( ConnectException e ) {
			System.out.println( "[client] not connect" );
			System.out.println( e.toString());
		} catch( SocketTimeoutException e) {
		System.out.println( "[client] read timeout" );
	} catch( IOException e) {
		e.printStackTrace();
	} catch(Exception e)
		{
		 	System.out.println(e.toString());
		}
		finally {
		try {
			if( socket != null && socket.isClosed() == false ) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	}
	
}
