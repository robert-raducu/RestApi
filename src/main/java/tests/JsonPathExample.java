package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class JsonPathExample {
	
	@Test
	public void testJsonPath() {
		
		//Json path = librarie pusa independent ce stie sa parsese un JSON 
		
		Response response =  given().get("https://keytrcrud.herokuapp.com/api/users/")
				.then().extract().response();
		System.out.println(response.asString());
		JsonPath json =  response.jsonPath();
		
		System.out.println(json.getString("users"));
		
		System.out.println(json.getString("users.size()"));
		
		System.out.println(json.getString("users.email"));
		System.out.println(json.getString("users.name"));
		System.out.println(json.getString("users._id"));
		
		System.out.println(json.getString("users[0]"));
		System.out.println(json.getString("users[0].name"));
		System.out.println(json.getString("users.name[0]"));
		System.out.println(json.getString("users._id[0]"));
		System.out.println(json.getString("users[0]._id"));
		
		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.findAll{it.name == 'Vincent'}"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent'}._id"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent'}.name"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent'}.email"));
		//System.out.println(json.getString("users.findAll{it.name == 'Vincent'}.adresa"));

		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age < 51}"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age < 51}.age"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age < 51}.size()"));

		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age <= 51}"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age <= 51}.age"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age <= 51}.size()"));

		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age > 51}"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age > 51}.age"));
		System.out.println(json.getString("users.findAll{it.name =='Vincent' & it.age > 51}.size()"));

		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.findAll{it.name == 'Vincent' & it.gender == 'f'}"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' & it.gender == 'f'}.size()"));

		System.out.println("-----------------------------------------------");
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' & it.gender == ''}"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' & it.gender == ''}.size()"));

		
		System.out.println("-----------------------------------------------");
		
		System.out.println(json.getString("users.findAll{it.name == 'Bobo'}"));
		System.out.println(json.getString("users.findAll{it.name == 'Bobo'}.size()"));
		System.out.println(json.getString("users.findAll{it.name == 'Bobo' & it.gender == ''}"));
		System.out.println(json.getString("users.findAll{it.name == 'Bobo' & it.gender == null }"));

		System.out.println("-----------------------------------------------");
		System.out.println(json.getString("users.findAll{it.name == 'BOBO'}"));
		//and
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' & it.age == 5 }"));
		//or
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' | it.age == 5 }"));
		System.out.println(json.getString("users.findAll{it.name == 'Vincent' | it.age == 5 }.size()"));
		
		System.out.println("-----------------------------------------------");

		System.out.println(json.getString("users.find{it.name == 'Vincent'}"));
		
		//JSONObject obj =  (JSONObject) json.getJsonObject("users[1]");
		//System.out.println(obj.toJSONString());
		
		//comment on branch testConflict 
	
		//comment pe master branch 
	}
	
}
