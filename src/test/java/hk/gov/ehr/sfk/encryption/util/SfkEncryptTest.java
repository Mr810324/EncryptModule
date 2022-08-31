package hk.gov.ehr.sfk.encryption.util;


import hk.gov.ehr.sfk.encryption.entity.ScrambleLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SfkEncryptTest {



    @Test
    public void testSwapString() throws Exception {
        SystemProperties sysPro = new SystemProperties();
        String srcStr = "12348b7590a6";
        String desStr = "1234567890ab";
        String logicStr = "12,8#6,5";
        //initSystemProperties();
        System.out.println("初始化 initSystemProperties 成功");
        Assertions.assertEquals(desStr,new SfkEncrypt().swapString(srcStr,logicStr));
    }
    public void initSystemProperties() throws Exception {
        System.out.println("start to initSystemProperties.");
        SystemProperties sysPro = new SystemProperties();
        String logicStr = "12,8#6,5";
        int[] src = {12,8};
        int[] des = {6,5};
        ScrambleLogic logic = new ScrambleLogic(src,des);
        System.out.println("构造 logic 成功");
        when(sysPro.getLogic(logicStr)).thenReturn(logic);
        System.out.println(logic.getDesChar()[0]);

    }
    @Test
    public void testReverseSwapString() throws Exception {
        String originStr = "1234567890ab";
        Assertions.assertEquals(originStr,new SfkEncrypt().swapString("12348b7590a6","12,8#6,5"));
        Assertions.assertEquals("123",new SfkEncrypt().swapString("123","12,8#6,5"));
    }

    @Test
    public void getKey() {
    }

}
