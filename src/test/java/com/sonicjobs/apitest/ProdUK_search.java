package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.sonicjobs.base.BaseUrl;

public class ProdUK_search extends BaseUrl{

	
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
			
			
			
			given()
			     .contentType("application/json")
			     .header("AUTH-TOKEN",jobseekerToken)
			     .body(obj.toString())
			     .post(prodbaseurl_uk+"/search/jobs/0/0?micro=Test&macro=Other")
			     .then()
			     .statusCode(200)
	             .body("data.content.searchType[1]", equalTo("JOB"));
			     
		   
	   }
	   
	   
	   @Test(dependsOnMethods = { "login_existinguser2_uk" })
	   public void newSearch() {
	 	   given()
	 		     .contentType("application/json")
	 		     .header("AUTH-TOKEN",jobseekerToken2)
	 		     .get(prodbaseurl_uk+"/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=38.43589401245117&location_long=-0.0844489145&location_lat=51.5054420236&location_name=London&page=1&page_size=30&agent=USER")
	 		     .then()
	 		     .statusCode(200)
	 		     .body("data.content[0].active", equalTo(true))
	              .body("data.content.searchType[1]", equalTo("JOB"));
  
	   }
	   
}
