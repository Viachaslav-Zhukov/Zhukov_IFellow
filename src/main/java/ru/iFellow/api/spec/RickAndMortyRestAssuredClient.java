package ru.iFellow.api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import ru.iFellow.config.Props;

import static io.restassured.http.ContentType.JSON;

public class RickAndMortyRestAssuredClient {
    private static final Props props = ConfigFactory.create(Props.class);

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(JSON)
                .setBaseUri(props.rickAndMortyBaseUrl())
                .build();
    }
}



