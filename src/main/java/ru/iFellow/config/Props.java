package ru.iFellow.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/ru.iFellow/conf.properties"
})
public interface Props extends Config {
    @Key("name")
    String name();

    @Key("job")
    String job();

    @Key("createUserPath")
    String createUserPath();

    @Key("deleteUserPath")
    String deleteUserPath();

    @Key("getUserPath")
    String getUserPath();

    @Key("userBaseUrl")
    String userBaseUrl();

    @Key("rickAndMortyBaseUrl")
    String rickAndMortyBaseUrl();

    @Key("mortyName")
    String mortyName();

    @Key("characterEndpoint")
    String characterEndpoint();
}
