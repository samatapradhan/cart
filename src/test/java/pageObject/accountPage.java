package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountPage extends basePage {
	
public accountPage(WebDriver driver)
	
	{
		
		super(driver);
		
	}

@FindBy(xpath="//button[normalize-space()='HOME']")  
WebElement home;
	
@FindBy(xpath="//button[normalize-space()='Sign Out']")  
WebElement signOut;


public boolean isHomePageDisplayed()
{
	
	try {
		return home.isDisplayed();		
	}
	catch(Exception e)
	{
		return false;
	}
	
}

public void clickSignOut()
{
	signOut.click();
}

}
