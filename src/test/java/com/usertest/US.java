package com.usertest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class US {

	public static String stagingbaseurl_us = "https://hunter-us-master.sonic-dev.net/hunter/api/v2";
	
	static String us_jobseekerToken ;
	static String us_jobseekerToken2 ;
	
	 @Test(priority = 1)
		public static void forceUpdate_us() {
			
			
			HashMap<String, String> map = new HashMap<String, String>();
			
			map.put("appType", "JOBSEEKER");
			map.put("appType", "1.2.240");
			map.put("appType", "IOS");
			
			JSONObject jb = new JSONObject();
			jb.put("appType", "JOBSEEKER");
			jb.put("version", "1.2.260");
			jb.put("os", "IOS");
			
			   given()
		         .contentType("application/json")
	             .body(jb.toString())
	        .when()
	              .post(stagingbaseurl_us+"/forceUpdate/update")
	        .then()
	        .statusCode(200)
               .body("data", equalTo("IGNORE"));
		

		}	
		
	  @Test(priority = 2)
	  public void login_existinguser_us() {
		   
			JSONObject login = new JSONObject();
			login.put("email", "akhileh@yopmail.com");
			login.put("password", "Jet@12345");
			login.put("deviceType", "IOS");
			
			
		 us_jobseekerToken =	RestAssured.given()
	             .contentType("application/json")
	             .body(login.toString())
	       .when()
	             .post(stagingbaseurl_us+"/auth/jobseeker/password")
	       .jsonPath().get("data.token");
		 
		 System.out.println(us_jobseekerToken);

			
		   
	  }
	  
	  @Test(priority = 3)
	  public void login_existinguser2_us() {
		   
			JSONObject login = new JSONObject();
			login.put("email", "11jan@yopmail.com");
			login.put("password", "Jet@12345");
			login.put("deviceType", "IOS");
			
			
		 us_jobseekerToken2 =	RestAssured.given()
	             .contentType("application/json")
	             .body(login.toString())
	       .when()
	             .post(stagingbaseurl_us+"/auth/jobseeker/password")
	       .jsonPath().get("data.token");
		 
		 System.out.println(us_jobseekerToken2);

			
		   
	  }
	  
	  
	  
	  @Test (priority = 4)
	  public void newSearch() {
		   
		
			given()
			     .contentType("application/json")
			     .header("AUTH-TOKEN",us_jobseekerToken)
			     .get(stagingbaseurl_us+"/search/jobs?query=Cleaner&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=40.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&location_id=6183f7d05add74754e41af41&agent=USER")
			     .then()
			     .statusCode(200)
			     .body("data.content[0].active", equalTo(true))
	             .body("data.content.searchType[1]", equalTo("JOB"));

		   
	  }
	  
	  @Test (priority = 5)
	  public void newSerach2() {
		  

	       	given()
			     .contentType("application/json")
			     .header("AUTH-TOKEN",us_jobseekerToken2)
			     .get(stagingbaseurl_us+"/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=20.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&agent=USER")
			     .then() 
			     .statusCode(200)	             
	             .body("data.content[0].active", equalTo(true))
	             .body("data.content.searchType[0]", equalTo("JOB"));
	           

	     
	  }
	   
	   
}
