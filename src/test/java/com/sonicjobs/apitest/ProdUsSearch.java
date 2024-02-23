package com.sonicjobs.apitest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.sonicjobs.base.BaseUrl;

public class ProdUsSearch extends BaseUrl {

	@Test(dependsOnMethods = { "loginExistinguserUs" })
	public void newSearch() {

		given().contentType("application/json").header("AUTH-TOKEN", us_jobseekerToken).get(prodBaseurlUs
				+ "/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=40.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&location_id=6183f7d05add74754e41af41&agent=USER")
				.then().statusCode(200).body("data.content[0].active", equalTo(true))
				.body("data.content.searchType[1]", equalTo("JOB"));

	}

	@Test(dependsOnMethods = { "loginExistinguser2Us" })
	public void newSerach2() {

		given().contentType("application/json").header("AUTH-TOKEN", us_jobseekerToken2).get(prodBaseurlUs
				+ "/search/jobs?query=Test&full_time=false&part_time=false&remote=false&no_experience=false&sort=FEATURED&max_radius=20.0&location_long=-74.00697&location_lat=40.71222&location_name=New%20York&page=1&page_size=30&agent=USER")
				.then().statusCode(200).body("data.content[0].active", equalTo(true))
				.body("data.content.searchType[0]", equalTo("JOB"));

	}
}
