package testdata;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {

	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		Faker fake = new Faker();
		JSONObject bodyBuilder = new JSONObject();
		bodyBuilder.put("name", fake.name().firstName());
		bodyBuilder.put("email", fake.internet().emailAddress());
		bodyBuilder.put("age", fake.number().numberBetween(6, 100));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
	}
	
	
}
