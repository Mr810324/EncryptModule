package hk.gov.ehr.sfk.encryption.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SfkConfig
 * @Description TODO
 * @date 2022/08/07 15:35
 * @Version 1.0
 */
@Configuration
@Data
public class SfkConfig {
    @Value("${sfk.maxSrcStrArySize}")
    int maxSrcStrArySize;
    @Value("${sfk.maxPlainTextLength}")
    int maxPlainTextLength;
    @Value("${sfk.maxCipherTextLength}")
    int maxCipherTextLength;
    @Value("${sfk.encryptKeyString}")
    String encryptKeyString;
    @Value("${sfk.scrambleKeyString}")
    String scrambleKeyString;
}
