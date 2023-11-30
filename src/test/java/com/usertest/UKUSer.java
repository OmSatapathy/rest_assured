package com.usertest;

import org.testng.annotations.Test;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class UKUSer {
	
	
	public static String baseurl_uk = "https://hunter-uk-master.sonic-dev.net/hunter/api/v2";
	public static String baseurl_us = "https://hunter-us-master.sonic-dev.net/hunter/api/v2";
	
	static String jobseekerToken ;

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
		
	              given()
		         .contentType("application/json")
	             .body(jb.toString())
	        .when()
	              .post(baseurl_uk+"/forceUpdate/update")
	        .then()
	        .statusCode(200)
                 .body("data", equalTo("IGNORE"));

                
	

	}	
	
   @Test
   public void login_existinguser_uk(ITestContext context) {
	   
		JSONObject login = new JSONObject();
		login.put("email", "totaljob5@yopmail.com");
		login.put("password", "Jet@12345");
		login.put("deviceType", "IOS");
		
		
  Response res =       given()
              .contentType("application/json")
              .body(login.toString())
        .when()
              .post(baseurl_uk+"/auth/jobseeker/password");
     
     
	
  jobseekerToken =  res.jsonPath().get("data.token");
 
// context.setAttribute("jobseekerToken", "token");

 System.out.println(jobseekerToken);
	   
   }
   
   
   
   
  
  
//  @Test ( for old search)
   public void searchDetails(ITestContext context) {
	   
	   
	   String token = (String) context.getAttribute("user_token");
	   
	   JSONObject obj = new JSONObject();
	   obj.put("postcode", "W1C 1DE");
	   obj.put("county", "Greater London");
	   obj.put("active", "true");
	   obj.put("type", "POSTCODE");
	   obj.put("name", "London");
		
		String cordinate[] = {"-0.141331712","51.515529775399997"};
		obj.put("location",cordinate);
		
		
		
		System.out.println(obj.toString());
		
		System.out.println("JS'token in 2nd m::   " + jobseekerToken);
		
		
		given()
		     .contentType("application/json")
		     .header("AUTH-TOKEN",jobseekerToken)
		     .body(obj.toString())
		     .post(baseurl_uk+"/search/jobs/0/0?micro=Clenaer&macro=Other")
		     .then()
		     .log().all();
		
	
	   
   }
  
  
  @Test
  public void newSearch() {
	   
		
		
		given()
		     .contentType("application/json")
		     .header("AUTH-TOKEN",jobseekerToken)
		     .get(baseurl_uk+"/search/jobs?query=Cleaner&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=49.88967943466947&location_long=-0.12755&location_lat=51.5073&location_name=Charing%20Cross&page=1&page_size=30&agent=USER ")
		     .then()
		     .statusCode(200)
             .body("data.content.searchType[1]", equalTo("JOB"));
		     
		
		

	   
	   
  }

}
