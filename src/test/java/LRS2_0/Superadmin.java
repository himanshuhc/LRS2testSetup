package LRS2_0;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class Superadmin extends Reader
{
	public static WebDriver driver;
	public static Reader read;

	// public int incrId = 0;
	// public SequenceNum S_Num = new SequenceNum();

	public Superadmin()
	{
		driver = runTests.driver;
		read = runTests.read;
	}

	public void login()
	{
		Reporter.log("Logging in as Superadmin User: "
						+ read.getSuperAdminUserName() + " on "
						+ read.getBrowser() + " on " + read.getPlatform()
						+ "<br>");
		String url = read.getURL();

		driver.get(url);

		// helperFunctions.waitForElementToExist( By.id("btnLoginSubmit"),
		// read.getlongWaitTime(), true);
		helperFunctions.waitForLoginPage();
		helperFunctions.typeText(	By.cssSelector("*[id='j_username']"),
									read.getSuperAdminUserName());
		helperFunctions.typeText(	By.id("j_password"),
									read.getSuperAdminPassword());

		helperFunctions.click(driver.findElement(By.id("btnLoginSubmit")));
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.waitForElementToBeVisible(	By.xpath("//ul[@id='main-menu-right']//a[@class='dropdown-toggle']"),
													read.getmediumWaitTime(),
													true);

	}

	public void signOut()
	{

		helperFunctions.click(By.xpath("//ul[@id='main-menu-right']//a[@class='dropdown-toggle']"));
		helperFunctions.click(By.xpath("//ul[@class='dropdown-menu']//li/a[contains(text(),' Out')]"));
		Reporter.log("Signed Out" + "<br>");

	}

	public void scrollToBottom()
	{
		helperFunctions.scrollToBottom();
	}

	public void checkTopMenu()
	{
		String cssLeftMainMenu = "ul#main-menu-left";
		String cssRightMainMenu = "ul#main-menu-right";
		String dropDownMenu = "ul.dropdown-menu";

		// String leftMenuItemTexts =
		// helperFunctions.getText(By.cssSelector(cssLeftMainMenu));

		String[] menuText = fetchSuperadminMenuItems(cssLeftMainMenu);
		String[] expectedMenuItems = { "Accounts", "Customer Support Users",
										"Roles" };
		Assert.assertEqualsNoOrder(menuText, expectedMenuItems);

		menuText = fetchSuperadminMenuItems(cssRightMainMenu);
		// String rightMenuItemTexts =
		// helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		String[] expectedRightMenuItems = { "Help", "LRS Super User" };
		Assert.assertEqualsNoOrder(menuText, expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		menuText = fetchSuperadminMenuItems(dropDownMenu);
		// String subMenuItemTexts =
		// helperFunctions.getText(By.cssSelector(dropDownMenu));
		String[] expectedSubMenuItems = { "User Settings", "Sign Out" };
		Assert.assertEqualsNoOrder(menuText, expectedSubMenuItems);

	}

	public String[] fetchSuperadminMenuItems(String cssLocator)
	{
		List<WebElement> nestedLIs = driver.findElements(By.cssSelector(cssLocator
																		+ ">li"));

		ArrayList<String> menuText = new ArrayList<String>();
		for (int i = 0; i < nestedLIs.size(); i++)
		{
			WebElement nestedA = nestedLIs.get(i).findElement(By.tagName("a"));
			menuText.add(helperFunctions.getAttributeValue(nestedA,
															"textContent")
										.trim());
		}
		String[] menuTexts = menuText.toArray(new String[menuText.size()]);
		return menuTexts;
	}

	public void checkAccountList()
	{
		Assert.assertTrue(	helperFunctions.waitForElementToBeVisible(	By.xpath("//li[(@id='lrsadminAccountsItem') "
																					+ "and (@class='activeTab')]"),
																		read.getshortWaitTime(),
																		true),
							"'Accounts' menu not selected by default in the top menu");
		helperFunctions.checkListIsAlphabeticalAndFirstItemSelected();

	}
}
