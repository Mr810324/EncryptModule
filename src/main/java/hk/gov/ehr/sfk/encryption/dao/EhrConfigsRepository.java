package hk.gov.ehr.sfk.encryption.dao;

import hk.gov.ehr.sfk.encryption.entity.EhrConfigs;
import hk.gov.ehr.sfk.encryption.entity.SesRtConfigs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface EhrConfigsRepository extends JpaRepository<EhrConfigs,String> {
    @Query(value="Select PROJECT_CD,MODULE_CD from EHR_CONFIGS",nativeQuery=true)
    List<Map<String,String>> listEhrConfigs ();
    @Query(value = "select MODE from VW_SES_RT_CONFIGS WHERE MODE='CBC'",nativeQuery = true)
    String findMode();

}
