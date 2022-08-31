package hk.gov.ehr.sfk.encryption.service;

import hk.gov.ehr.sfk.encryption.entity.resultDTO;

import java.util.List;

public interface SfkService {
    resultDTO encrypt(String[] srcStrAry);
/*
    resultDTO encrypt1(char[][] srcChaAry);
*/
    resultDTO decrypt(String[] srcStrAry) throws Exception;
    resultDTO scramble(String[] srcStrAry);
    resultDTO unscramble(String[] srcStrAry);
/*    resultDTO encryptedToScrambled(String[] srcStrAry);
    resultDTO scrambledToEncrypted(String[] srcStrAry);*/
    }
