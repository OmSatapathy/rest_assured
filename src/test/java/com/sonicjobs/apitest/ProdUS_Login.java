package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.sonicjobs.base.BaseUrl;

import io.restassured.RestAssured;

public class ProdUS_Login extends BaseUrl{

Properties prop;

public void readFile() {
			
			
			try (FileInputStream input = new FileInputStream("src/test/java/com/sonicjobs/base/credentials.properties")) 
			{

		        prop = new Properties();


		        prop.load(input);

		} catch(Exception e){
			
	    }
			
	}
	
	
	 @Test(priority = 1)
		public static void forceUpdate_produs() {
			
			
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
	              .post(prodbaseurl_us+"/forceUpdate/update")
	        .then()
	        .statusCode(200)
               .body("data", equalTo("IGNORE"));
		

		}	
		
	  @Test(priority = 2)
	  public void login_existinguser_us() {
		   
		  readFile();
			JSONObject login = new JSONObject();
			login.put("email", prop.getProperty("us_email1"));
			login.put("password", prop.getProperty("password"));
			login.put("deviceType", "IOS");
			
			
		 us_jobseekerToken =	RestAssured.given()
	             .contentType("application/json")
	             .body(login.toString())
	       .when()
	             .post(prodbaseurl_us+"/auth/jobseeker/password")
	       .jsonPath().get("data.token");
		 

			
		   
	  }
	  
	  @Test(priority = 3)
	  public void login_existinguser2_us() {
		   
		    readFile();
			JSONObject login = new JSONObject();
			login.put("email", prop.getProperty("us_email2"));
			login.put("password", prop.getProperty("password"));
			login.put("deviceType", "IOS");
			
			
		 us_jobseekerToken2 =	RestAssured.given()
	             .contentType("application/json")
	             .body(login.toString())
	       .when()
	             .post(prodbaseurl_us+"/auth/jobseeker/password")
	       .jsonPath().get("data.token");
		 
	
			
		   
	  }
	  
  
	  @Test (dependsOnMethods = {"login_existinguser_us"})
	  public void newSearch() {
		   
		
			given()
			     .contentType("application/json")
			     .header("AUTH-TOKEN",us_jobseekerToken)
			     .get(prodbaseurl_us+"/search/jobs?query=developer&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=40.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&location_id=6183f7d05add74754e41af41&agent=USER")
			     .then()
			     .statusCode(200)
			     .body("data.content[0].active", equalTo(true))
	             .body("data.content.searchType[1]", equalTo("JOB"));

		   
	  }
	  
	  @Test (dependsOnMethods = {"login_existinguser2_us"})
	  public void newSerach2() {
		  

	       	given()
			     .contentType("application/json")
			     .header("AUTH-TOKEN",us_jobseekerToken2)
			     .get(prodbaseurl_us+"/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=20.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&agent=USER")
			     .then() 
			     .statusCode(200)	             
	             .body("data.content[0].active", equalTo(true))
	             .body("data.content.searchType[0]", equalTo("JOB"));
	           

	     
	  }
}
