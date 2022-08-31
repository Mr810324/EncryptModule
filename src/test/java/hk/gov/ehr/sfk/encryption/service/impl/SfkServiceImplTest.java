/*
package hk.gov.ehr.sfk.encryption.service.impl;

import hk.gov.ehr.sfk.encryption.config.SfkConfig;
import hk.gov.ehr.sfk.encryption.dao.SesRtConfigsRepository;
import hk.gov.ehr.sfk.encryption.entity.SesRtConfigs;
import hk.gov.ehr.sfk.encryption.entity.resultDTO;
import hk.gov.ehr.sfk.encryption.util.SfkEncrypt;
import hk.gov.ehr.sfk.encryption.util.SystemProperties;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SfkServiceImplTest {

    @Mock
    SesRtConfigsRepository srcRep;
    @Mock
    SfkEncrypt sfkEncrypt;
    @Mock
    SystemProperties sysPro;
    @Mock
    SfkConfig sfkConfig;
    @InjectMocks
    SfkServiceImpl sfkService;


    @Test
    public void testEncrypt1() throws Exception {
        SesRtConfigs sesRtConfigs = new SesRtConfigs();
        sesRtConfigs.setAlgorithm("AES");
        sesRtConfigs.setMode("CBC");
        sesRtConfigs.setPadding("PKCS5Padding");
        sesRtConfigs.setIv("EB408552C6564A2CA44469769B2E634B");
        List list1 = new ArrayList();
        list1.add(sesRtConfigs);
        when(srcRep.listSesRtConfigs()).thenReturn(list1);
        //when(srcRep.listSesRtConfigs().get(0)).thenReturn(sesRtConfigs);
        sfkService.sysPro = new SystemProperties();
        String cipherStr = sfkService.encrypt1("string");
        Assertions.assertTrue("xbqNs3vApSbp1cqnCFR2Cw==".equals(cipherStr));
    }



    @Test
    public void testEncrypt() throws Exception {
        SesRtConfigs sesRtConfigs = new SesRtConfigs();
        sesRtConfigs.setAlgorithm("AES");
        sesRtConfigs.setMode("CBC");
        sesRtConfigs.setPadding("PKCS5Padding");
        sesRtConfigs.setIv("EB408552C6564A2CA44469769B2E634B");
        byte[] iv = Hex.decodeHex("EB408552C6564A2CA44469769B2E634B".toCharArray());
        sfkService.sysPro = new SystemProperties();
        byte[] plainBytes = "string".getBytes(sfkService.sysPro.getCharSetName());
        List list1 = new ArrayList();
        list1.add(sesRtConfigs);
        when(srcRep.listSesRtConfigs()).thenReturn(list1);
       //when(sfkEncrypt.encrypt(sesRtConfigs.getAlgorithm(),sesRtConfigs.getMode(),sesRtConfigs.getPadding()))
        when(sfkEncrypt.encrypt("AES","CBC","PKCS5Padding",iv,plainBytes))
                .thenReturn("xbqNs3vApSbp1cqnCFR2Cw==".getBytes("UTF-8"));
        String[] strings = {"string"};
        //sfkService.sfkConfig.setMaxSrcStrArySize(100);
        when(sfkConfig.getMaxSrcStrArySize()).thenReturn(100);
        //ReflectionTestUtils.setField(sfkService.sfkConfig,"maxSrcStrArySize",100);
        resultDTO resultDTO = sfkService.encrypt(strings);
        Assertions.assertTrue("xbqNs3vApSbp1cqnCFR2Cw==".equals(resultDTO.getResultStrAry()[0]));
    }



    @Test
    public void decrypt() {
    }

    @Test
    public void scramble() {
    }

    @Test
    public void unscramble() {
    }

    @Test
    public void encryptedToScrambled() {
    }

    @Test
    public void scrambledToEncrypted() {
    }
}*/
