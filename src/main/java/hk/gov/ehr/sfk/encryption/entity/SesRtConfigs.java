package hk.gov.ehr.sfk.encryption.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName SesRtConfigs
 * @Description TODO
 * @date 21:26
 * @Version 1.0
 */
@Data
@Entity
@Table(name="VW_SES_RT_CONFIGS")
public class SesRtConfigs {

    @Id
    @Column(name="ALGORITHM")
    private String algorithm;
    @Column(name="MODE")
    private String mode;
    @Column(name="PADDING")
    private String padding;
/*    @Column(name="PROVIDER")
    private String provider;
    @Column(name="KEY_STORE")
    private String keystore;
    @Column(name="KEY_ALIAS")
    private String keyalias;*/
    @Column(name="IV")
    private String iv;
/*    @Column(name="KEY_STORE_SLOT")
    private String keystoreslot;
    @Column(name="HSM_CONFIG_PATH")
    private String hsmsonfigpath;
    @Column(name="PROJECT_CD")
    private String projectcd;
    @Column(name="MODU_CD")
    private String moducd;
    @Column(name="ALS_DS")
    private String alsds;
    @Column(name="SRT_PATH")
    private String srtpath;*/
}