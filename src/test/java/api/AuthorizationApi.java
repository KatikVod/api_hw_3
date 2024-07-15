package api;

import config.AuthConfig;
import io.qameta.allure.Step;
import models.AuthorizationBodyModel;
import models.AuthorizationResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.BookStoreApiSpec.requestSpec;
import static specs.BookStoreApiSpec.successful200ResponseSpec;

public class AuthorizationApi {

    public static String userId, token, expires;
    public static AuthConfig config = ConfigFactory.create(AuthConfig.class);

    @Step("Авторизоваться в системе")
    public static AuthorizationResponseModel sendAuthorisationRequest() {
        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setUserName(config.userName());
        authData.setPassword(config.password());

        return given(requestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(successful200ResponseSpec)
                .extract().as(AuthorizationResponseModel.class);
    }

    @Step("Установить авторизационные куки в браузере")
    public static void setBrowserCookie() {
        AuthorizationResponseModel authResponse = sendAuthorisationRequest();
        userId = authResponse.getUserId();
        token = authResponse.getToken();
        expires = authResponse.getExpires();
        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
    }

}