package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BookStoreApiSpec {

    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static RequestSpecification authorisedRequestSpec(String token) {
        return with()
                .filter(withCustomTemplates())
                .log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON);
    }

    public static ResponseSpecification successful201ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static ResponseSpecification successful204ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();

    public static ResponseSpecification successful200ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

}
