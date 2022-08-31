package hk.gov.ehr.sfk.encryption.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.junit.Test;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @ClassName SfkQuery
 * @Description TODO
 * @date 19:55
 * @Version 1.0
 */
@JsonIgnoreProperties({})
@Data
public class SfkQuery {
    private String[] srcStrAry;
    private String consumerServCd;
    private int consumerMajVer;
    private int consumerMinVer;
}
