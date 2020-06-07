package io.avec.springvault.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
//@ConfigurationProperties("example")
public class VaultConfig {

    @Value("${mysecretkey}")
    private String mysecretkey;
}
