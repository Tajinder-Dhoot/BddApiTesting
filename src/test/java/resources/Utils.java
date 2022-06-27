package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static RequestSpecification requestSpecification;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		/*
		 * for more than 1 test cases, we don't have to run the following block of code
		 */
		if(requestSpecification==null) {
			PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
			requestSpecification = new RequestSpecBuilder().setBaseUri(getGlobalProperty("baseURI")).addQueryParam("key", "qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
			return requestSpecification;
		}
		
		return requestSpecification;
		
	}
	
	public String getGlobalProperty(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(file);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
