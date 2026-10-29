package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseTest.baseClass;

import pageObject.homePage;
import pageObject.registrationPage;

public class TC02_registrationTest extends baseClass{
//WebDriver driver;

	

	@Test(groups={"main","Master"})
	public void register() throws InterruptedException {
		
		
		
		logger.info("Execution started for REGISTRATION TEST....");  
		
		try {
		homePage hp=new homePage(driver);
		hp.click_registerHere();
		
		
		logger.info("Cicked on Register here link...");
		Thread.sleep(3000);
		
		registrationPage registerPage=new registrationPage(driver);
		
		registerPage.set_firstname("John");
		registerPage.set_lastname("Jacobbb");
		registerPage.set_Gender();
		registerPage.setEmail("johnJacobbb@test.com");
		registerPage.setphone("9001002002");
		registerPage.set_Password("John@1234");
		registerPage.set_ConfirmPassword("John@1234");
		registerPage.selectAgeCheckbox();
		registerPage.submit();
		logger.info("Entered mandatory fields...");
		String msg=registerPage.getConfirmationMsg();
		
		AssertJUnit.assertEquals(msg, "Account Created Successfully");
		
		logger.info("account created...");
		}
		catch(Exception e)
		
		{
			logger.error("Testcase failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("Execution completed.....");
	}
	
}
