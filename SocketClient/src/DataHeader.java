/**
 * Created by break on 18. 4. 7.
 */

public class DataHeader {

    private final  byte STX = 0x02;
    private final  byte ETX = 0x03;
    final static byte NVL = 0x00;
    private byte[] dataHeader;
    private String header;

    public DataHeader(long length, byte dataType){
        if(length > Long.MAX_VALUE){
            System.out.println("데이터가 너무 크다 ");
        }
        if(dataType != 77 && dataType != 66){
            System.out.println("유효한 데이터가 아님 ");
        }
        this.header = "" + STX + length + dataType;
        for(int i =0; i<55; i++){
            this.header += NVL;
        }
        System.out.println("데이터 헤더의 성공적 생성");
    }

    public byte getSTX() {
        return STX;
    }

    public byte getETX() {
        return ETX;
    }
    
    public String getHeader(){
        return header;
    }
}
