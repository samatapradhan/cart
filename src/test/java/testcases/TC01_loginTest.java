package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.baseClass;
import pageObject.accountPage;
import pageObject.homePage;

public class TC01_loginTest extends baseClass{
	
	
	@Test(groups={"smoke","Master"})
	public void login() throws InterruptedException
	{
		
		logger.info("*****Starting TC02 Login test *******");
		homePage hp=new homePage(driver);
		
		Thread.sleep(2000);
		hp.enter_email(p.getProperty("email"));
		logger.info("Username entered....");
		Thread.sleep(2000);
		hp.enter_password(p.getProperty("password"));
		logger.info("Password entered....");
		hp.click_submit();
		logger.info("Login successfull....");
		Thread.sleep(2000);
		
		accountPage accp =new accountPage(driver);
		boolean accountpagedisplayed= accp.isHomePageDisplayed();
		
		Assert.assertEquals(accountpagedisplayed, true);
		
		logger.info("On Accounts Page ....");
		
		accp.clickSignOut();
		
		logger.info("*****Finished TC02 Login test *******");
	}
	

}
