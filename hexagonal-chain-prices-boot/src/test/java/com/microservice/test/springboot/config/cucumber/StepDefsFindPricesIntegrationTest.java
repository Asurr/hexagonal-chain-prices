package com.microservice.test.springboot.config.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class StepDefsFindPricesIntegrationTest extends SpringIntegrationTest {

    private static final String URL = "http://localhost:8081/hexagonal-chain-prices/findPrice";

    @When("the user calls with product_id {int} and application_date {string}")
    public void the_client_search_POST_200(int productId,String applicationDate) throws Throwable {
        JSONObject jsonObj = new JSONObject()
                .put("application_date",applicationDate)
                .put("product_id",String.valueOf(productId))
                .put("brand_id","1");
        executePost(URL,jsonObj);
    }


    @Then("the user recive status_code {int} and brand_id {int} and product_id {int} and price_list {int} and start_date {string}")
    public void the_client_receives_status_code(int statusCode,int brand,int product,int price,String start){
        final Integer currentStatusCode = latestResponse.getStatusCodeValue();

        if(statusCode==200){
            try {
                final Integer brandId = latestResponse.getBody().getBrandId();
                final Integer productId = latestResponse.getBody().getProductId();
                final Integer priceList = latestResponse.getBody().getPriceList();
                final String startDate = latestResponse.getBody().getStartDate().toInstant().toString();

                assertThat("brand id is incorrect : " + latestResponse.getBody(), brandId, is(brand));
                assertThat("product id  is incorrect : " + latestResponse.getBody(), productId, is(product));
                assertThat("price list  is incorrect : " + latestResponse.getBody(), priceList, is(price));
                assertThat("start Date is incorrect : " + latestResponse.getBody(), startDate, is(start));
            }catch (Exception e){
                log.error("Error to get body values {}",e.getMessage());
            }

        }
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode, is(statusCode));
    }


}
