package tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import io.restassured.response.Response;
import testdata.DataBuilder;
import utils.BaseComponent;
import static org.junit.Assert.assertThat;

public class BaseComponetTest extends BaseComponent {

	String id, email;
	
	@Test(priority=1)
	public void createUser() {
		
		Response respone = doPostRequest(DataBuilder.buildUser().toJSONString(), "api/users",
				HttpStatus.SC_CREATED);
		assertThat(respone.jsonPath().getString("msg"), is(equalTo("Successfully added!")));
		id = respone.jsonPath().getString("result._id");
		email = respone.jsonPath().getString("result.email");
	}
	
	@Test(priority=2, dependsOnMethods = "createUser")
	public void readUser() {
		
		Response response = doGetRequest("api/users/"+id, 200);
		assertThat(response.jsonPath().getString("result._id"), is(equalTo(id)));
		assertThat(response.jsonPath().getString("result.email"), is(equalTo(email)));
		
	}
	@Test(priority=3, dependsOnMethods = "createUser")
	public void deleteUser() {
		
		Response response =  doDeleteRequest("api/users/"+id, 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("It has been deleted.")));

	}
	
	
}
