package tests;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import testdata.DataBuilder;
import utils.BaseComponent;
import static org.junit.Assert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SchemaValidationExample extends BaseComponent {

	@Test
	public void testResponseSchema() {
		
		Response resp = doPostRequest(DataBuilder.buildUser().toJSONString(), "api/users", 201);
		assertThat(resp.asString(), matchesJsonSchemaInClasspath("schema.json"));
		
	}
	
	
}
