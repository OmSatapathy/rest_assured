package com.sonicjobs.apitest;

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

import io.restassured.response.Response;

public class ProdUK_Login extends BaseUrl {

	@Test(priority = 1)
	public static void forceUpdateProduk() {

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("appType", "JOBSEEKER");
		map.put("appType", "1.2.240");
		map.put("appType", "IOS");

		JSONObject jb = new JSONObject();
		jb.put("appType", "JOBSEEKER");
		jb.put("version", "1.2.260");
		jb.put("os", "IOS");

		given().contentType("application/json").body(jb.toString()).when().post(prodBaseurlUk + "/forceUpdate/update")
				.then().statusCode(200).body("data", equalTo("IGNORE"));

	}

	@Test(priority = 2)
	public void loginExistinguserUk() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("uk_email1"), "IOS");

		Response res = given().contentType("application/json").body(login.toString()).when()
				.post(prodBaseurlUk + "/auth/jobseeker/password");

		jobseekerToken = res.jsonPath().get("data.token");

	}

	@Test(priority = 3)
	public void loginExistinguser2Uk() {

		Properties prop = FileReader.readFile();
		JSONObject login = FileReader.getUserByEmail(prop.getProperty("uk_email2"), "IOS");

		Response res = given().contentType("application/json").body(login.toString()).when()
				.post(prodBaseurlUk + "/auth/jobseeker/password");

		jobseekerToken2 = res.jsonPath().get("data.token");

	}

}
