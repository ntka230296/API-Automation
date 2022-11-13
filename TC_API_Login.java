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

public class TC_API_Login {
	
	private String account;
	private String password;
	private Response response;
	private ResponseBody resBody;
	private JsonPath jsonBody;
	
	@BeforeClass
	public void init() {
		String baseUrl = PropertiesFileUtils.getProperty("baseUrl");
		String loginPath = PropertiesFileUtils.getProperty("loginPath");
		account = PropertiesFileUtils.getProperty("account");
		password = PropertiesFileUtils.getProperty("password");
		
		RestAssured.baseURI = baseUrl;
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("account", account);
		body.put("password", password);
		
		RequestSpecification request = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(body);
		
		response = request.post(loginPath);
		resBody = response.body();
		jsonBody = resBody.jsonPath();
		
		System.out.println(" " + resBody.asPrettyString());
	}
	
	@Test(priority=0)
	public void TC01_Validate200_OK() {
		assertEquals(response.getStatusCode(), 200, "Status code is not matched!");
	}
	
	@Test(priority=1)
	public void TC02_ValidateMessage() {
		assertTrue(resBody.asString().contains("message"), "Message field is missing!");
		String resMessage = jsonBody.getString("message");
		assertEquals(resMessage, "Đăng nhập thành công", "Message is not correct!");
	}
	
	@Test(priority=2)
	public void TC03_ValidateToken() {
		assertTrue(resBody.asString().contains("token"), "Token field is missing!");
		String token = jsonBody.getString("token");
		PropertiesFileUtils.saveToken("token", token);
	}
	
	@Test(priority=3)
	public void TC04_ValidateUserType() {
		assertTrue(resBody.asString().contains("user"), "User field is missing!");
		assertTrue(resBody.asString().contains("type"), "Type field is missing!");
		String resType = jsonBody.getString("user.type");
		assertEquals(resType, "UNGVIEN", "Type is not correct!");
	}
	
	@Test(priority=4)
	public void TC05_ValidateAccount() {
		assertTrue(resBody.asString().contains("user"), "User field is missing!");
		
		assertTrue(resBody.asString().contains("account"), "Account field is missing!");
		String resAcc = jsonBody.getString("user.account");
		assertEquals(resAcc, account, "Account is not correct!");
		
		assertTrue(resBody.asString().contains("password"), "Password field is missing!");
		String resPass = jsonBody.getString("user.password");
		assertEquals(resPass, password, "Password is not correct!");
	}
}
