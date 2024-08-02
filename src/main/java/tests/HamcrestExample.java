package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import static utils.NumberChecker.numbersOnly;


public class HamcrestExample {

	@Test
	public void hamcrestTest() {
		
		Response response = given().get("https://swapi.dev/api/planets/1/")
				.then().extract().response();
	
		System.out.println(response.asPrettyString());
		
		JsonPath json = response.jsonPath();
		
		String name = json.getString("name");
		System.out.println(name);
	
		//TestNg
		assertEquals(name, "Tatooine");
		//hamcrest assert
		//assertThat(name,"Tatooine" );
		assertThat(name, is("Tatooine"));
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is(equalTo("Tatooine")));
		
		//TestNg
		assertNotEquals(name, "Mars");
		//hamcrest
		assertThat(name, is(not("Mars")));
		assertThat(name, is(not(equalTo("Mars"))));
		
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));
		
		// startsWith  endsWith
		assertThat(name, startsWith("Tat"));
		assertThat(name, endsWith("oine"));
		System.out.println(response.asString());
		assertThat(response.asString(), startsWithIgnoringCase("{\"NAME\":\"TaToOiNe\""));
		assertThat(response.asString(), endsWithIgnoringCase("PLANETS/1/\"}"));
		
		String name2 = "  Tatooine  ";
		assertThat(name, equalToCompressingWhiteSpace(name2));
		//assertThat(name, equalToIgnoringWhiteSpace(name2));
	
		//pattern
		assertThat(name, matchesPattern("[a-zA-Z]+"));
		String gravity = json.getString("gravity");
		assertThat(gravity, matchesPattern("[a-zA-Z 0-9]+"));
		String diameter = json.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
		//and
		assertThat(response.asString(), both(containsString("orbital_period"))
				.and(containsString("10465")));
		
		//or
		assertThat(name, either(is("Tatooine")).or(is("Mars")).or(is("Terra")));
		
		assertThat(response.asString(), either(containsString("ABC"))
				.or(containsString("population")));
	
		
		assertThat(response.asString(), containsStringIgnoringCase("ArID"));
		
		assertThat(response.asString(), stringContainsInOrder("rotation_period", "orbital_period"));
	
		//allOf
		String url = json.getString("url");
		//https://swapi.dev/api/planets/1/"}
		assertThat(url, allOf(startsWith("https:"), containsStringIgnoringCase("SWAPI"),
				endsWith("1/")));
		
		
		List<String> films = json.getList("films");
		System.out.println(films.get(0));
		
		
		assertThat(films, contains(  
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
	
		assertThat(films, hasSize(5));
		assertThat(films, hasSize(lessThan(10)));
		assertThat(films, hasSize(greaterThan(3)));
		
		assertThat(films, both(hasSize(lessThan(6))).and(hasToString(containsString("films/6/"))));
		
		
		assertThat(films, contains(
				startsWithIgnoringCase("HTTPS:"),
				containsString("swapi"),
				equalTo("https://swapi.dev/api/films/4/"),
				endsWith("/5/"),
				is(not(equalTo("ABC")))));
		
		assertThat(films, hasItem("https://swapi.dev/api/films/4/"));
		
		assertThat(films, hasItems("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/5/"));
	
		assertThat(films, hasItem(startsWith("http")));
		assertThat(films, hasItem(endsWith("/6/")));
		assertThat(films, hasItem(containsString("swapi.dev")));
		
		assertThat(films, hasItems(containsString("swapi.dev"), endsWith("/1/")));

		assertThat(films, is(not(emptyCollectionOf(String.class))));
	
		
		//array
		String[] array = {json.getString("name"),json.getString("diameter"),
				json.getString("climate"), json.getString("gravity"), json.getString("terrain")};
		
		System.out.println(array[2]);
		
		assertThat(array, arrayContaining("Tatooine", "10465" ,"arid","1 standard", "desert" ));
		
		assertThat(array, arrayContainingInAnyOrder("10465","Tatooine" ,"arid","1 standard", "desert" ));
		
		assertThat(array, is(not(nullValue())));
		
		
		String orbitalPeriod = json.getString("orbital_period");
		String climate = json.getString("climate");
		String gravity2 = json.getString("gravity");
	
		System.out.println("--------------------------------------");
		System.out.println(orbitalPeriod);
		System.out.println(climate);
		System.out.println(gravity2);
		
		
		
		assertThat(orbitalPeriod, is(numbersOnly()));
		assertThat(climate, is(not(numbersOnly())));
		assertThat(gravity2, is(not(numbersOnly())));

		
	}
	
	
}
