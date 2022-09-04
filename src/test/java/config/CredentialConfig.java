package config;

import org.aeonbits.owner.Config;

/**
 * интерфейс для доступа к проперти
 */
@Config.Sources("classpath:config/credentials.properties")
public interface CredentialConfig extends Config {

    String login();
    String password();
}
