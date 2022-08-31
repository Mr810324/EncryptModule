package hk.gov.ehr.sfk.encryption.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName resultDTO
 * @Description TODO
 * @date 20:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class resultDTO {
    private int statusCd;
    private String statusMsg;
    private String[] resultStrAry;
}
