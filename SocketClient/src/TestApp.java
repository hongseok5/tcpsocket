import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestApp {

	public static void main(String[] args) {
		try {
			File file = new File("/home/break/fosex4.txt");
			FileOutputStream fos = new FileOutputStream(file);
			String id = "hongseok5";
			String pw = "1234abcd";
			byte[] byteId;
			byte[] bytePw;
			byteId = DataBody.fillPw(id);
			bytePw = DataBody.fillPw(pw);
			DataHeader dh = new DataHeader(Long.MAX_VALUE, (byte)77);
			byte[] header = dh.getHeader().getBytes("UTF-8");
			fos.write(header);
			fos.write(byteId);
			fos.write(bytePw);
			fos.write(dh.getETX());
			fos.flush();
			fos.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
