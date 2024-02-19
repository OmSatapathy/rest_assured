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

import io.restassured.response.Response;

public class ProdUK_Login extends BaseUrl{
	
	Properties prop;
	
	public void readFile() {
			
			
			try (FileInputStream input = new FileInputStream("src/test/java/com/sonicjobs/base/credentials.properties")) 
			{

		        prop = new Properties();


		        prop.load(input);

		} catch(Exception e){
			
	    }
			
	}
	
	
	@Test(priority=1)
	public static void forceUpdate_produk() {

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
	              .post(prodbaseurl_uk+"/forceUpdate/update")
	        .then()
	        .statusCode(200)
                 .body("data", equalTo("IGNORE"));

                
	

	}
	
	
	   @Test(priority = 2)
	   public void login_existinguser_uk() {
		   
		   readFile();
			JSONObject login = new JSONObject();
			login.put("email", prop.getProperty("uk_email1"));
			login.put("password", prop.getProperty("password"));
			login.put("deviceType", "IOS");
			
			
	  Response res =       given()
	              .contentType("application/json")
	              .body(login.toString())
	        .when()
	              .post(prodbaseurl_uk+"/auth/jobseeker/password");
	        
		
	  jobseekerToken =  res.jsonPath().get("data.token");

		   
	   }
	   
	   @Test(priority = 3)
	   public void login_existinguser2_uk() {
		   
		   readFile();
			JSONObject login = new JSONObject();
			login.put("email", prop.getProperty("uk_email2"));
			login.put("password", prop.getProperty("password"));
			login.put("deviceType", "ANDROID"); 
			
			
	 Response res =       given()
	             .contentType("application/json")
	             .body(login.toString())
	       .when()
	             .post(prodbaseurl_uk+"/auth/jobseeker/password");
	    
	    
		
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
