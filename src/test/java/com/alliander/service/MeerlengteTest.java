package com.alliander.service;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by OmarChenoufInfiniot on 13-12-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"spring.mvc.view.prefix=/WEB-INF/jsp/", "spring.mvc.view.suffix=.jsp"})
public class MeerlengteTest {

    @Value("${server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testOptions() throws Exception {
        String response = doRequestTest("meerlengte", RequestMethod.OPTIONS, "", HttpStatus.OK);

        Assert.assertTrue(response.contains("OPTIONS"));
        Assert.assertTrue(response.contains("POST"));
    }

    @Test
    public void testPost() {
        doRequestTest("meerlengte", RequestMethod.POST, "", HttpStatus.BAD_REQUEST);

        String body = "{\n" +
                "\t\"cableConnectionType\": \"eenkabeltype\",\n" +
                "\t\"coordinate\": {\n" +
                "\t\t\"x\":24,\n" +
                "\t\t\"y\":80\n" +
                "\t}\n" +
                "}";
        doRequestTest("meerlengte", RequestMethod.POST, body, HttpStatus.OK);
    }

    private String doRequestTest(String pathSuffix, RequestMethod requestMethod, String body, HttpStatus expectedStatus) {
        Response response = null;
        RequestSpecification reqSpec = given()
                .log().everything()
                .contentType("application/json");

        switch (requestMethod) {
            case POST:
                response = reqSpec
                        .body(body)
                        .when()
                        .post(Application.API_PREFIX + pathSuffix);
                break;
            case OPTIONS:
                response = reqSpec
                        .when()
                        .options(Application.API_PREFIX + pathSuffix);
                break;
        }

        response = response.then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();

        return response.asString();
    }
}
