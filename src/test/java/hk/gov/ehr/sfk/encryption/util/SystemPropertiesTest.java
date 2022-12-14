package hk.gov.ehr.sfk.encryption.util;

import hk.gov.ehr.sfk.encryption.entity.ScrambleLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class SystemPropertiesTest {



    @Test
    private void getLogic() throws Exception {
        int[] src = {12,8};
        int[] des = {6,5};
        ScrambleLogic testLogic = new ScrambleLogic(src,des);
        SystemProperties sysPro = new SystemProperties();
        String logicStr = "12,8#6,5";
        Assertions.assertArrayEquals(testLogic.getSrcChar(),(sysPro.getLogic(logicStr).getSrcChar()));
        Assertions.assertArrayEquals(testLogic.getDesChar(),(sysPro.getLogic(logicStr).getDesChar()));
    }

    public static void main(String[] args) {
        String[] s1 = {"str1","str2",null};
        for (int i = 0;i< s1.length;i++ ) {
            System.out.println(s1[i]);
        }
    }
}