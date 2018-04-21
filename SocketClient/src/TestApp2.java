
public class TestApp2 {
	
	public static void main(String args[]) {
		//System.out.println(Byte.MAX_VALUE);
		DataHeader dh = new DataHeader(DataHeader.LOGIN_DATA_SIZE, (byte) 77);
		/*
		System.out.println(dh.longToBytes(Long.MAX_VALUE).length);
		for(int i = 0; i < dh.longToBytes(Long.MAX_VALUE).length; i++) {
			System.out.println(dh.longToBytes(Long.MAX_VALUE)[i]);
			
		}
		*/
	}
	

}
