package hk.gov.ehr.sfk.encryption.entity;

import lombok.Data;

/**
 * @author yunzh
 */
@Data
public class ServiceInfo {

    private String algorithm;
    private String mode;
    private String padding;
    private String provider;
    private String keyStoreName;
	/** private String keyStoreKey */
    private char[] localKeyStoreKey;
    private char[] remoteKeyStoreKey;
    private String keyAlias;
    private byte[] iv;
    private int keyStoreSlot;
    private String hsmConfigPath;
    private String projectCd;
    private String systemCd;
    private String alsDs;
    private String srtPath;
    private String moduleCd;
}