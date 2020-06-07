## Spring Boot and Hashicorp Vault
This Spring Boot Application demonstrates integration with Hashicorp Vault.
The application read properties from Vault and nothing more at this point.

### How to get started
* [Download Hashicorp Vault](https://www.vaultproject.io/downloads)
* [Install Vault](https://learn.hashicorp.com/vault/getting-started/install)
* Start Vault `vault server -dev -dev-root-token-id=12345` (Note! Not for production)
* Open address `http://localhost:8200` and login with token `12345`
* Click Secrets Engine `secret/`
* Create secret `application` and add key `mysecretkey` and a value
* Create secret `another-secret` and add key `mysecretkey` once more but this time give it another value
* Build the Spring Boot Application with `mvn clean install`
* Start the Spring Boot Application with `mvn spring-boot:run`

When the application starts log should display something like this
```
2020-06-07 16:20:14.380 DEBUG 28552 --- [           main] i.a.springvault.SpringVaultApplication   : secret/application/mysecretkey = mysecretvalue
2020-06-07 16:20:14.382 DEBUG 28552 --- [           main] i.a.springvault.SpringVaultApplication   : secret/another-secret/mysecretkey = another-secret
2020-06-07 16:20:14.382 DEBUG 28552 --- [           main] i.a.springvault.SpringVaultApplication   : vaultConfig.getMysecretkey() = mysecretvalue
```

### Files to inspect and play with
* bootstrap.yml
* VaultConfig.java
* SpringVaultApplication.java

### Inspiration
I got inspiration from this resources:
* https://www.youtube.com/watch?v=MaTDiKp_IrA
* https://spring.io/guides/gs/accessing-vault/
* https://docs.spring.io/spring-vault/docs/current/reference/html
* https://spring.io/guides/gs/vault-config/ 