package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.sonicjobs.base.BaseUrl;
import com.sonicjobs.base.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import io.restassured.response.Response;

public class StagingUkLogin extends BaseUrl {

	Properties prop;

	@Test(priority = 1)
	public static void forceUpdate_staginguk() {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("appType", "JOBSEEKER");
		map.put("appType", "1.2.240");
		map.put("appType", "IOS");

		JSONObject jb = new JSONObject();
		jb.put("appType", "JOBSEEKER");
		jb.put("version", "1.2.260");
		jb.put("os", "IOS");

		given().contentType("application/json").body(jb.toString()).when()
				.post(stagingBaseurlUk + "/forceUpdate/update").then().statusCode(200).body("data", equalTo("IGNORE"));

	}

	@Test(priority = 2)
	public void loginExistinguserStaginguk() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("uk_email1"), "IOS");

		Response res = given().contentType("application/json").body(login.toString()).when()
				.post(stagingBaseurlUk + "/auth/jobseeker/password");

		jobseekerToken = res.jsonPath().get("data.token");

	}

	@Test(priority = 3)
	public void loginExistinguser2Uk() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("uk_email2"), "IOS");

		Response res = given().contentType("application/json").body(login.toString()).when()
				.post(stagingBaseurlUk + "/auth/jobseeker/password");

		jobseekerToken2 = res.jsonPath().get("data.token");

	}

	@Test(dependsOnMethods = { "loginExistinguserStaginguk" })
	public void oldSearch() {

		JSONObject obj = new JSONObject();
		obj.put("postcode", "W1C 1DE");
		obj.put("county", "Greater London");
		obj.put("active", "true");
		obj.put("type", "POSTCODE");
		obj.put("name", "London");

		String cordinate[] = { "-0.141331712", "51.515529775399997" };
		obj.put("location", cordinate);

		given().contentType("application/json").header("AUTH-TOKEN", jobseekerToken).body(obj.toString())
				.post(stagingBaseurlUk + "/search/jobs/0/0?micro=Test&macro=Other").then().statusCode(200)
				.body("data.content.searchType[1]", equalTo("JOB"));

	}

	@Test(dependsOnMethods = { "loginExistinguser2Uk" })
	public void newSearch() {
		given().contentType("application/json").header("AUTH-TOKEN", jobseekerToken2).get(stagingBaseurlUk
				+ "/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=38.43589401245117&location_long=-0.0844489145&location_lat=51.5054420236&location_name=London&page=1&page_size=30&agent=USER")
				.then().statusCode(200).body("data.content[0].active", equalTo(true))
				.body("data.content.searchType[1]", equalTo("JOB"));

	}

}
