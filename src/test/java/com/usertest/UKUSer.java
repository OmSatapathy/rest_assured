package com.usertest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UKUSer {
	
	
	public static String stagingbaseurl_uk = "https://hunter-uk-master.sonic-dev.net/hunter/api/v2";
	public static String stagingbaseurl_us = "https://hunter-us-master.sonic-dev.net/hunter/api/v2";
	
	static String jobseekerToken ;
	static String jobseekerToken2 ;

	@Test(priority=1)
	public static void forceUpdate() {
		
	
		

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
	              .post(stagingbaseurl_uk+"/forceUpdate/update")
	        .then()
	        .statusCode(200)
                 .body("data", equalTo("IGNORE"));

                
	

	}	
	
   @Test(priority = 2)
   public void login_existinguser_uk() {
	   
		JSONObject login = new JSONObject();
		login.put("email", "totaljob5@yopmail.com");
		login.put("password", "Jet@12345");
		login.put("deviceType", "IOS");
		
		
  Response res =       given()
              .contentType("application/json")
              .body(login.toString())
        .when()
              .post(stagingbaseurl_uk+"/auth/jobseeker/password");
        
	
  jobseekerToken =  res.jsonPath().get("data.token");


 System.out.println(jobseekerToken);
	   
   }
   
  @Test(priority = 3)
   public void login_existinguser2_uk() {
	   
		JSONObject login = new JSONObject();
		login.put("email", "18jan@yopmail.com");
		login.put("password", "Jet@12345");
		login.put("deviceType", "ANDROID"); 
		
		
 Response res =       given()
             .contentType("application/json")
             .body(login.toString())
       .when()
             .post(stagingbaseurl_uk+"/auth/jobseeker/password");
    
    
	
 jobseekerToken2 =  res.jsonPath().get("data.token");

  }
  
  @Test(dependsOnMethods = {"login_existinguser_uk"}) 
   public void oldSearch() {
	  
	   
	   JSONObject obj = new JSONObject();
	   obj.put("postcode", "W1C 1DE");
	   obj.put("county", "Greater London");
	   obj.put("active", "true");
	   obj.put("type", "POSTCODE");
	   obj.put("name", "London");
		
		String cordinate[] = {"-0.141331712","51.515529775399997"};
		obj.put("location",cordinate);
		
		
		
		//System.out.println(obj.toString());
		
		
		
		given()
		     .contentType("application/json")
		     .header("AUTH-TOKEN",jobseekerToken)
		     .body(obj.toString())
		     .post(stagingbaseurl_uk+"/search/jobs/0/0?micro=Test&macro=Other")
		     .then()
		     .statusCode(200)
             .body("data.content.searchType[1]", equalTo("JOB"));
		     
		
	
	   
   }
  

  @Test(dependsOnMethods = { "login_existinguser2_uk" })
  public void newSearch() {
	   given()
		     .contentType("application/json")
		     .header("AUTH-TOKEN",jobseekerToken2)
		     .get(stagingbaseurl_uk+"/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=38.43589401245117&location_long=-0.0844489145&location_lat=51.5054420236&location_name=London&page=1&page_size=30&agent=USER")
		     .then()
		     .statusCode(200)
		     .body("data.content[0].active", equalTo(true))
             .body("data.content.searchType[1]", equalTo("JOB"));
		     
		
		

	   
	   
  }

}
