package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.sonicjobs.base.BaseUrl;
import com.sonicjobs.base.FileReader;

import io.restassured.RestAssured;

public class ProdUsLogin extends BaseUrl {

	@Test(priority = 1)
	public static void forceUpdateProdus() {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("appType", "JOBSEEKER");
		map.put("appType", "1.2.240");
		map.put("appType", "IOS");

		JSONObject jb = new JSONObject();
		jb.put("appType", "JOBSEEKER");
		jb.put("version", "1.2.260");
		jb.put("os", "IOS");

		given().contentType("application/json").body(jb.toString()).when().post(prodBaseurlUs + "/forceUpdate/update")
				.then().statusCode(200).body("data", equalTo("IGNORE"));

	}

	@Test(priority = 2)
	public void loginExistinguserUs() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("us_email1"), "IOS");

		us_jobseekerToken = RestAssured.given().contentType("application/json").body(login.toString()).when()
				.post(prodBaseurlUs + "/auth/jobseeker/password").jsonPath().get("data.token");

	}

	@Test(priority = 3)
	public void loginExistinguser2Us() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("us_email2"), "IOS");

		us_jobseekerToken2 = RestAssured.given().contentType("application/json").body(login.toString()).when()
				.post(prodBaseurlUs + "/auth/jobseeker/password").jsonPath().get("data.token");

	}

}
