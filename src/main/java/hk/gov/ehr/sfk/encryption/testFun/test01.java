package hk.gov.ehr.sfk.encryption.testFun;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class test01 {
    public static void main(String[] args) {
/*        ByteArrayInputStream is1 = new ByteArrayInputStream(("slot:" + 3).getBytes());
        System.out.println(("slot:"+3).getBytes());
        byte[] b1 = ("slot:"+3).getBytes();
        String s1 = new String(b1);
        System.out.println(b1);
        System.out.println(s1);
        System.out.println(("slot:"+3).getBytes());
        System.out.println(("slot:"+3).getBytes());
        System.out.println(("slot:"+3).getBytes());*/

        String[] srcStrAry = {"1", "", " ", null};
        System.out.println(srcStrAry.length);
        for (int i = 0; i < srcStrAry.length; i++) {
           if(srcStrAry[i] == null){
                System.out.print("this is else if");
                System.out.println(srcStrAry[i]);
            }
        }
        Map<String,String> map1 = new HashMap<>();
        map1.put("ALGORITHM","AES");
        System.out.println(map1.get("ALGORITHM"));
    }
}
