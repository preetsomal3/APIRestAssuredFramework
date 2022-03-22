package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpec() throws IOException {
		
		if(req==null) {
			Date d = new Date();
			DateFormat df = new SimpleDateFormat("ddMMMyyyy_HH-mm-ss");
			
			
		PrintStream log = new PrintStream(new FileOutputStream("logs\\logging.txt"+df.format(d)));
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public String getGlobalValue(String key) throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		
		prop.load(new FileInputStream("src/test/java/resources/global.properties"));
		return prop.getProperty(key);
		
		
	}
	
	public String getJsonPath(Response response, String key) {
		
		JsonPath js = new JsonPath(response.asString());
		return js.getString(key);
		
	}
}
