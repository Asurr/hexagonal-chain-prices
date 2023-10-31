package com.microservice.test.springboot.config.restassured;

import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(
  webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductPricesControllerIT {
 
  @LocalServerPort
  private Integer port;

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-14T10:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_first_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-14T10:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
      .when()
        .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
      .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(1))
            .body("start_date", equalTo("2020-06-14T00:00:00+02:00"))
            .body("end_date", equalTo("2020-12-31T23:59:59+01:00"))
            .body("price", equalTo(35.5F))
            .log().body()
        .statusCode(200).and()
        .extract();
  }

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-14T16:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_second_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-14T16:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
            .when()
            .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
            .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(2))
            .body("start_date", equalTo("2020-06-14T15:00:00+02:00"))
            .body("end_date", equalTo("2020-06-14T18:30:00+02:00"))
            .body("price", equalTo(25.45F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-14T21:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_third_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-14T21:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
            .when()
            .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
            .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(1))
            .body("start_date", equalTo("2020-06-14T00:00:00+02:00"))
            .body("end_date", equalTo("2020-12-31T23:59:59+01:00"))
            .body("price", equalTo(35.5F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-15T10:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_fourth_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-15T10:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
            .when()
            .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
            .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(1))
            .body("start_date", equalTo("2020-06-14T00:00:00+02:00"))
            .body("end_date", equalTo("2020-12-31T23:59:59+01:00"))
            .body("price", equalTo(35.5F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-16T21:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_fifth_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-16T21:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
            .when()
            .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
            .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(4))
            .body("start_date", equalTo("2020-06-15T16:00:00+02:00"))
            .body("end_date", equalTo("2020-12-31T23:59:59+01:00"))
            .body("price", equalTo(38.95F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-15T01:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_sixth_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put("application_date","2020-06-15T01:00:00.000Z")
            .put("product_id","35455")
            .put("brand_id","1");

    RestAssured.given().contentType("application/json")
            .body(jsonObj.toString())
            .when()
            .get("http://localhost:" + port + "/hexagonal-chain-prices/findPrice")
            .then()
            .assertThat()
            .body("product_id", equalTo(35455))
            .body("brand_id", equalTo(1))
            .body("price_list", equalTo(3))
            .body("start_date", equalTo("2020-06-15T00:00:00+02:00"))
            .body("end_date", equalTo("2020-06-15T11:00:00+02:00"))
            .body("price", equalTo(30.5F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

}
