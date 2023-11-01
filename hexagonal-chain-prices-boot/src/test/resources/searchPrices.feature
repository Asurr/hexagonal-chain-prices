Feature: the message can be retrieved
  Scenario: user makes call to POST first case
    When the user calls with product_id 35455 and application_date "2020-06-14T10:00:00.000Z"
    Then the user recive status_code 200 and brand_id 1 and product_id 35455 and price_list 1 and start_date "2020-06-13T22:00:00Z"
  Scenario: user makes call to POST second case
    When the user calls with product_id 35455 and application_date "2020-06-14T16:00:00.000Z"
    Then the user recive status_code 200 and brand_id 1 and product_id 35455 and price_list 2 and start_date "2020-06-14T13:00:00Z"
  Scenario: user makes call to POST third case
    When the user calls with product_id 35455 and application_date "2020-06-14T21:00:00.000Z"
    Then the user recive status_code 200 and brand_id 1 and product_id 35455 and price_list 1 and start_date "2020-06-13T22:00:00Z"
  Scenario: user makes call to POST fourth case
    When the user calls with product_id 35455 and application_date "2020-06-15T10:00:00.000Z"
    Then the user recive status_code 200 and brand_id 1 and product_id 35455 and price_list 3 and start_date "2020-06-14T22:00:00Z"
  Scenario: user makes call to POST fifth case
    When the user calls with product_id 35455 and application_date "2020-06-16T21:00:00.000Z"
    Then the user recive status_code 200 and brand_id 1 and product_id 35455 and price_list 4 and start_date "2020-06-15T14:00:00Z"
  Scenario: user makes call to POST seven case
    When the user calls with product_id 35456 and application_date "2020-06-15T01:00:00.000Z"
    Then the user recive status_code 404 and brand_id 1 and product_id 35455 and price_list 3 and start_date "2020-06-14T22:00:00Z"
