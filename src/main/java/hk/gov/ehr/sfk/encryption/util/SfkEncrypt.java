package hk.gov.ehr.sfk.encryption.util;

import hk.gov.ehr.sfk.encryption.entity.ScrambleLogic;
import hk.gov.ehr.sfk.encryption.entity.SesRtConfigs;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SfkEncrypt {
    @Autowired
    SystemProperties sysPro;

    public byte[] encrypt(String algorithm, String mode, String padding, byte[] iv, String keyAlias, byte[] plainBytes) throws Exception {
        System.out.println("开始执行 SfkEncrypt.encrypt(...)");
        try {
            SecretKey key = getKey(keyAlias, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] cipherBytes = cipher.doFinal(plainBytes);
            return Base64.getEncoder().encode(cipherBytes);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] decrypt(String algorithm, String mode, String padding, byte[] iv, String keyAlias,
                          byte[] cipherBytes) {
        try {
            SecretKey key = getKey(keyAlias, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] plainBytes = cipher.doFinal(Base64.getDecoder().decode(cipherBytes));
            return plainBytes;
        } catch (IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
            //return null;
            throw new RuntimeException(e.getClass()+e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public String swapString(String scrambleObj, String logicStr) throws Exception {
        ScrambleLogic logic = sysPro.getLogic(logicStr);
        int[] srcChar = logic.getSrcChar();
        int[] desChar = logic.getDesChar();

        char[] inputChar = scrambleObj.toCharArray();
        for (int j = 0; j < srcChar.length; j++) {
            if (srcChar[j] > inputChar.length || desChar[j] > inputChar.length) {
                continue;
            }
            char temp = inputChar[srcChar[j] - 1];
            inputChar[srcChar[j] - 1] = inputChar[desChar[j] - 1];
            inputChar[desChar[j] - 1] = temp;
        }

        return new String(inputChar);
    }

    public String reverseSwapString(String scrambledObj, String logicStr) throws Exception {
        int[] srcChar = sysPro.getLogic(logicStr).getSrcChar();
        int[] desChar = sysPro.getLogic(logicStr).getDesChar();
        char[] inputChar = scrambledObj.toCharArray();
        for (int j = srcChar.length - 1; j >= 0; j--) {
            if (srcChar[j] > inputChar.length || desChar[j] > inputChar.length) {
                continue;
            }
            char temp = inputChar[srcChar[j] - 1];
            inputChar[srcChar[j] - 1] = inputChar[desChar[j] - 1];
            inputChar[desChar[j] - 1] = temp;
        }
        return new String(inputChar);
    }

    public SecretKey getKey(String keyAlias, String algorithm) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(keyAlias.getBytes(sysPro.getCharSetName()));
            KeyGenerator generator = KeyGenerator.getInstance(algorithm);
            generator.init(128, random);
            SecretKey key = generator.generateKey();
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
