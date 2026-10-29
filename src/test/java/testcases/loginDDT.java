package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.baseClass;
import pageObject.accountPage;
import pageObject.homePage;
import utilities.dataProvider;

public class loginDDT extends baseClass{
	
	
	@Test(dataProvider="LoginData",dataProviderClass=dataProvider.class)
	public void verify_DDT(String email,String pass, String exp) throws InterruptedException {
		logger.info("*****Starting TC03 Login DDTtest *******");
		homePage hp=new homePage(driver);
		
		Thread.sleep(2000);
		hp.enter_email(email);
		logger.info("Username entered....");
		Thread.sleep(2000);
		hp.enter_password(pass);
		logger.info("Password entered....");
		hp.click_submit();
		logger.info("Login successfull....");
		Thread.sleep(2000);
		
		accountPage accp =new accountPage(driver);
		boolean accountpagedisplayed= accp.isHomePageDisplayed();
		
		//Assert.assertEquals(accountpagedisplayed, true);
		
		logger.info("On Accounts Page ....");
		
		//accp.clickSignOut();
		
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(accountpagedisplayed==true)
			{
				
				accp.clickSignOut();
				Assert.assertTrue(true);
				
			}
			
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(accountpagedisplayed==true)
			{
				accp.clickSignOut();
				Assert.assertTrue(false);
				
			}
			else
			{
				hp.clearemail();
				hp.clearpassword();
				Assert.assertTrue(true);
			
			}
		}
		
		logger.info("*****Finished TC03 Login DDT test *******");
	}

}
