/**
 * 
 */
package com.airline.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author vikas ingle
 *
 */
public class RestUtils
{

	public static String passName()
	{
		String name = RandomStringUtils.randomAlphabetic(6);
		
		return name;
	}
	
	
	public static int passTrips()
	{
		int trips = RandomUtils.nextInt(0,999);
		
		return trips;
	}
	
	
	public static int airline()
	{
		int airlineID = RandomUtils.nextInt(0,9999);
		
		return airlineID;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
