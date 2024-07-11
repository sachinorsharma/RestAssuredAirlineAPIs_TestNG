/**
 * 
 */
package com.airline.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author vikas ingle
 *
 */
public class TestBase 
{
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public static String passengerID;
	
	public static Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		logger = Logger.getLogger("RestAssuredFramework_TestNG");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
