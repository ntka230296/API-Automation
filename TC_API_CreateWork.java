package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork {
	
	private String token;
	private Response res;
	private ResponseBody resBody;
	private JsonPath jsonBody;
	
	private String myWork = "Data Analyst";
	private String myExperience = "3 years";
	private String myEducation = "University degree";
	
	@BeforeClass
	public void init() {
		String baseUrl = PropertiesFileUtils.getProperty("baseUrl");
		String createWorkPath = PropertiesFileUtils.getProperty("createWorkPath");
		String token = PropertiesFileUtils.getToken("token");
		
		RestAssured.baseURI = baseUrl;
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("nameWork", myWork);
		body.put("experience", myExperience);
		body.put("education", myEducation);
		
		RequestSpecification request = RestAssured.given()
				.contentType(ContentType.JSON)
				.header("token", token)
				.body(body);
		
		res = request.post(createWorkPath);
		resBody = res.body();
		jsonBody = resBody.jsonPath();
		
		System.out.println(" " + resBody.asPrettyString());
	}
	
	@Test(priority=0)
	public void TC01_Validate201_Created() {
		assertEquals(res.getStatusCode(), 201, "Status code is not matched!");
	}
	
	@Test(priority=1)
	public void TC02_ValidateWorkId() {
		assertTrue(resBody.asString().contains("id"), "Id field is missing!");
	}
	
	@Test(priority=2)
	public void TC03_ValidateNameOfWork() {
		assertTrue(resBody.asString().contains("nameWork"), "nameWork field is missing!");
		String resNameWork = jsonBody.getString("nameWork");
		assertEquals(resNameWork, myWork, "nameWork is not correct!");
	}
	
	@Test(priority=3)
	public void TC04_ValidateExperience() {
		assertTrue(resBody.asString().contains("experience"), "Experience field is missing!");
		String resExp = jsonBody.getString("experience");
		assertEquals(resExp, myExperience, "Experience is not correct!");
	}
	
	@Test(priority=4)
	public void TC05_ValidateEducation() {
		assertTrue(resBody.asString().contains("education"), "Education field is missing!");
		String resEdu = jsonBody.getString("education");
		assertEquals(resEdu, myEducation, "Education is not correct!");
	}
}
