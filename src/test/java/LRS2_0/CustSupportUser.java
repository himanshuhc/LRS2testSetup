package LRS2_0;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class CustSupportUser
{
	public static WebDriver driver;
	public static Reader read;

	CustSupportUser()
	{
		driver = runTests.driver;
		read = runTests.read;
	}

	public void login()
	{
		Reporter.log("Logging in as Customer Support User: "
						+ read.getCsuUserName() + " on " + read.getBrowser()
						+ " on " + read.getPlatform() + "<br>");
		driver.get(read.getURL());

		// helperFunctions.waitForElementToExist( By.id("btnLoginSubmit"),
		// read.getlongWaitTime(), true);
		helperFunctions.waitForLoginPage();
		helperFunctions.typeText(	By.cssSelector("*[id='j_username']"),
									read.getCsuUserName());
		helperFunctions.typeText(By.id("j_password"), read.getCsuPassword());

		helperFunctions.click(driver.findElement(By.id("btnLoginSubmit")));
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.waitForElementToBeVisible(	By.xpath("//ul[@id='main-menu-right']//a[@class='dropdown-toggle']"),
													read.getmediumWaitTime(),
													true);

	}

	public void checkTopMenu()
	{
		String cssLeftMainMenu = "ul#main-menu-left";
		String cssRightMainMenu = "ul#main-menu-right";
		String dropDownMenu = "ul.dropdown-menu";

		String leftMenuItemTexts = helperFunctions.getText(By.cssSelector(cssLeftMainMenu));
		String[] expectedMenuItems = { "Accounts", "Roles" };
		Assert.assertEqualsNoOrder(	leftMenuItemTexts.split("\n"),
									expectedMenuItems);

		String rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		String[] expectedRightMenuItems = {
											"Help",
											read.getCsuFirstName() + " "
													+ read.getCsuLastName() };
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		String subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		String[] expectedSubMenuItems = { "User Settings", "Sign Out" };
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);

	}

}
