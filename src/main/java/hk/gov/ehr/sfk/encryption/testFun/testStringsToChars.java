package hk.gov.ehr.sfk.encryption.testFun;

import java.util.Arrays;

/**
 * @ClassName testStringsToChars
 * @Description TODO
 * @date 22:03
 * @Version 1.0
 */
public class testStringsToChars {
    public static void main(String[] args) {
        String[] srcStrAry = {null,"abcdefg"};
        char[][] srcChaAry = new testStringsToChars().convertStringArrayToCharArray(srcStrAry);
        String[] desStrAry = new String[2];
        for(int i = 0; i < srcChaAry.length;i++){
            if (srcChaAry[i] == null){
                desStrAry[i] = null;
                System.out.println(desStrAry[i]);
                continue;
            }
            desStrAry[i] = new String(srcChaAry[i]);
            System.out.println(desStrAry[i]);
        }

    }

    public char[][] convertStringArrayToCharArray(String[] srcStrAry){
        char[][] srcChaAry = new char[srcStrAry.length][];
        for (int i = 0;i<srcStrAry.length;i++){
            if (srcStrAry[i] == null){
                srcChaAry[i] = null;
                continue;
            }
            srcChaAry[i] = srcStrAry[i].toCharArray();
        }
        return srcChaAry;
    }
}
