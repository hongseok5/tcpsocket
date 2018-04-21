import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketClient {
	
	private static final String SERVER_IP = "45.119.147.81";
	private static final int SERVER_PORT = 9191;

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			socket = new Socket();//1.Socket 생성

			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			byte[] header = null;
			byte[] echoData = new byte[512];
			// 데이터 바디를 먼저 채운다
			byte[] eData = new byte[130];
			byte[] byteId;
			byte[] bytePw;
			int index = 0;
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
			DataHeader dh = new DataHeader(DataHeader.LOGIN_DATA_SIZE, (byte)77);
			
			OutputStream os = socket.getOutputStream();

			for(int i = 0; i < 65; i++) {
				eData[i] = dh.getDataHeader()[i];
				index++;
			}
			
			for(int i = 0; i <  32; i++) {
				eData[index] = byteId[i];
				index++;
			}
			
			for(int i = 0; i <  32; i++) {
				eData[index] = bytePw[i];
				index++;
			}
			eData[eData.length-1] = DataHeader.getETX();
			for(int i = 0; i < eData.length; i++ ) {
				System.out.print(eData[i]);
				
			}
			
			os.write(eData);
			
			os.flush();
			InputStream is = socket.getInputStream();
			int readByteCount = is.read(echoData);
			String message = new String(echoData, 0, readByteCount, "UTF-8");
			System.out.println("success :" + message );

			os.close();
			is.close();

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
