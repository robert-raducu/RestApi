package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BaseComponent {
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";	
		
	}
	
	public static Response doPostRequest(String body, String path, int statusCode) {
		
		Response resp = 
				given()
					.contentType(ContentType.JSON)
				 //header("Content-Type", "application/json"). 
					.body(body)
				.when()
					.post(path)
				.then() 
				   .statusCode(statusCode) 
				   .extract().response();		
		return resp;
		
	}
	
	
	public static Response doPutRequest(String body, String path, int statusCode) {
		
		Response resp = 
				given()
					.contentType(ContentType.JSON)
				 //header("Content-Type", "application/json"). 
					.body(body)
				.when()
					.put(path)
				.then() 
				   .statusCode(statusCode) 
				   .extract().response();		
		return resp;
		
	}
	
	
	public static Response doGetRequest(String path, int statusCode) {
		
		Response resp = 
				given()
					.contentType(ContentType.JSON)
				 //header("Content-Type", "application/json"). 
				.when()
					.get(path)
				.then() 
				   .statusCode(statusCode) 
				   .extract().response();		
		return resp;
		
	}

	
	public static Response doDeleteRequest(String path, int statusCode) {
		
		Response resp = 
				given()
					.contentType(ContentType.JSON)
				 //header("Content-Type", "application/json"). 
				.when()
					.delete(path)
				.then() 
				   .statusCode(statusCode) 
				   .extract().response();		
		return resp;
		
	}
	
}
