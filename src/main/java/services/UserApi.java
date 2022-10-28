package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class UserApi {
  private String baseUrl =  System.getProperty("wiremock.hostname","http://localhost:9190");
  private String scorePath = "/user/get/{id}";
  private String userPath = "/user/get/all";
  private String coursePath = "/cource/get/all";

  private RequestSpecification reqSpec;
  public UserApi() {
    reqSpec = given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON);
  }

  public Response getScore(String id) {
    Response response = RestAssured
            .given(reqSpec)
            .log().all()
            .when()
            .get(scorePath,id)
            .andReturn();

    response.prettyPrint();

    return response;
  }
  public ValidatableResponse getListUser() {
    return given(reqSpec)
            .basePath(userPath)
            .log().all()
            .when()
            .get()
            .then()
            .statusCode(200)
            .log().all();
  }

  public ValidatableResponse getListCourse() {
    return given(reqSpec)
            .basePath(coursePath)
            .log().all()
            .when()
            .get()
            .then()
            .statusCode(200)
            .log().all();
  }

  //Метод, который проверяет наличие поля name и возвращает его значение
  public String getIntFromJson(Response response, String name) {
    response
            .then()
            .assertThat()
            .defaultParser(Parser.JSON)
            .body("$",hasKey(name)); //$ - ищем поле в корне json

    return response.jsonPath().getString(name);
  }
}
