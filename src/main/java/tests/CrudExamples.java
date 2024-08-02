package tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CrudExamples {

	String id;
	JSONObject requestBody;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		Faker fake = new Faker();
		
		requestBody =  new JSONObject();
		requestBody.put("title", fake.lordOfTheRings().character());
		requestBody.put("body", fake.chuckNorris().fact());	
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void postATodoMessage() {
		
	Response response=	
					given().
						header("Content-Type", "application/json"). 
						body(requestBody.toJSONString()).			
					when(). 
						post("api/save").
					then(). 
						statusCode(200).
						body("info",is( equalTo("Todo saved! Nice job!"))). 
				extract().response();
		
	System.out.println(response.asPrettyString());
		
	id = response.jsonPath().getString("id");
	}
	
	@Test(priority = 2, dependsOnMethods = "postATodoMessage")
	public void readTodo() {
		
		Response response=	
				given().
					header("Content-Type", "application/json"). 			
				when(). 
					get("api/"+id).
				then(). 
					statusCode(200).
			extract().response();
	
		System.out.println(response.asPrettyString());		
		
	}
	
	@Test(priority = 3, dependsOnMethods = "postATodoMessage")
	public void updateTodo() {
		Response response=	
				given().
					header("Content-Type", "application/json"). 
					body(requestBody.toJSONString()).			
				when(). 
					put("api/todos/"+id).
				then(). 
					statusCode(201).
					body("msg",is( equalTo("Item updated"))). 
			extract().response();
	
     System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 4, dependsOnMethods = "postATodoMessage")
	public void deleteTodo() {
		
		Response response=	
				given().
					header("Content-Type", "application/json"). 			
				when(). 
					delete("api/delete/"+id).
				then(). 
					statusCode(200).
					body("msg",is( equalTo("Event deleted."))).
			extract().response();
	
		System.out.println(response.asPrettyString());	
		
		
	}
}
