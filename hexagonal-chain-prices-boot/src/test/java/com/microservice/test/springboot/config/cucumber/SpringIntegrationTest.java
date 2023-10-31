package com.microservice.test.springboot.config.cucumber;

import com.microservice.test.api.dto.v1.FindPriceResponseDTO;
import com.microservice.test.springboot.config.SpringbootRestApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringbootRestApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static ResponseEntity<FindPriceResponseDTO> latestResponse = null;


    @Autowired
    protected RestTemplate restTemplate;


     void executePost(String url,JSONObject jsonObj) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         HttpEntity<String> entity = new HttpEntity<>(jsonObj.toString(), headers);
        try{
            latestResponse = restTemplate
                    .exchange(url, HttpMethod.POST, entity, FindPriceResponseDTO.class);
        }catch (HttpStatusCodeException e){
            latestResponse = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(new FindPriceResponseDTO());
        }
    }


}