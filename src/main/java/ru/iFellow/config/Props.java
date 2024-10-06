package ru.iFellow.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/test/resources/ru.iFellow/conf.properties"
})

public interface Props extends Config {
    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("loginPageUrl")
    String loginPageUrl();

    @Key("issueType")
    String issueType();

    @Key("summary")
    String summary();

    @Key("createdTaskName")
    String createdTaskName();

    @Key("bugIssueType")
    String bugIssueType();

    @Key("bugSummary")
    String bugSummary();

    @Key("bugDescription")
    String bugDescription();

    @Key("bugEnvironment")
    String bugEnvironment();

    @Key("newBugSummary")
    String newBugSummary();
}

