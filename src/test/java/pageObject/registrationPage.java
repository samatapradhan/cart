package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends basePage{

	
	public registrationPage(WebDriver driver)
	
	{
		
		super(driver);
		
	}
	
	


@FindBy(xpath="//input[@id='firstName']")  
WebElement firstName;
@FindBy(xpath="//input[@id='lastName']")  
WebElement lastName;

@FindBy(xpath="//input[@id='userEmail']")  
WebElement email;
@FindBy(xpath="//input[@id='userMobile']") 
 WebElement enterYourNumber;
@FindBy(xpath="//select[@class='custom-select ng-untouched ng-pristine ng-valid']")  
WebElement chooseYourOccupationDoctorSt;
@FindBy(xpath="//input[@value='Female']") 
private WebElement female;
@FindBy(xpath="//input[@id='userPassword']")  
WebElement passsword;
@FindBy(xpath="//input[@id='confirmPassword']")   
WebElement confirmPassword;

@FindBy(xpath="//input[@type='checkbox']")  
WebElement chk_box_iAm18Year;

@FindBy(xpath="//input[@type='submit']")
WebElement btn_submit;

@FindBy(xpath="//h1[normalize-space()='Account Created Successfully']")  
WebElement accountCreatedSuccessfully;

public void set_firstname(String fname)
{
	firstName.sendKeys(fname);
}

public void set_lastname(String lname)
{
	lastName.sendKeys(lname);
}
public void setEmail(String emaill)
{
	email.sendKeys(emaill);
}
public void setphone(String phone)
{
	enterYourNumber.sendKeys(phone);
}

public void set_Gender()
{
	female.click();
}

public void set_Password(String pass)
{
	passsword.sendKeys(pass);
}

public void set_ConfirmPassword(String confpass)
{
	confirmPassword.sendKeys(confpass);
}

public void selectAgeCheckbox()
{
	chk_box_iAm18Year.click();
}

public void submit()
{
	btn_submit.click();
}

public String getConfirmationMsg() {
	try
	{
		return (accountCreatedSuccessfully.getText());
	}
	catch (Exception e)
	{
		return (e.getMessage());
	}
	
}
}
