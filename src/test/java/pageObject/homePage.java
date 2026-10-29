package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{
	
	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	@FindBy(className="text-reset")  
	WebElement registerHere;
	
	@FindBy(css="#userEmail")
	WebElement emailID;
	
	@FindBy(css="#userPassword")
	WebElement password;
	
	@FindBy(css="#login")
	WebElement btn_submit;
	
	public void click_registerHere() {
		registerHere.click();
	}
	
	public void enter_email(String email) {
		emailID.sendKeys(email);
	}
		
		public void enter_password(String pass) {
			password.sendKeys(pass);
	}
		
		public void click_submit() {
			btn_submit.click();
	}
		
		public void clearemail()
		{
			emailID.clear();
		}
		
		public void clearpassword()
		{
			password.clear();
		}
}
