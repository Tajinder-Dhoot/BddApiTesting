package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceAPI {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	Utils utils = new Utils();
	TestDataBuild data = new TestDataBuild();
		
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String website) throws IOException {

		requestSpecification = given().spec(utils.requestSpecification())
				.body(data.addPlacePayload(name, address, website));
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	}

	@Given("Delete Payload")
	public void delete_payload() {

	
	}
	
	@When("user calls API {string} with {string} http request")
	public void user_calls_API_with_http_request(String resource, String httpMethod) {

		System.out.println("passed API resource:" +resource);
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println("Resource API is:" +resourceAPI.getResource());
		
		if(httpMethod.equalsIgnoreCase("post")) {
			response = requestSpecification
					.when().post(resourceAPI.getResource())
					.then().spec(responseSpecification).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("get")) {
			response = requestSpecification
					.when().get(resourceAPI.getResource())
					.then().spec(responseSpecification).extract().response();
		}

		String responseString=response.asString();
		System.out.println(responseString);
	}

	@Then("API call got success with status code {string}")
	public void api_call_got_success_with_status_code(String string) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedKeyValue) {

		String actualKeyValue = utils.getJsonPath(response, key);
		System.out.println("Expected Key Value:" +expectedKeyValue);
		System.out.println("Actual Key Value:" +actualKeyValue);
		assertEquals(expectedKeyValue, actualKeyValue);
	}
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		String placeId = utils.getJsonPath(response, "place_id");
		
		//getAPI request specification
		requestSpecification = given().spec(utils.requestSpecification())
				.queryParam("place_id", placeId);
		user_calls_API_with_http_request(resource, "get");
		
		//verify name in getPlaceAPI response is what is expected
		String actualName = utils.getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
