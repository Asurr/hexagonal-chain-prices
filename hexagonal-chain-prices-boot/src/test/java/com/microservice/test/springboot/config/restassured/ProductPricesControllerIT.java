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

  public static final String URL_SEARCH = "/hexagonal-chain-prices/search";
  public static final String HTTP_LOCALHOST = "http://localhost:";
  public static final String PRODUCT_ID = "product_id";
  public static final String APPLICATION_DATE = "application_date";
  public static final String BRAND_ID = "brand_id";
  public static final String PRICE_LIST = "price_list";
  public static final String START_DATE = "start_date";
  public static final String END_DATE = "end_date";
  public static final String PRICE = "price";
  public static final String APPLICATION_JSON = "application/json";

  @LocalServerPort
  private Integer port;
  /* ID        BRAND_ID           START_DATE                                  END_DATE                        PRICE_LIST             PRODUCT_ID          PRIORITY         PRICE           CURR

  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

      1              1         2020-06-14-00.00.00                        2020-12-31-23.59.59                       1                    35455                0             35.50            EUR

      2              1         2020-06-14-15.00.00                        2020-06-14-18.30.00                       2                    35455                1             25.45            EUR

      3              1         2020-06-15-00.00.00                        2020-06-15-11.00.00                       3                    35455                1             30.50            EUR

      4              1         2020-06-15-16.00.00                        2020-12-31-23.59.59                       4                    35455                1             38.95            EUR

 -          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA) ----> ID 1

 -          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA) ----> ID 2

 -          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA) ----> ID 1

 -          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA) ----> ID 3

 -          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA) ----> ID 4
 */


  /**
   * find a price with valid data.
   * given reference data from product that is stored in our system
   * date 2020-06-14T10:00:00.000Z
   * then return correct find price response
   */
  @Test
  void given_find_price_request_with_first_case_expect_200() throws JSONException {
    JSONObject jsonObj = new JSONObject()
            .put(APPLICATION_DATE,"2020-06-14T10:00:00.000Z")
            .put(PRODUCT_ID,"35455")
            .put(BRAND_ID,"1");

    RestAssured.given().contentType(APPLICATION_JSON)
            .body(jsonObj.toString())
      .when()
        .post(HTTP_LOCALHOST + port + URL_SEARCH)
      .then()
            .assertThat()
            .body(PRODUCT_ID, equalTo(35455))
            .body(BRAND_ID, equalTo(1))
            .body(PRICE_LIST, equalTo(1))
            .body(START_DATE, equalTo("2020-06-14T00:00:00+02:00"))
            .body(END_DATE, equalTo("2020-12-31T23:59:59+01:00"))
            .body(PRICE, equalTo(35.5F))
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
            .put(APPLICATION_DATE,"2020-06-14T16:00:00.000Z")
            .put(PRODUCT_ID,"35455")
            .put(BRAND_ID,"1");

    RestAssured.given().contentType(APPLICATION_JSON)
            .body(jsonObj.toString())
            .when()
            .post(HTTP_LOCALHOST + port + URL_SEARCH)
            .then()
            .assertThat()
            .body(PRODUCT_ID, equalTo(35455))
            .body(BRAND_ID, equalTo(1))
            .body(PRICE_LIST, equalTo(2))
            .body(START_DATE, equalTo("2020-06-14T15:00:00+02:00"))
            .body(END_DATE, equalTo("2020-06-14T18:30:00+02:00"))
            .body(PRICE, equalTo(25.45F))
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
            .put(APPLICATION_DATE,"2020-06-14T21:00:00.000Z")
            .put(PRODUCT_ID,"35455")
            .put(BRAND_ID,"1");

    RestAssured.given().contentType(APPLICATION_JSON)
            .body(jsonObj.toString())
            .when()
            .post(HTTP_LOCALHOST + port + URL_SEARCH)
            .then()
            .assertThat()
            .body(PRODUCT_ID, equalTo(35455))
            .body(BRAND_ID, equalTo(1))
            .body(PRICE_LIST, equalTo(1))
            .body(START_DATE, equalTo("2020-06-14T00:00:00+02:00"))
            .body(END_DATE, equalTo("2020-12-31T23:59:59+01:00"))
            .body(PRICE, equalTo(35.5F))
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
            .put(APPLICATION_DATE,"2020-06-15T10:00:00.000Z")
            .put(PRODUCT_ID,"35455")
            .put(BRAND_ID,"1");

    RestAssured.given().contentType(APPLICATION_JSON)
            .body(jsonObj.toString())
            .when()
            .post(HTTP_LOCALHOST + port + URL_SEARCH)
            .then()
            .assertThat()
            .body(PRODUCT_ID, equalTo(35455))
            .body(BRAND_ID, equalTo(1))
            .body(PRICE_LIST, equalTo(3))
            .body(START_DATE, equalTo("2020-06-15T00:00:00+02:00"))
            .body(END_DATE, equalTo("2020-06-15T11:00:00+02:00"))
            .body(PRICE, equalTo(30.5F))
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
            .put(APPLICATION_DATE,"2020-06-16T21:00:00.000Z")
            .put(PRODUCT_ID,"35455")
            .put(BRAND_ID,"1");

    RestAssured.given().contentType(APPLICATION_JSON)
            .body(jsonObj.toString())
            .when()
            .post(HTTP_LOCALHOST + port + URL_SEARCH)
            .then()
            .assertThat()
            .body(PRODUCT_ID, equalTo(35455))
            .body(BRAND_ID, equalTo(1))
            .body(PRICE_LIST, equalTo(4))
            .body(START_DATE, equalTo("2020-06-15T16:00:00+02:00"))
            .body(END_DATE, equalTo("2020-12-31T23:59:59+01:00"))
            .body(PRICE, equalTo(38.95F))
            .log().body()
            .statusCode(200).and()
            .extract();
  }

}
