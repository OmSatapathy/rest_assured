package com.usertest;

import org.testng.annotations.Test;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;




public class Mattcher {

	public static String baseurl_uk = "https://hunter-uk-master.sonic-dev.net/hunter/api/v2";
	public static String baseurl_us = "https://hunter-us-master.sonic-dev.net/hunter/api/v2";
	
	
	
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
}
