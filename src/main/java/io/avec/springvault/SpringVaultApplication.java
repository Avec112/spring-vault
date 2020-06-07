package io.avec.springvault;

import io.avec.springvault.config.VaultConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.annotation.VaultPropertySources;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
//@VaultPropertySource("secret/third-secret")
@VaultPropertySources(value = {
        @VaultPropertySource("secret/application"),
        @VaultPropertySource("secret/second-secret"),
        @VaultPropertySource("secret/third-secret")
})
public class SpringVaultApplication implements CommandLineRunner {

    @Value("${firstkey}")
    private String firstkey;

    @Value("${secondkey}")
    private String secondkey;

    @Value("${thirdkey}")
    private String thirdkey;

    @Autowired
    private VaultTemplate vaultTemplate;

    @Autowired
    private VaultOperations vaultOperations;

    @Autowired
    private VaultConfig vaultConfig; // assosiated with "secret/application" (default) or what is defined in spring.application.name

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        readProperties();
        writeProperties();
    }

    private void writeProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("secondkey", "second-value-written-once-more");
        map.put("otherkey", "the value exists!");
        vaultOperations.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).put("second-secret", map);
        VaultResponse response = vaultOperations.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("second-secret"); // reload after putting
        log.debug("Reading updated secret/second-secret/secondkey = {}", response.getData().get("secondkey"));
        log.debug("Reading new secret/second-secret/otherkey = {}", response.getData().get("otherkey"));
    }

    private void readProperties() {
        // get from a created object defaulting to "application" but can be changed by setting "spring.application.name" to whatever you choose inside Vault.
        log.debug("vaultConfig.getFirstkey() = {}", vaultConfig.getFirstkey());

        // get from specific secret (in this case "application")
        VaultResponse response = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("application");
        log.debug("secret/application/firstkey = {}", response.getData().get("firstkey"));

        response = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("second-secret");
        log.debug("secret/second-secret/secondkey = {}", response.getData().get("secondkey"));

        // get via @VaultPropertySources
        log.debug("firstkey = {}", firstkey);
        log.debug("secondkey = {}", secondkey);
        log.debug("thirdkey = {}", thirdkey);
    }
}
