package hk.gov.ehr.sfk.encryption.util;



import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class testFunc {
    public static void main(String[] args) {


    }

    public void test1() throws UnsupportedEncodingException {
        //现在Base64编码   import java.util.Base64;
        String s = "zhangjilin";
        String encodeToString = Base64.getEncoder().encodeToString(s.getBytes());
        System.out.println("s.getBytes() is: "+s.getBytes());
        System.out.println(new String(s.getBytes()));
        byte[] b1 = Base64.getEncoder().encode(s.getBytes());
        System.out.println(encodeToString);
        System.out.println( Base64.getEncoder().encodeToString(b1));
        //解码
        byte[] decode = Base64.getDecoder().decode(encodeToString);
        System.out.println("decode is: "+decode);
        System.out.println(new String(decode));
        ByteArrayInputStream is1 ;
        is1 = new ByteArrayInputStream(("slot:"+1).getBytes());
    }
}
