package stepDefinitions;

import static org.junit.Assert.assertEquals;

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
import pojo.AddPlace;
import pojo.Location;

public class AddPlaceAPI {
	
//	@Given("Add Place Payload")
//	public void add_place_payload() {
//	    System.out.println("Demo");
//	}
//
//	@When("user calls API {string} with Posthttp request")
//	public void user_calls_api_with_posthttp_request(String string) {
//		
//		assertEquals(true, true);
//	}
//
//	@Then("API call got success with status code {string}")
//	public void api_call_got_success_with_status_code(String string) {
//	}
//
//	@Then("{string} in response body is {string}")
//	public void in_response_body_is(String string, String string2) {
//	}
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
		
	
	@Given("Add Place Payload")
	public void add_place_payload() {

	    System.out.println("Demo");

		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		 
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(req).body(p);
	}

	@When("user calls API {string} with Posthttp request")
	public void user_calls_API_with_posthttp_request(String string) {

		response = res.when().post("/maps/api/place/add/json").
				then().spec(resspec).extract().response();
		String responseString=response.asString();
		System.out.println(responseString);
	}

	@Then("API call got success with status code {string}")
	public void api_call_got_success_with_status_code(String string) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedKeyValue) {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String actualKeyValue = js.get(key).toString();
		assertEquals(expectedKeyValue, actualKeyValue);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
