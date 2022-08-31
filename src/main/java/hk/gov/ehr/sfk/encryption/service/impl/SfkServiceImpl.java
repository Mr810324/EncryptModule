package hk.gov.ehr.sfk.encryption.service.impl;


import hk.gov.ehr.sfk.encryption.config.SfkConfig;
import hk.gov.ehr.sfk.encryption.dao.SesRtConfigsRepository;
import hk.gov.ehr.sfk.encryption.entity.SesRtConfigs;
import hk.gov.ehr.sfk.encryption.entity.resultDTO;
import hk.gov.ehr.sfk.encryption.service.SfkService;
import hk.gov.ehr.sfk.encryption.util.SfkEncrypt;
import hk.gov.ehr.sfk.encryption.util.SystemProperties;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.*;
import java.util.List;
import java.util.Base64;
import java.util.Map;


@Service
public class SfkServiceImpl implements SfkService {
    @Autowired
    SesRtConfigsRepository srcRep;
    @Autowired
    SfkEncrypt sfkEncrypt;
    @Autowired
    SfkConfig sfkConfig;
    @Autowired
    SystemProperties sysPro;


/*    @Override
    public resultDTO encrypt1(char[][] srcStrAry) {
        if (srcStrAry == null) {
            throw new RuntimeException("srcStrAry should not be null.");
        }
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();
        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            System.out.println("The length of this encrypt array is: " + srcStrAry.length);
            for (int i = 0; i < srcStrAry.length; i++) {
                if (i >= sfkConfig.getMaxSrcStrArySize() || srcStrAry[i] == null ||
                        srcStrAry[i].length > sfkConfig.getMaxPlainTextLength()) {
                    System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i] + ". The length of " +
                            "this string is: " + srcStrAry[i].length);
                    System.out.println("sfkConfig.getMaxSrcStrArySize() is: " + sfkConfig.getMaxSrcStrArySize());
                    resultStrAry[i] = null;
                    continue;
                }
                System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i]);
                ByteBuffer buf = Charset.forName(sysPro.getCharSetName()).encode(CharBuffer.wrap(srcStrAry[i]));
                byte[] byteAry = new byte[buf.limit()];
                buf.get(byteAry);
                //byte[] plainBytes = srcStrAry[i].toString().getBytes(sysPro.getCharSetName());
                byte[] cipherBytes = sfkEncrypt.encrypt(algorithm, mode, padding, iv, byteAry);
                //byte[] cipherBytes = sfkEncrypt.encrypt();
                resultStrAry[i] = new String(cipherBytes, sysPro.getCharSetName());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }*/

    @Override
    public resultDTO encrypt(String[] srcStrAry) {
        if (srcStrAry == null) {
            throw new RuntimeException("srcStrAry should not be null.");
        }
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();
        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            System.out.println("The length of this encrypt array is: " + srcStrAry.length);
            for (int i = 0; i < srcStrAry.length; i++) {
                if (i >= sfkConfig.getMaxSrcStrArySize() || srcStrAry[i] == null ||
                        srcStrAry[i].length() > sfkConfig.getMaxPlainTextLength()) {
                    System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i] + ". The length of " +
                            "this string is: " + srcStrAry[i].length());
                    System.out.println("sfkConfig.getMaxSrcStrArySize() is: " + sfkConfig.getMaxSrcStrArySize());
                    resultStrAry[i] = null;
                    continue;
                }
                if (srcStrAry[i].equals("") || srcStrAry[i].replaceAll("\\s+", "").equals("")) {
                    System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i]);
                    resultStrAry[i] = srcStrAry[i];
                    continue;
                }
                System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i]);
                byte[] plainBytes = srcStrAry[i].getBytes(sysPro.getCharSetName());
                byte[] cipherBytes = sfkEncrypt.encrypt(algorithm, mode, padding, iv,
                        sfkConfig.getEncryptKeyString(),plainBytes);
                //byte[] cipherBytes = sfkEncrypt.encrypt();
                resultStrAry[i] = new String(cipherBytes, sysPro.getCharSetName());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }

    @Override
    public resultDTO decrypt(String[] srcStrAry) throws Exception {
        if (srcStrAry == null) {
            return null;
            //throw new RuntimeException("srcStrAry should not be null.");
        }
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();

        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            for (int i = 0; i < srcStrAry.length; i++) {
                if (i >= sfkConfig.getMaxSrcStrArySize() || srcStrAry[i] == null ||
                        srcStrAry[i].length() > sfkConfig.getMaxCipherTextLength()) {
                    System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i] + ". The length of " +
                            "this string is: " + srcStrAry[i].length() + "无需执行加密，赋 null 即可");
                    resultStrAry[i] = null;
                    continue; }
                if (srcStrAry[i].equals("") || srcStrAry[i].replaceAll("\\s+", "").equals("")) {
                    System.out.println("srcStrAry[" + i + "] is: " + srcStrAry[i] + "无需执行加密，原样返回");
                    resultStrAry[i] = srcStrAry[i];
                    continue;
                }
                byte[] cipherBytes = srcStrAry[i].getBytes(sysPro.getCharSetName());
                byte[] plainBytes = sfkEncrypt.decrypt(algorithm, mode, padding, iv,
                        sfkConfig.getEncryptKeyString(), cipherBytes);
                if (plainBytes == null) {
                    resultStrAry[i] = srcStrAry[i];
                    continue;
                }
                resultStrAry[i] = new String(plainBytes, sysPro.getCharSetName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }

    @Override
    public resultDTO scramble(String[] srcStrAry) {
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String logicStr = "12,8#6,5";
        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            for (int i = 0; i < srcStrAry.length; i++) {
                String swappedStr = sfkEncrypt.swapString(srcStrAry[i], logicStr);
                byte[] swappedByte = swappedStr.getBytes(sysPro.getCharSetName());
                byte[] scrambledByte = sfkEncrypt.encrypt(sesRtConfigs.getAlgorithm(), sesRtConfigs.getMode(),
                        sesRtConfigs.getPadding(), iv,sfkConfig.getScrambleKeyString(), swappedByte);
                resultStrAry[i] = new String(scrambledByte, sysPro.getCharSetName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }

    @Override
    public resultDTO unscramble(String[] srcStrAry) {
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String logicStr = "12,8#6,5";

        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            for (int i = 0; i < srcStrAry.length; i++) {
                byte[] scrambledByte = srcStrAry[i].getBytes(sysPro.getCharSetName());
                byte[] swappedByte = sfkEncrypt.decrypt(sesRtConfigs.getAlgorithm(), sesRtConfigs.getMode(),
                        sesRtConfigs.getPadding(), iv,sfkConfig.getScrambleKeyString(), scrambledByte);
                if (swappedByte == null) {
                    resultStrAry[i] = srcStrAry[i];
                    continue;
                }
                String plainStr = sfkEncrypt.reverseSwapString(new String(swappedByte, sysPro.getCharSetName()), logicStr);
                resultStrAry[i] = plainStr;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }

/*
    @Override
    public resultDTO encryptedToScrambled(String[] srcStrAry) {
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();

        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            for (int i = 0; i < srcStrAry.length; i++) {
                byte[] plainBytes = srcStrAry[i].getBytes(sysPro.getCharSetName());
                byte[] cipherBytes = sfkEncrypt.scrambleEncrypt(algorithm, mode, padding, iv, plainBytes);
                resultStrAry[i] = new String(cipherBytes, sysPro.getCharSetName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }

    @Override
    public resultDTO scrambledToEncrypted(String[] srcStrAry) {
        String[] resultStrAry = new String[srcStrAry.length];
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();

        try {
            byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
            for (int i = 0; i < srcStrAry.length; i++) {
                byte[] cipherBytes = srcStrAry[i].getBytes(sysPro.getCharSetName());
                byte[] plainBytes = sfkEncrypt.scrambleDecrypt(algorithm, mode, padding, iv, cipherBytes);
                resultStrAry[i] = new String(plainBytes, sysPro.getCharSetName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sysPro.getResultDTO(resultStrAry);
    }
*/

/*
    public String encrypt1(String plainText) throws Exception {//"string"->"9pJB5z4rbrNuj2boJJj0FQ=="
        //SystemProperties sysPro = new SystemProperties();
        System.out.println("即将执行 encrypt1 的 srcRep.listSesRtConfigs().get(0)");
        //SesRtConfigs sesRtConfigs = srcRep.listSesRtConfigs().get(0);
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();
        byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("KeyForEncryption".getBytes(sysPro.getCharSetName()));
        KeyGenerator generator = KeyGenerator.getInstance(algorithm);
        generator.init(128, random);
        SecretKey key = generator.generateKey();
        byte[] plainBytes = plainText.getBytes(sysPro.getCharSetName());
        try {
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] cipherBytes = cipher.doFinal(plainBytes);
            return Base64.getEncoder().encodeToString(cipherBytes);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new Exception("Exception at Method encrypt() Message: " + e.getMessage());
        }
    }

    public String encrypt2(String plainText) throws Exception {//"string"->"9pJB5z4rbrNuj2boJJj0FQ=="
        System.out.println("即将执行 encrypt2 的 srcRep.listSesRtConfigs().get(0)");
        //SesRtConfigs sesRtConfigs = srcRep.listSesRtConfigs().get(0);
        List<SesRtConfigs> listSesRtConfigs = srcRep.listSesRtConfigs();
        SesRtConfigs sesRtConfigs = listSesRtConfigs.get(0);
        String algorithm = sesRtConfigs.getAlgorithm();
        String mode = sesRtConfigs.getMode();
        String padding = sesRtConfigs.getPadding();
        byte[] iv = Hex.decodeHex(sesRtConfigs.getIv().toCharArray());
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed("KeyForEncryption".getBytes(sysPro.getCharSetName()));
        KeyGenerator generator = KeyGenerator.getInstance(algorithm);
        generator.init(128, random);
        SecretKey key = generator.generateKey();
        byte[] plainBytes = plainText.getBytes(sysPro.getCharSetName());
        try {
            Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] cipherBytes = cipher.doFinal(plainBytes);
            return Base64.getEncoder().encodeToString(cipherBytes);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new Exception("Exception at Method encrypt() Message: " + e.getMessage());
        }
    }
*/


}
