package baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass{
public static WebDriver driver;
public Properties p;

public Logger logger;
	
	@BeforeClass(groups={"smoke","main","Master"})
	@Parameters({"browser","OS"})
	public void setup(String br, String os) throws IOException{
	
		FileInputStream fis=new FileInputStream(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(fis);
		
		logger=LogManager.getLogger(this.getClass());  // log4j2.xml file info provided here 
		
		
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities cap=new DesiredCapabilities();
		/*cap.setPlatform(Platform.MAC);
		cap.setBrowserName("chrome");*/
		
		if(os.equalsIgnoreCase("windows"))
		{
			cap.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			cap.setPlatform(Platform.MAC);
		}
		else if(os.equalsIgnoreCase("linux"))
		{
			cap.setPlatform(Platform.LINUX);
		}
		else 
		{
			System.out.println("No Matching OS");
			return;
		}
		
		if(br.equalsIgnoreCase("chrome"))
		{
			cap.setBrowserName("chrome");
		}
		else if(br.equalsIgnoreCase("edge"))
		{
			cap.setBrowserName("MicrosoftEdge");
		}
		else {
			System.out.println("Invalid Browser .....");
			return;
		}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444"),cap);
	}
	else 
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))

	{ 
		switch (br)
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid value .....");
			return;
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	}
	//amazing_almeida
	
	@AfterClass(groups={"smoke","main","Master"})
	public void testDown() {
		
		driver.close();
	}
	
	public String captureScreenshot(String tname) throws IOException
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy.dd.hh.mm.ss");
		Date dt=new Date();
		String timestamp=df.format(dt);
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Source=ts.getScreenshotAs(OutputType.FILE);
		
		String Filename = tname +"_"+timestamp+".png";
		
		String TargetFilePath=System.getProperty("user.dir")+ "//screenshots//"+Filename; 
		
		File TargetFile=new File(TargetFilePath);
		
		FileHandler.copy(Source,TargetFile);
		
		return TargetFilePath;
	}
		
}
