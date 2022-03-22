package stepdefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceValidations extends Utils{
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	static String placeid;
	
	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
		TestDataBuild td = new TestDataBuild();
		
		request = given().spec(requestSpec())
				.body(td.DeletePlacePayload(placeid));
	}

	
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with_name_address_language(String s1, String s2, String s3) throws IOException {

		
		TestDataBuild td = new TestDataBuild();
		AddPlace ap=td.AddPlacePayload(s1,s2,s3);
		
	
		 request = given().spec(requestSpec())
		.body(ap);
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		APIResources ar =APIResources.valueOf(resource);
		String resrc = ar.getResource();
		System.out.println(resrc);
		if(method.equals("POST"))
		 response = request.when().post(resrc).then().extract().response();
		if(method.equals("GET"))
			response = request.when().get(resrc).then().extract().response();
			
	}
	@Then("API call got success with status code as {int}")
	public void api_call_got_success_with_status_code_as(int int1) {
	 assertEquals(response.getStatusCode(),int1);
	}
	@Then("{string} in response body is {string}")
	public void status_in_response_body_is_ok(String key, String exvalue) {

      JsonPath js = new JsonPath(response.asString());
      assertEquals(js.getString(key), exvalue);
      
	}
	
	@Then("verify place_id created maps to {string} with {string}")
	public void verify_place_id_created_maps_to_with(String name, String resource) throws IOException {
		
				 placeid = getJsonPath(response,"place_id");
		 request = given().spec(requestSpec()).queryParam("place_id", placeid);
		 user_calls_with_post_http_request(resource,"GET");
		 String actualname = getJsonPath(response,"name");
		 assertEquals(actualname,name);
	}

}
