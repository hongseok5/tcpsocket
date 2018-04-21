import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestApp {

	public static void main(String[] args) {
		
		DataHeader dh = new DataHeader(DataHeader.LOGIN_DATA_SIZE, (byte)77);
		
		
		try {
			File file = new File("/home/break/fosex11.txt");
			FileOutputStream fos = new FileOutputStream(file);
			String id = "hongseok5";
			String pw = "1234abcd";
			byte[] byteId;
			byte[] bytePw;
			byte[]	eData =new byte[130];
			int index = 0;
			byteId = DataBody.fillPw(id);
			bytePw = DataBody.fillPw(pw);
			
			for(int i = 0; i < 65; i++) {
				eData[i] = dh.getDataHeader()[i];
				index++;
			}
			System.out.println("line29 : " + index);
			for(int i = 0; i <  32; i++) {
				eData[index] = byteId[i];
				index++;
			}
			System.out.println("line34 : " + index);
			for(int i = 0; i <  32; i++) {
				eData[index] = bytePw[i];
				index++;
			}
			eData[eData.length-1] = DataHeader.getETX();
			System.out.println("line34 : " + (index-1));

			System.out.print("StringBuilder로 만든 strData는 : ");
			String strData = "";
			StringBuilder sb = new StringBuilder(); 
			for(int i = 0; i < eData.length; i++ ) {
				//System.out.print(eData[i]);
				strData +=eData[i];
				sb.append(eData[i]);
				 
			}
			
			 
			System.out.println(sb.toString());
			
			fos.write(eData);
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
