package LRS2_0;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LRSAdmin
{
	public static WebDriver driver;
	public static Reader read;

	LRSAdmin()
	{
		driver = runTests.driver;
		read = runTests.read;
	}

	public void login()
	{
		Reporter.log("Logging in as Admin User: " + read.getAdminUsername()
						+ " on " + read.getBrowser() + " on "
						+ read.getPlatform() + "<br>");

		driver.get(read.getURL());

		// helperFunctions.waitForElementToExist( By.id("btnLoginSubmit"),
		// read.getlongWaitTime(), true);
		helperFunctions.waitForLoginPage();

		driver.findElement(By.cssSelector("*[id='j_username']"))
				.sendKeys(read.getAdminUsername());
		driver.findElement(By.id("j_password")).sendKeys(read.getAdminPass());
		driver.findElement(By.id("btnLoginSubmit")).click();

		// uncomment this later after GUI stable
		helperFunctions.waitForElementToExist(	By.xpath("//*[contains(text(),'You own this account')]"),
												read.getlongWaitTime(), true);
	}

}
