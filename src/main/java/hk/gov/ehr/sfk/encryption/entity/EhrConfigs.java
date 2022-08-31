package hk.gov.ehr.sfk.encryption.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="EHR_CONFIGS")
public class EhrConfigs {
    @Id
    @Column(name="PROJECT_CD")
    private String projectCd;
    @Column(name="MODULE_CD")
    private String moduleCd;
    @Column(name="SERVICE_CD")
    private String serviceCd;
    @Column(name="SERVICE_VER")
    private String serviceVer;
    @Column(name="CONFIG_PROFILE_CD")
    private String configProfileCd;
    @Column(name="CONFIG_TYPE")
    private String configType;
    @Column(name="CONFIG_NAME")
    private String configName;
    @Column(name="CONFIG_DESC")
    private String configDesc;
    @Column(name="CONFIG_VALUE")
    private String configValue;
    @Column(name="CONFIG_SEQ")
    private String configSeq;
    @Column(name="LAST_UPD_BY")
    private String lastUpdBy;
    @Column(name="LAST_UPD_DTM")
    private String lastUpdDtm;
}
