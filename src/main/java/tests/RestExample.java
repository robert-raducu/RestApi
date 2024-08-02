package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RestExample {

	@SuppressWarnings("unchecked")
	@Test
	public void restExampleTest() {
		Faker fake = new Faker();
		
		JSONObject requestBody =  new JSONObject();
		requestBody.put("title", fake.lordOfTheRings().character());
		requestBody.put("body", fake.chuckNorris().fact());
		
		File fisierJson = new File("data.json");
		
		Response response = 
				given().
					header("Content-Type", "application/json"). 
					//preconditie
					//ex1 --> body sub forma de string
					//body("{\"title\":\"dawdada\","
					//		+ "\"body\":\"awdadada\"}"). 
					//ex2 --> body sub forma unui obiect json
					//body(requestBody.toJSONString()).
					//ex3 --> body sub forma unui fisier JSON
					body(fisierJson).
				when(). 
				//fac request ul 
					post("https://keytodorestapi.herokuapp.com/api/save").
				then(). 
					statusCode(200).
					body("info",is( equalTo("Todo saved! Nice job!"))).
					//log().all().
					extract().response();
					
	
		
		
		
		System.out.println(response);
		System.out.println(response.asString());
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		//System.out.println(response.getHeaders());

		assertEquals(response.statusCode(), 200);
		assertEquals(response.jsonPath().getString("info"), "Todo saved! Nice job!");
		assertThat(response.jsonPath().getString("info"), is( equalTo("Todo saved! Nice job!")));
		
		//assertThat(response.jsonPath().getString("info"), matchesPattern("[A-Za-z]+"));

		
		System.out.println(response.jsonPath().getString("id"));
		
		
	}
	
	
}
