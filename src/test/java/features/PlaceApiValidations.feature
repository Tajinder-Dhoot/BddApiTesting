
@PlaceApi
Feature: Validations for Place API

  @AddPlaceApi
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls API "<API>" with Posthttp request
    Then API call got success with status code "<status_code>"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

  	Examples: 
      | API  					| status_code 	|
      | AddPlaceAPI 	|     200 			|
