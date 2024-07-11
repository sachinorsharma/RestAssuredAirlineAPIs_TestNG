/**
 * 
 */
package com.airline.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import com.airline.base.TestBase;
import com.airline.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

/**
 * @author vikas ingle
 *
 */
public class POST_Passenger_Record extends TestBase
{
	
	String passName = RestUtils.passName();
	int  passTrips = RestUtils.passTrips();
	int validAirlineID = 5;
	int inValidAirlineID = 78090;
	
	
	
	
	@BeforeGroups("Valid Airline ID")
	public void createValidPassengerRecord()
	{
		logger.info("<---------------- Started testing of POST_Passenger_Record- Valid Airline ID ------------->");
		
		RestAssured.baseURI = "https://api.instantwebtools.net";	// End point to create passenger
		
		httpRequest = RestAssured.given();
		
		// Creating JSONObject to pass the data of passenger
		
		JSONObject requestParams = new JSONObject();
		
		logger.info("Passennegr name: "+passName);
		logger.info("Passenger Trips: "+passTrips);
		logger.info("Passengers Valid Airline ID: "+validAirlineID);
		
		requestParams.put("name", passName);
		requestParams.put("trips", passTrips);
		requestParams.put("airline", validAirlineID);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		
		response = httpRequest.request(Method.POST,"/v1/passenger");
		
	}
	
	
	
	
	
	@Test(groups = "Valid Airline ID")		
	void verifyValidAIDStatusCode()
	{
		logger.info("Testing verifyValidAIDStatusCode");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 200);
	}
	
	
	
	@Test(groups = "Valid Airline ID")
	public void verifyValidAIDResposeBody()
	{
		logger.info("Testing verifyValidAIDResposeBody");
		
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(passName), true);
		Assert.assertEquals(responseBody.contains(Integer.toString(passTrips)), true);
		Assert.assertEquals(responseBody.contains(Integer.toString(validAirlineID)), true);
		
		
		
	}
	
	
	  @Test(groups = "Valid Airline ID")
	  public static void verifyValidAIDZGetPassengerID() {  
	  logger.info("Getting Passenger ID");
	  
	  JsonPath jsonPath = response.jsonPath();
	  
	  passengerID = jsonPath.get("_id").toString();
	  
	  logger.info("Created Passenger with id: "+passengerID);
	  
	  
	  }
	 
		
		
	@Test(groups = "Valid Airline ID")
	void verifyValidAIDstatusLine()
	{
		logger.info("Testing verifyValidAIDstatusLine");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test(groups = "Valid Airline ID")
	void verifyValidAIDContentType()
	{
		logger.info("Testing verifyValidAIDContentType");
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test(groups = "Valid Airline ID")
	void verifyValidAIDserverType()
	{
		logger.info("Testing verifyValidAIDserverType");
		
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.18.0");
	}

		
	@AfterGroups("Valid Airline ID")
	void verifyValidAIDtearDown()
	{
		logger.info("<---------------- Finished testing of POST_Passenger_Record- Valid Airline ID ------------->\"");
	}
	
	
	@BeforeGroups("Invalid Airline ID")
	public void createInvalidPassengerRecord()
	{
		logger.info("<---------------- Started testing of POST_Passenger_Record- Invalid Airline ID ------------->");
		
		RestAssured.baseURI = "https://api.instantwebtools.net";	// End point to create passenger
		
		httpRequest = RestAssured.given();
		
		// Creating JSONObject to pass the data of passenger
		
		JSONObject requestParams = new JSONObject();
		
		logger.info("Passennegr name: "+passName);
		logger.info("Passenger Trips: "+passTrips);
		logger.info("Passengers Inalid Airline ID: "+inValidAirlineID);
		
		requestParams.put("name", passName);
		requestParams.put("trips", passTrips);
		requestParams.put("airline", inValidAirlineID);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		
		response = httpRequest.request(Method.POST,"/v1/passenger");
		
	}
	
	@Test(groups = "Invalid Airline ID")		
	void verifyInvalidAIDStatusCode()
	{
		logger.info("Testing verifyInvalidAIDStatusCode");
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
		int statusCode = response.getStatusCode(); // Getting status code
		Assert.assertEquals(statusCode, 400);
	}
	
	
	@Test(groups = "Invalid Airline ID")
	void verifyInvalidAIDResposeBody()
	{
		logger.info("Testing verifyInvalidAIDResposeBody");
		
		String responseBody = response.getBody().asString();
		logger.info(responseBody);
		
		Assert.assertEquals(responseBody.contains("valid airline data must submit."), true);
	}
		
		
	@Test(groups = "Invalid Airline ID")
	void verifyInvalidAIDstatusLine()
	{
		logger.info("Testing verifyInvalidAIDstatusLine");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
		
	}
	
	@Test(groups = "Invalid Airline ID")
	void verifyInvalidAIDContentType()
	{
		logger.info("Testing verifyInvalidAIDContentType");
		
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test(groups = "Invalid Airline ID")
	void verifyInvalidAIDserverType()
	{
		logger.info("Testing verifyInvalidAIDserverType");
		
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.18.0");
	}

		
	@AfterGroups("Invalid Airline ID")
	void verifyInvalidAIDtearDown()
	{
		logger.info("<---------------- Finished testing of POST_Passenger_Record- Invalid Airline ID ------------->");
	}
	

	
	
	
}
