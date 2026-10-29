package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.baseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkrepoter;
	public ExtentReports reports;
	public ExtentTest test;
	
	
	public void onStart(ITestContext context) {
	    // not implemented
		
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.dd.hh.mm.ss");
		Date dt=new Date();
		String currentTimeStamp= df.format(dt);
		
		//String repname ="Test Report-" +currentTimeStamp+".html";

		
		String reportName = "Test-Report-" + currentTimeStamp + ".html";

		String projectPath = System.getProperty("user.dir");

		String reportFolder = projectPath + File.separator + "reports";
		
		String reportPath = reportFolder + File.separator + reportName;

		sparkrepoter = new ExtentSparkReporter(reportPath);
		
	//	sparkrepoter =new ExtentSparkReporter("//Users//samatapradhan//eclipse-workspace//cart//reports//"+repname);
		sparkrepoter.config().setDocumentTitle("AUTOMATION PROJECT...");
		sparkrepoter.config().setReportName("HERe is the report for Functional test...");
		sparkrepoter.config().setTheme(Theme.STANDARD);
		
		
		reports= new ExtentReports();
		
		reports.attachReporter(sparkrepoter);
		
		reports.setSystemInfo("Tester name", "Samata");
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("Browser used", browser);
		
		
		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			reports.setSystemInfo("GROUPS Executed", includedGroups.toString());
		}
	  }
	
	
	
	public void onTestSuccess(ITestResult result) {
		    // not implemented
		test=reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "testcase passed is: " + result.getName());
		  }

		  
		  
	public void onTestFailure(ITestResult result) {
		    // not implemented
		test=reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "testcase failed is: " + result.getName());
		test.log(Status.FAIL, "testcase failed is: " + result.getThrowable());
		
		baseClass bc=new baseClass();
		try {
		  	String imgpath=bc.captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(imgpath.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  }

		  
	public void onTestSkipped(ITestResult result) {
		    // not implemented
		test=reports.createTest(result.getName());
		test.log(Status.SKIP, "testcase skipped is: " + result.getName());
		  }
	
	public void onFinish(ITestContext context) {
	    // not implemented
		
		reports.flush();
	  }

}
