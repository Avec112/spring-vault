package io.avec.springvault;

import io.avec.springvault.config.VaultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

@Slf4j
@SpringBootApplication
public class SpringVaultApplication implements CommandLineRunner {

    @Autowired
    private VaultTemplate vaultTemplate;

    @Autowired
    private VaultConfig vaultConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // get from specific secret (in this case "application")
        VaultResponse response = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("application");
        log.debug("secret/application/mysecretkey = {}", response.getData().get("mysecretkey"));

        response = vaultTemplate.opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("another-secret");
        log.debug("secret/another-secret/mysecretkey = {}", response.getData().get("mysecretkey"));

        // get from a created object defaulting to "application" but can be changed by setting "spring.application.name to whatever you choose inside Vault.
        log.debug("vaultApplicationConfig.getMysecretkey() = {}", vaultConfig.getMysecretkey());
    }
}
