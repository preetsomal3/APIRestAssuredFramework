
@PlaceAPI_Tests
Feature: Validating Place APIs
  

  @AddPlace
Scenario Outline: Validate the Place is being successfully added using AddPlace API
  Given Add Place payload with "<name>" "<address>" "<language>"
  When User calls "AddPlaceAPI" with "POST" http request
  Then API call got success with status code as 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify place_id created maps to "<name>" with "getPlaceAPI" 

Examples:

  | name | address | language |
  | Londson| 12 north | CHinese  |
  
  
   @DeletePlace
Scenario: Verify Delete Place functionality
  Given Delete Place payload
  When User calls "deletePlaceAPI" with "POST" http request
  Then API call got success with status code as 200
  And "status" in response body is "OK"
 
