package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import com.sonicjobs.base.BaseUrl;
import com.sonicjobs.base.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import io.restassured.RestAssured;

public class StagingUS_Login extends BaseUrl {

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

		given().contentType("application/json").body(jb.toString()).when()
				.post(stagingBaseurlUS + "/forceUpdate/update").then().statusCode(200).body("data", equalTo("IGNORE"));

	}

	@Test(priority = 2)
	public void loginExistinguserUs() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("us_email1"), "IOS");

		us_jobseekerToken = RestAssured.given().contentType("application/json").body(login.toString()).when()
				.post(stagingBaseurlUS + "/auth/jobseeker/password").jsonPath().get("data.token");

	}

	@Test(priority = 3)
	public void loginExistinguser2Us() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("us_email2"), "IOS");

		us_jobseekerToken2 = RestAssured.given().contentType("application/json").body(login.toString()).when()
				.post(stagingBaseurlUS + "/auth/jobseeker/password").jsonPath().get("data.token");

	}

	@Test(dependsOnMethods = { "loginExistinguserUs" })
	public void newSearch() {

		given().contentType("application/json").header("AUTH-TOKEN", us_jobseekerToken).get(stagingBaseurlUS
				+ "/search/jobs?query=Driver&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=40.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&location_id=6183f7d05add74754e41af41&agent=USER")
				.then().statusCode(200).body("data.content[0].active", equalTo(true))
				.body("data.content.searchType[1]", equalTo("JOB"));

	}

	@Test(dependsOnMethods = { "loginExistinguser2Us" })
	public void newSerach2() {

		given().contentType("application/json").header("AUTH-TOKEN", us_jobseekerToken2).get(stagingBaseurlUS
				+ "/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=20.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&agent=USER")
				.then().statusCode(200).body("data.content[0].active", equalTo(true))
				.body("data.content.searchType[0]", equalTo("JOB"));

	}

}
