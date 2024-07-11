/**
 * 
 */
package com.airline.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.airline.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
	
/**
 * @author vikas ingle
 *
 */
public class DELETE_Passenger_Record extends TestBase
{

	
	@BeforeClass
	public void updatePassengerRecord()
	{
		logger.info("<---------------- Started testing of DELETE_Passenger_Record ------------->");
		
		RestAssured.baseURI = "https://api.instantwebtools.net/v1/passenger/";	// End point to delete passenger
		
		httpRequest = RestAssured.given();
		
//		passengerID = "6289dae1d0ea33519ae8ddf4";
		
		response = httpRequest.request(Method.DELETE, passengerID);
		
	}
	
	
	@Test()		
	void verifyStatusCode()
	{
		logger.info("Testing verifyStatusCode");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);
	}
	
	
	
	@Test()
	void verifyResposeBody()
	{
		logger.info("Testing verifyResposeBody");
		
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		Assert.assertEquals(responseBody.contains("Passenger data deleted successfully."), true);
	}
		
		
	@Test()
	void verifystatusLine()
	{
		logger.info("Testing verifystatusLine");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test()
	void verifyContentType()
	{
		logger.info("Testing verifyContentType");
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test()
	void verifyserverType()
	{
		logger.info("Testing verifyserverType");
		
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.18.0");
	}

		
	@AfterClass
	void verifyDtearDown()
	{
		logger.info("<---------------- Finished testing of DELETE_Passenger_Record ------------->\"");
	}
	

		
	
	
	
}
