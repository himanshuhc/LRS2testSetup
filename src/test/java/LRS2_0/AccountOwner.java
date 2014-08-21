package LRS2_0;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class AccountOwner
{
	public static WebDriver driver;
	public static Reader read;

	AccountOwner()
	{
		driver = runTests.driver;
		read = runTests.read;
	}

	public void login()
	{
		Reporter.log("Logging in as Account Owner User: "
						+ read.getAccOwner_userName() + " on "
						+ read.getBrowser() + " on " + read.getPlatform()
						+ "<br>");
		driver.get(read.getURL());

		// helperFunctions.waitForElementToExist( By.id("btnLoginSubmit"),
		// read.getlongWaitTime(), true);
		helperFunctions.waitForLoginPage();
		helperFunctions.typeText(	By.cssSelector("*[id='j_username']"),
									read.getAccOwner_userName());
		helperFunctions.typeText(	By.id("j_password"),
									read.getAccOwner_Password());

		helperFunctions.click(driver.findElement(By.id("btnLoginSubmit")));

		helperFunctions.waitForElementToBeVisible(	By.xpath("//ul[@id='main-menu-right']//a[@class='dropdown-toggle']"),
													read.getmediumWaitTime(),
													true);

		// uncomment this later after GUI stable
		// helperFunctions.waitForElementToBeVisible(
		// By.xpath("//*[contains(text(),'You own this account')]"),
		// read.getlongWaitTime(),
		// true);

	}

}
