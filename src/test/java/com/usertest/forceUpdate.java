package com.usertest;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;

import java.util.HashMap;

import org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;

import io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.response.Response;

public class forceUpdate {
	

	public static String baseurl = "https://hunter-uk-master.sonic-dev.net/hunter/api/v2";
	
	static RestAssured res;
	
	@Test
	public static void forceUpdate() {
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("appType", "JOBSEEKER");
		map.put("appType", "1.2.240");
		map.put("appType", "IOS");
		
		JSONObject jb = new JSONObject();
		jb.put("appType", "JOBSEEKER");
		jb.put("version", "1.2.260");
		jb.put("os", "IOS");
		
		
	  
		RestAssured.given()
		         .contentType("application/json")
	             .body(jb.toString())
	        .when()
	              .post(baseurl+"/forceUpdate/update")
	        .then()
                  .log().all();
	

	}	
	
   @Test
   public void login_existinguser() {
	   
		JSONObject login = new JSONObject();
		login.put("email", "totaljob5@yopmail.com");
		login.put("password", "Jet@12345");
		login.put("deviceType", "IOS");
		
		
		RestAssured.given()
              .contentType("application/json")
              .body(login.toString())
        .when()
              .post(baseurl+"/auth/jobseeker/password")
        .then()
              .log().all();

		
	   
   }
  

   

}
