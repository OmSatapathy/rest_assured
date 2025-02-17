package com.user.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pojos.UserDetails;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Createuser extends UserDetails {

		
//	@Test
	public void createUsers() {
		
		UserDetails user= new Createuser();
		user.setJob("leader");
		user.setName("morpheus");
		
		Response res= RestAssured.given()
				.accept(ContentType.JSON)
				.when().body(user)
				.post("https://reqres.in/api/users")
				.andReturn();
		
	   System.out.println(res.body().asPrettyString());
		
	}
	
	
//	@Test
	public void getUser() {
	Response res=	RestAssured.given().accept(ContentType.JSON).contentType("application/json")
		.when().get("https://reqres.in/api/users?page=2")
		.andReturn();
	
	System.out.println(res.jsonPath().prettyPrint());
	
	Assert.assertEquals(res.getStatusCode(), 200);
	
	}
	
	//@Test
	public void getSingleUser() {
	
		Response res=RestAssured.given().accept(ContentType.JSON)
		.contentType("appliaction/json")
		.when()
		.get("https://reqres.in/api/users/2")
		
		.andReturn();
		
		res.asPrettyString();
		
		System.out.println(res.asPrettyString());
	}

	
	@Test
	public void registerUser() {
		
		RequestSpecification spec = RestAssured.given().accept(ContentType.JSON)
				.contentType("appliaction/json");
		
	Response res= spec.when()
		.get("https://reqres.in/api/register/2").andReturn();
	
	System.out.println(res.asPrettyString());
		
	}
}
