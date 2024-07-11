	package com.airline.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporting extends TestListenerAdapter
{	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		
		
		String projectWorkingDir = System.getProperty("user.dir");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).replace(".", "_");
		sparkReporter = new ExtentSparkReporter(projectWorkingDir+"/test-output/Extent Report/"+timeStamp+".html");
		try 
		{
		sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		}
		catch(IOException e)
		{
			e.getStackTrace();
		}
		
		sparkReporter.config().setDocumentTitle("API Automation Report");	// Title of the report
		sparkReporter.config().setReportName("Functional Report");		// Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Automation Tester", "Vikas Ingle");
		extent.setSystemInfo("Browser", "Firefox");
	
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());	// Creates new Entry in project
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));	// send the passed information to the report with GREEN color highlighted
		
	}
	
	public void onTestFailure(ITestResult tr)
	{
		
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));	// send the passed information to the report with RED color highlighted
		
		try
		{
		logger.log(Status.INFO, tr.getThrowable());
		
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	
	
}
