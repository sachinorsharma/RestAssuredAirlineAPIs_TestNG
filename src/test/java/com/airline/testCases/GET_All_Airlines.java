/**
 * 
 */
package com.airline.testCases;

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
public class GET_All_Airlines extends TestBase
{
		
	@BeforeClass
	void getAllAirlines() throws InterruptedException
	{
		
		logger.info("<--------- Testing GET_All_Airlines ---------->");
		
		RestAssured.baseURI = "https://api.instantwebtools.net";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/v1/airlines/");
		
		Thread.sleep(5000);	
		
		
	}
	
	@Test
	void verifyResponseBody()
	{
		logger.info("<------------ Verifying Response Body of GET_All_Airlines ------------->");
		
		String responseBody = response.getBody().asString();
		
		logger.info("Response Body"+responseBody);
		
		Assert.assertEquals(responseBody.contains("id"), true);
		Assert.assertEquals(responseBody.contains("name"), true);
		Assert.assertEquals(responseBody.contains("country"), true);
		Assert.assertEquals(responseBody.contains("logo"), true);
		Assert.assertEquals(responseBody.contains("slogan"), true);
		
		
	}
	
	@Test
	void verifyStatusCode()
	{
		logger.info("<-----------  Checking Status Code --------->");
		
		int statusCode = response.getStatusCode();  // Getting a status code
		logger.info("Status code is: "+statusCode);	// Status code- 200
		
		Assert.assertEquals(statusCode, 200);
		
	}
	
	@Test
	void verifyResponseTime()
	{
		logger.info("<------------ Verifying Response Time ----------->");
		
		long responseTime = response.getTime(); // Get Time in milliseconds
		logger.info("Response Time: "+responseTime);
		
		if(responseTime>6000)
		{
			logger.warn("Response time is greater than 6000");
		}
		
		Assert.assertTrue(responseTime<10000);
		
	}
	
	@Test
	void verifyStatusLine()
	{
		logger.info("<------------ Verifying Status Line ----------->");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
		
	}
	
	
	@Test
	void verifyContentType()
	{
		logger.info("<------------ Verifying Response Time ----------->");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is: " + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	void verifyServerType()
	{
		logger.info("<------------ Verifying Server Type ----------->");
		
		String serverType = response.header("Server");
		logger.info("Server Type is: " +serverType); 
		Assert.assertEquals(serverType, "nginx/1.18.0");
	
	}

	
	@Test
	void verifyContentLenght()
	{
		logger.info("<------------ Verifying Content Lenghth ----------->");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is: " +contentLength); 
		
		if(Integer.parseInt(contentLength)<200)
			logger.warn("Content Length is less than 200");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>200);
		
	}
		
		
	@AfterClass
	void tearDown()
	{
		logger.info("<------------ Finished testing of GET_All_Airlines ----------->");
	}
	

}
