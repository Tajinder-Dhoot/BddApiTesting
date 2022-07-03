
@PlaceApi
Feature: Validations for Place API

  @AddPlaceApi
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<address>" "<website>"
    When user calls API "addPlaceAPI" with "Post" http request
    Then API call got success with status code "<status_code>"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Then Verify place_id created maps to "<name>" using "getPlaceAPI"

  	Examples: 
      | API  					| status_code 	|	name						|	address							|	website						|
      #| addPlaceAPI 	|     200 			|	Tajinder Singh	|	Panjab, Khalistan		|	www.Khalistan.com	|
      | addPlaceAPI 	|     200 			|	Tajinder Singh	|	Dhoot, Panjab				|	www.panjab.com		|
      
  @DeletePlaceApi
  Scenario Outline: Verify that a place is being deleted using DeletePlaceAPI
    Given Delete Payload
    When user calls API "deletePlaceAPI" with "Post" http request
    Then API call got success with status code "<status_code>"
    And "status" in response body is "OK"

  	Examples: 
      | status_code 	|
      |     200 			|
