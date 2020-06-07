## Spring Boot and Hashicorp Vault
This Spring Boot Application demonstrates integration with Hashicorp Vault.
The application read and write properties.

### How to get started
* [Download Hashicorp Vault](https://www.vaultproject.io/downloads)
* [Install Vault](https://learn.hashicorp.com/vault/getting-started/install)
* Start Vault `vault server -dev -dev-root-token-id=12345` (Note! Not for production)
* Open address `http://localhost:8200` and login with token `12345`
* Click Secrets Engine `secret/`
* Create secret `application` and add key `firstkey` and a value
* Create secret `second-secret` and add key `secondkey` once more but this time give it another value
* Create secret `third-secret` and add key `thirdkey` once more with yet another value
* Build the Spring Boot Application with `mvn clean install`
* Start the Spring Boot Application with `mvn spring-boot:run`

When the application starts log should display something like this
```
2020-06-07 18:53:34.313  INFO 9408 --- [           main] i.a.springvault.SpringVaultApplication   : No active profile set, falling back to default profiles: default
2020-06-07 18:53:35.885  INFO 9408 --- [           main] i.a.springvault.SpringVaultApplication   : Started SpringVaultApplication in 4.646 seconds (JVM running for 5.102)
2020-06-07 18:53:35.886 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : vaultConfig.getFirstkey() = first-value
2020-06-07 18:53:35.896 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : secret/application/firstkey = first-value
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : secret/second-secret/secondkey = second-value
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : firstkey = first-value
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : secondkey = second-value
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : thirdkey = third-value
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : Reading updated secret/second-secret/secondkey = second-value-written-once-more
2020-06-07 18:53:35.898 DEBUG 9408 --- [           main] i.a.springvault.SpringVaultApplication   : Reading new secret/second-secret/otherkey = the value exists!
```

### Inspiration
I got inspiration from this resources:
* https://www.youtube.com/watch?v=MaTDiKp_IrA
https://www.youtube.com/watch?v=lVZkj688R4I
* https://spring.io/guides/gs/accessing-vault/
* https://docs.spring.io/spring-vault/docs/current/reference/html
* https://spring.io/guides/gs/vault-config/ 