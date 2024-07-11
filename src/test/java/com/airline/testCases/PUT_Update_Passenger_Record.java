/**
 * 
 */
package com.airline.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.airline.base.TestBase;
import com.airline.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

/**
 * @author vikas ingle
 *
 */
public class PUT_Update_Passenger_Record extends TestBase
{
	String passName = RestUtils.passName();
	int  passTrips = RestUtils.passTrips();
	int airlineID = 8;
	
	


	@BeforeClass
	public void updatePassengerRecord()
	{
		logger.info("<---------------- Started testing of PUT_Update_Passenger_Record ------------->");
		
		RestAssured.baseURI = "https://api.instantwebtools.net/v1/passenger/";	// End point to update passenger
		
		httpRequest = RestAssured.given();
		
		// Creating JSONObject to pass the data of passenger
		
		JSONObject requestParams = new JSONObject();
		
		logger.info("Passennegr name: "+passName);
		logger.info("Passenger Trips: "+passTrips);
		logger.info("Passengers Valid Airline ID: "+airlineID);
		
		requestParams.put("name", passName);
		requestParams.put("trips", passTrips);
		requestParams.put("airline", airlineID);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		
//		passengerID = "6289e594d0ea33dc03e8df5c";
		
		response = httpRequest.request(Method.PUT, passengerID);
		
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
		Assert.assertEquals(responseBody.contains("Passenger data put successfully completed."), true);
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
		logger.info("<---------------- Finished testing of PUT_Update_Passenger_Record ------------->\"");
	}
	

	
	
	
}
