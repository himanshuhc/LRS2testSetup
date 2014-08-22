package LRS2_0;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class runTests
{

	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	public static WebDriver driver = null;
	public static Reader read;
	Superadmin superadmin = null;
	LRSAdmin admin = null;
	AccountOwner accOwner = null;
	CustSupportUser csu = null;
	helperFunctions helpers = null;
	String platform = null;
	String browserName = null;

	@BeforeSuite
	public void initSuite()
	{
		// Reporter.log("inside @BeforeSuite" + "<br>");
		read = new Reader();
		read.logincredentials();
		read.timeouts();

		platform = read.getPlatform();
		browserName = read.getBrowser();

		// Reporter.log("Platform Used: " + platform + "<br>");
		// Reporter.log("Browser Used: " + browserName + "<br>");

		System.out.println("Platform Used: " + platform);
		System.out.println("Browser Used: " + browserName + "\n");

		if (platform.equals("Windows"))
		{
			if (browserName.equals("Firefox"))
				driver = new FirefoxDriver();
			else if (browserName.equals("Chrome"))
			{
				// File file = new File("driversWindows/chromedriver.exe");
				// System.setProperty( "webdriver.chrome.driver",
				// file.getAbsolutePath());

				// DesiredCapabilities capabilities =
				// DesiredCapabilities.chrome();
				// DesiredCapabilities.chrome();
				// ChromeOptions options = new ChromeOptions();
				// options.addArguments("test-type");
				// capabilities.setCapability(ChromeOptions.CAPABILITY,
				// options);
				// driver = new ChromeDriver(capabilities);

				/************
				 * Grid setup
				 */

				File file = new File("driversWindows/chromedriver.exe");
				System.setProperty(	"webdriver.chrome.driver",
									file.getAbsolutePath());

				threadDriver = new ThreadLocal<RemoteWebDriver>();

				DesiredCapabilities dc = DesiredCapabilities.chrome();
				// ChromeOptions options = new ChromeOptions();
				// options.addArguments("test-type");
				// dc.setCapability(ChromeOptions.CAPABILITY, options);
				// dc.setPlatform(Platform.WINDOWS);
				// dc.setBrowserName("chrome");

				dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
				dc.setPlatform(DesiredCapabilities.chrome().getPlatform());
				dc.setVersion(DesiredCapabilities.chrome().getVersion());
				try
				{
					threadDriver.set(new RemoteWebDriver(
															new URL(
																	"http://127.0.0.1:4444/wd/hub"),
															dc));
				}
				catch (MalformedURLException e)
				{
					e.printStackTrace();
				}

				driver = threadDriver.get();
				/*****
				 * Setup done
				 */

			}
			else
			// IE
			{
				// File file = new File("driversWindows/IEDriverServer_32.exe");
				// System.setProperty( "webdriver.ie.driver",
				// file.getAbsolutePath());
				//
				// DesiredCapabilities caps =
				// DesiredCapabilities.internetExplorer();
				// caps.setCapability(
				// InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				// true);
				// driver = new InternetExplorerDriver(caps);

				/************
				 * Grid setup
				 */

				File file = new File("driversWindows/IEDriverServer_32.exe");
				System.setProperty(	"webdriver.ie.driver",
									file.getAbsolutePath());

				threadDriver = new ThreadLocal<RemoteWebDriver>();

				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				// ChromeOptions options = new ChromeOptions();
				// options.addArguments("test-type");
				// dc.setCapability(ChromeOptions.CAPABILITY, options);
				// dc.setPlatform(Platform.WINDOWS);
				// dc.setBrowserName("chrome");

				dc.setBrowserName(DesiredCapabilities.internetExplorer()
														.getBrowserName());
				dc.setPlatform(DesiredCapabilities.internetExplorer()
													.getPlatform());
				dc.setVersion(DesiredCapabilities.internetExplorer()
													.getVersion());
				try
				{
					threadDriver.set(new RemoteWebDriver(
															new URL(
																	"http://127.0.0.1:4444/wd/hub"),
															dc));
				}
				catch (MalformedURLException e)
				{
					System.out.println("Exception caught here in IE catch!");
					e.printStackTrace();
				}

				driver = threadDriver.get();
				/*****
				 * Setup done
				 */
			}
		}
		else
		// Mac
		{
			final JavascriptExecutor javascriptMax = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
					: null);

			if (browserName.equals("Chrome"))
			{
				File file = new File("driversMac/chromedriver");
				System.setProperty(	"webdriver.chrome.driver",
									file.getAbsolutePath());

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(	"chrome.switches",
											Arrays.asList("--start-maximized"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);

				// maximizing
				// javascriptMax.executeScript("window.open('','mactestwindow',"
				// +
				// "'width=window.screen.availWidth,height=window.screen.availHeight,top=0,left=0')");
				// driver.close();
				// driver.switchTo().window("mactestwindow");

			}
			else
			{
				// SafariOptions capa = new SafariOptions();
				// capa.setSkipExtensionInstallation(true);
				// driver = new SafariDriver(capa);

				// DesiredCapabilities dc = DesiredCapabilities.safari();
				// SafariOptions safariOptions = new SafariOptions();
				// safariOptions.setUseCleanSession(true);
				// dc.setCapability(SafariOptions.CAPABILITY, safariOptions);
				// dc.setPlatform(Platform.MAC);
				// driver = new SafariDriver(dc);
				//

				// safari
				driver = new SafariDriver();

				// javascriptMax
				// .executeScript("window.resizeTo('width=window.screen.availWidth,"
				// + "height=window.screen.availHeight,top=0,left=0')");
			}

		}
		System.out.println("..1");
		driver.manage().window().maximize();
		System.out.println("..2");
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		System.out.println("..3");

		superadmin = new Superadmin();
		admin = new LRSAdmin();
		accOwner = new AccountOwner();
		csu = new CustSupportUser();
		helpers = new helperFunctions();
	}

	@BeforeMethod
	public void initTest()
	{
		// Reporter.log("inside @BeforeMethod" + "<br>");
		//
		// read = new Reader();
		// read.logincredentials();
		// read.timeouts();
		//
		// String platform = read.getPlatform();
		// String browserName = read.getBrowser();
		//
		// Reporter.log("Platform Used: " + platform + "<br>");
		// Reporter.log("Browser Used: " + browserName + "<br>");
		//
		// if (platform.equals("Windows"))
		// {
		// if (browserName.equals("Firefox"))
		// driver = new FirefoxDriver();
		// else if (browserName.equals("Chrome"))
		// {
		// File file = new File("driversWindows/chromedriver.exe");
		// System.setProperty( "webdriver.chrome.driver",
		// file.getAbsolutePath());
		//
		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("test-type");
		// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// driver = new ChromeDriver(capabilities);
		// }
		// else
		// // IE
		// {
		// File file = new File("driversWindows/IEDriverServer_32.exe");
		// System.setProperty( "webdriver.ie.driver",
		// file.getAbsolutePath());
		//
		// DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		// caps.setCapability(
		// InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		// true);
		// driver = new InternetExplorerDriver(caps);
		// }
		// }
		// else
		// // Mac
		// {
		// final JavascriptExecutor javascriptMax = (JavascriptExecutor) (driver
		// instanceof JavascriptExecutor ? driver
		// : null);
		//
		// if (browserName.equals("Chrome"))
		// {
		// File file = new File("driversMac/chromedriver");
		// System.setProperty( "webdriver.chrome.driver",
		// file.getAbsolutePath());
		//
		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// capabilities.setCapability( "chrome.switches",
		// Arrays.asList("--start-maximized"));
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("test-type");
		// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// driver = new ChromeDriver(capabilities);
		//
		// // maximizing
		// javascriptMax.executeScript("window.open('','mactestwindow',"
		// +
		// "'width=window.screen.availWidth,height=window.screen.availHeight,top=0,left=0')");
		// driver.close();
		// driver.switchTo().window("mactestwindow");
		//
		// }
		// else
		// {
		// // SafariOptions capa = new SafariOptions();
		// // capa.setSkipExtensionInstallation(true);
		// // driver = new SafariDriver(capa);
		//
		// DesiredCapabilities dc = DesiredCapabilities.safari();
		// SafariOptions safariOptions = new SafariOptions();
		// safariOptions.setUseCleanSession(true);
		// dc.setCapability(SafariOptions.CAPABILITY, safariOptions);
		// // dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
		// // UnexpectedAlertBehaviour.IGNORE);
		// dc.setPlatform(Platform.MAC);
		// driver = new SafariDriver(dc);
		//
		// // safari
		// // driver = new SafariDriver();
		//
		// // javascriptMax
		// // .executeScript("window.resizeTo('width=window.screen.availWidth,"
		// // + "height=window.screen.availHeight,top=0,left=0')");
		// }
		//
		// }
		//
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
		//
		// superadmin = new Superadmin();
		// admin = new LRSAdmin();
		// accOwner = new AccountOwner();
		// csu = new CustSupportUser();
		// helpers = new helperFunctions();
		// Reporter.log("---------------------------------------------------------------------------------"
		// + "<br>");
	}

	/*
	 * To test 'Page Manager Name', 'Phone number' and 'Status' are mandatory
	 * while adding 'Page Manager'
	 */
	@Test
	public void WEB_1026__WEB_1038__WEB_1807()
	{
		System.out.print("WEB-1026, WEB-1038, WEB-1807" + "\n");
		Reporter.log("WEB-1026, WEB-1038, WEB-1807" + "<br>");
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//a[text()='Surveys']"),
																read.getmediumWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//a[text()='Notifications']"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//a[text()='Devices']"),
																read.getveryShortWaitTime(),
																true));

		helperFunctions.addManagerWithoutPopulatingValues();

		// WEB-1038
		/*
		 * To test periods get automatically added while entering 'Phone Number'
		 * while adding 'Page Manager' restricting user to enter only 10 digits
		 * with format xxx.xxx.xxxx.
		 */
		// WEB-1807
		/*
		 * To test 'Account Owner' user can add 'Page Manger' to a location for
		 * 'Check Point' account
		 */
		Reporter.log("...testing WEB-1038, WEB-1807" + "<br>");
		helperFunctions.fillFieldsInPageManagerWindowAndValidatePhoneSyntaxAndSave();

	}

	/*
	 * To test that logged in user should able to add location to the specific
	 * product
	 */
	/*
	 * WEB-1276
	 * 
	 * To test on clicking 'Delete' user should be able to delete location.
	 */
	@Test
	public void WEB_1257__WEB_1276()
	{
		System.out.print("WEB-1257, WEB-1276" + "\n");
		Reporter.log("WEB-1257, WEB-1276" + "<br>");
		String locName = "";
		admin.login();
		helperFunctions.clickOnProductOnMainScreen("On Cue");
		// add location
		locName = helperFunctions.addLocation();
		helperFunctions.deleteLocation(locName);

		helperFunctions.switchProductThroughTopMenu("Check Point");
		locName = helperFunctions.addLocation();
		helperFunctions.deleteLocation(locName);

		helperFunctions.switchProductThroughTopMenu("Table Tracker");
		locName = helperFunctions.addLocation();
		helperFunctions.deleteLocation(locName);
	}

	/*
	 * To test that Admin user should be able to see all products assigned to
	 * him on "My Products" page after successful login
	 */
	@Test
	public void WEB_1259()
	{
		System.out.print("WEB-1259" + "\n");
		Reporter.log("WEB-1259" + "<br>");
		admin.login();

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[@class='myProductsContentDiv']//span[contains(text(),'"
																			+ read.getAdminCompanyName()
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("On Cue"));
		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("Check Point"));
		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("Table Tracker"));
	}

	/*
	 * To test that Manager/Reporting user should be able to see all products
	 * assigned to him on "My Products" page after successful login
	 */
	@Test
	public void WEB_1260__WEB_1267()
	{
		/*
		 * Assuming this user has all the 3 products
		 */
		System.out.print("WEB-1260, WEB-1267" + "\n");
		Reporter.log("WEB-1260, WEB-1267" + "<br>");
		helperFunctions.login("user1");
		// admin.login();
		// uncomment this later after GUI is stable
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[contains(text(),'You own this account')]"),
																read.getlongWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[@class='myProductsContentDiv']//span[contains(text(),'"
																			+ read.getGeneralUserCompanyName("user1")
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("On Cue"));
		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("Check Point"));
		Assert.assertTrue(helperFunctions.verifyProductExistsOnMainScreen("Table Tracker"));

		// WEB-1267
		/*
		 * To test that User should see Home page with proper product dropdown
		 * after successful selection of product on "My Products" page
		 */
		Reporter.log("...testing WEB-1267" + "<br>");
		helperFunctions.clickOnProductOnMainScreen("On Cue");

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "On Cue"
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Table Tracker"
																			+ "')]"),
																read.getmediumWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Check Point"
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Explore LRS Products"
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Go to My Products"
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

	}

	/*
	 * To test that 'Users' tab should only be visible to 'Account
	 * Owner'/'Manger'
	 */
	@Test
	public void WEB_1262()
	{

		System.out.print("WEB-1262" + "\n");
		Reporter.log("WEB-1262" + "<br>");
		// using Account Owner
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("On Cue");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));

		helperFunctions.switchProductThroughTopMenu("Check Point");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));

		helperFunctions.switchProductThroughTopMenu("Table Tracker");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));
		helperFunctions.signOut();

		// using Manager
		helperFunctions.login("user1");
		helperFunctions.clickOnProductOnMainScreen("On Cue");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));

		helperFunctions.switchProductThroughTopMenu("Check Point");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));

		helperFunctions.switchProductThroughTopMenu("Table Tracker");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a#users"),
																read.getmediumWaitTime(),
																true));
		helperFunctions.signOut();

		// using Reporting user
		helperFunctions.login("user2");
		helperFunctions.clickOnProductOnMainScreen("On Cue");
		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.cssSelector("a#users"),
																	read.getveryShortWaitTime(),
																	true));

		helperFunctions.switchProductThroughTopMenu("Check Point");
		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.cssSelector("a#users"),
																	read.getveryShortWaitTime(),
																	true));

		helperFunctions.switchProductThroughTopMenu("Table Tracker");
		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.cssSelector("a#users"),
																	read.getveryShortWaitTime(),
																	true));

	}

	/*
	 * To test that User should see Home page with various details after
	 * successful login if he has access to only one product
	 */
	@Test
	public void WEB_1263()
	{
		System.out.print("WEB-1263" + "\n");
		Reporter.log("WEB-1263" + "<br>");
		// using account with access to single product (Table Tracker)
		helperFunctions.login("user3");

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Table Tracker"
																			+ "')]"),
																read.getmediumWaitTime(),
																true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																			+ "Explore LRS Products"
																			+ "')]"),
																read.getveryShortWaitTime(),
																true));

		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																				+ "On Cue"
																				+ "')]"),
																	read.getveryShortWaitTime(),
																	true));

		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
																				+ "Check Point"
																				+ "')]"),
																	read.getveryShortWaitTime(),
																	true));

		helperFunctions.switchProductThroughTopMenu("Explore LRS Products");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[(contains(@class,'mdDetailHeaderLabel')) and "
																			+ "(contains(text(),'Explore Our Products'))]"),
																read.getmediumWaitTime(),
																true));

	}

	/*
	 * To test that logged in user should able to add/delete user for the
	 * specific product
	 */
	@Test
	public void WEB_1264__WEB_1277()
	{
		System.out.print("WEB-1264, WEB-1277" + "\n");
		Reporter.log("WEB-1264, WEB-1277" + "<br>");
		admin.login();

		helperFunctions.clickOnProductOnMainScreen("Table Tracker");
		helperFunctions.addAndDeleteUser();

		helperFunctions.switchProductThroughTopMenu("Check Point");
		helperFunctions.addAndDeleteUser();

		helperFunctions.switchProductThroughTopMenu("On Cue");
		helperFunctions.addAndDeleteUser();

	}

	/*
	 * To test that logged in user should be able to edit user's permissions of
	 * specific location for specific product
	 */
	@Test
	public void WEB_1273()
	{
		System.out.print("WEB-1273" + "\n");
		Reporter.log("WEB-1273" + "<br>");
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("Table Tracker");
		helperFunctions.editUserPermissionsForLocation();
	}

	/*
	 * To test menu bar when login LRSConnect using "Superadmin"
	 */
	@Test
	public void WEB_1758()
	{
		System.out.print("WEB-1758" + "\n");
		Reporter.log("WEB-1758" + "<br>");
		superadmin.login();
		superadmin.checkTopMenu();
	}

	/*
	 * To test menu bar when login LRSConnect using "Customer support user"
	 */
	@Test
	public void WEB_1759()
	{
		System.out.print("WEB-1759" + "\n");
		Reporter.log("WEB-1759" + "<br>");
		csu.login();
		csu.checkTopMenu();
	}

	/*
	 * To test menu bar when login LRSConnect using "Account owner"
	 */
	@Test
	public void WEB_1760()
	{
		System.out.print("WEB-1760" + "\n");
		Reporter.log("WEB-1760" + "<br>");
		accOwner.login();
		helperFunctions.checkAccountOwnerAndManagerMenus(	read.getAccountOwner_CompanyName(),
															read.getAccountOwner_lastName());
	}

	/*
	 * To test menu bar when login LRSConnect using Account having role Manager
	 */
	@Test
	public void WEB_1762()
	{
		System.out.print("WEB-1762" + "\n");
		Reporter.log("WEB-1762" + "<br>");
		helperFunctions.login("user1");
		helperFunctions.checkAccountOwnerAndManagerMenus(	read.getGeneralUserCompanyName("user1"),
															read.getGeneralUserLastName("user1"));
	}

	/*
	 * To test menu bar when login LRSConnect using Account having role
	 * Reporting
	 */
	@Test
	public void WEB_1764()
	{
		System.out.print("WEB-1764" + "\n");
		Reporter.log("WEB-1764" + "<br>");
		helperFunctions.login("user2");
		helperFunctions.checkReportingUserMenus(read.getGeneralUserCompanyName("user2"),
												read.getGeneralUserLastName("user2"));
	}

	/*
	 * To test Superadmin or customer support user should be able to see the
	 * account list
	 */
	@Test
	public void WEB_1775()
	{
		// !!!! NOT CHECKING IF LIST IS ALPHABETICAL AS THAT IS NOT IMPLEMENTED
		// CORRECTLY eg. 01Acc should be before 01_Acc, not after

		System.out.print("WEB-1775" + "\n");
		Reporter.log("WEB-1775" + "<br>");
		superadmin.login();
		superadmin.checkAccountList();

		helperFunctions.signOut();
		csu.login();
		superadmin.checkAccountList();
	}

	/*
	 * To test 'Superadmin' can create survey in a location for selected account
	 */
	@Test
	public void WEB_1824()
	{
		System.out.print("WEB-1824" + "\n");
		Reporter.log("WEB-1824" + "<br>");
		superadmin.login();
		helperFunctions.searchForItemInLeftPane(read.getAccountOwner_CompanyName());
		helperFunctions.click(By.cssSelector("button.btn.loginAsAccountAdminBtn"));
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		helperFunctions.addSurvey();
	}

	/*
	 * To test user with 'manager' role can create survey in a selected location
	 */
	@Test
	public void WEB_1827__WEB_1828__WEB_1955__WEB_1953__WEB_1954__WEB_1984__WEB_1964__WEB_1968()
	{
		System.out.print("WEB-1827, WEB-1828, WEB-1955, WEB-1953, WEB-1954, WEB-1984, WEB-1964, WEB-1968"
							+ "\n");
		Reporter.log("WEB-1827, WEB-1828, WEB-1955, WEB-1953, WEB-1954, WEB-1984, WEB-1964, WEB-1968"
						+ "<br>");
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		String surveyAdded = helperFunctions.addSurvey();

		// WEB-1828
		/*
		 * To test user with 'manager' role can edit existing survey in a
		 * selected location
		 */
		Reporter.log("...testing WEB-1828" + "<br>");
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));
		String editedSurveyName = helperFunctions.editSurveyName(surveyAdded);

		// WEB-1955
		/*
		 * To test Cancel button on popup while changing survey from 'Draft' to
		 * 'Active'
		 */
		Reporter.log("...testing WEB-1955" + "<br>");
		helperFunctions.changeSurveyStatus(editedSurveyName, "Draft", "Active");
		// click Cancel button on popup, and then save
		helperFunctions.click(By.cssSelector("button.btn.btn-large.modalCancelBtn"));
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveBtn"));
		String currentSurveyStatus = helperFunctions.checkSurveyStatus(editedSurveyName);
		Assert.assertEquals(currentSurveyStatus.toUpperCase(),
							"Draft".toUpperCase());

		// WEB-1953
		/*
		 * To test only 150 characters are allowed in question string
		 */

		// for testing only-
		// String editedSurveyName = "AutoSurvey7055_edited";

		Reporter.log("...testing WEB-1953" + "<br>");
		String qString = helperFunctions.checkSurveyQuesCharLimit(editedSurveyName);

		// WEB-1954, WEB-1984
		/*
		 * Test blank answers can not be added to 'Multiple Selection' Question
		 */
		/*
		 * Test only Ten answers can be added to 'Multiple Selection' Question
		 */
		Reporter.log("...testing WEB-1954, WEB-1984" + "<br>");
		// adding this ques as multiple selection
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='MULTIPLESELECTION']"))
				.click();
		By qType = By.xpath("//*[(@id='questionTypeSelectBoxItText') and (text()='Multiple Selection')]");
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(qType,
																	read.getveryShortWaitTime(),
																	true));
		// check empty answer is not allowed
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
		By byErrorAnsField = By.cssSelector("input.input-block-level.invalid.error.errorBox");
		Assert.assertTrue(helperFunctions.waitForElementToExist(byErrorAnsField,
																read.getveryShortWaitTime(),
																true));

		driver.findElement(byErrorAnsField).clear();
		// check more answers can be added
		int answerIndex = 1;
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.id("answer-"
																			+ answerIndex),
																	read.getveryShortWaitTime(),
																	true),
							"New Answer Not Added!");
		helperFunctions.typeText(	By.id("answer-" + answerIndex),
									"ans-" + answerIndex);
		for (int newAnsIndex = answerIndex + 1; newAnsIndex < 12; newAnsIndex++)
		{
			helperFunctions.click(By.cssSelector("a.addAnswer"));

			if (newAnsIndex > 10)
			{
				Assert.assertTrue(	helperFunctions.waitForElementToBeVisible(	By.xpath("//div[contains(text(),'You may only add up to 10 answers')]"),
																				read.getveryShortWaitTime(),
																				true),
									"Error message did not appear on adding 11th answer!");
				Assert.assertTrue(	!helperFunctions.waitForElementToExist(	By.id("answer-"
																					+ newAnsIndex),
																			read.getveryShortWaitTime(),
																			true),
									"11th Answer Was Added!");
			}
			else
			{
				Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.id("answer-"
																					+ newAnsIndex),
																			read.getveryShortWaitTime(),
																			true),
									"New Answer Not Added!");
				helperFunctions.typeText(	By.id("answer-" + newAnsIndex),
											"ans-" + newAnsIndex);
			}
		}
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
		// should now be visible in the left pane
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.xpath("//*[@id='mdMasterListItems']"
																				+ "//div[(@class='mdMasterTablePrimaryLabel')"
																				+ " and (contains(text(),'Question'))]"),
																	read.getshortWaitTime(),
																	true),
							"Question" + " not visible in left pane!");
		By byNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
									+ "//div[(@class='mdMasterTableSecondaryLabel')"
									+ " and (contains(text(),'"
									+ qString.substring(0, 13) + "'))]");
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	byNewQAdded,
																	read.getshortWaitTime(),
																	true),
							"Question" + " not visible in left pane!");
		// WEB-1964
		/*
		 * To test answers can be removed from 'Multiple Selection' Question
		 */
		Reporter.log("...testing WEB-1964" + "<br>");
		helperFunctions.click(byNewQAdded);
		// remove ans-3
		By byRemoveLink = By.xpath("//*[@id='answer-3']/ancestor::tr[1]//a[text()='Remove']");
		helperFunctions.click(byRemoveLink);
		helperFunctions.click(By.name("confirmButtonLabel"));
		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.id("answer-3"),
																	read.getveryShortWaitTime(),
																	true));

		for (int newAnsIndex = 1; newAnsIndex < 11; newAnsIndex++)
			if (newAnsIndex != 3)
				Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("answer-"
																				+ newAnsIndex),
																		read.getveryShortWaitTime(),
																		true));
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));

		// WEB-1968
		/*
		 * Test to check functionality of 'Cancel' button in message pop up
		 * while changing answer type.
		 */
		Reporter.log("...testing WEB-1968" + "<br>");
		helperFunctions.click(byNewQAdded);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		String expectedAlertMessage = "If you change the Answer Type your existing answers "
										+ "will be deleted and are not recoverable.";
		try
		{
			driver.findElement(By.xpath("//li[@data-val='YESNO']")).click();
			Assert.assertEquals(helperFunctions.handlePopup(false),
								expectedAlertMessage);
		}
		catch (UnhandledAlertException e)
		{
			// this happens in safari
			// see http://code.google.com/p/selenium/issues/detail?id=3862
			Reporter.log("UnhandledAlertException: Safari dismissing alert pop-up on its own."
							+ "<br>");
			Assert.assertEquals(e.getAlertText(), expectedAlertMessage);
		}
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(qType,
																	read.getveryShortWaitTime(),
																	true));
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));

		helperFunctions.deleteSurvey(surveyAdded);
	}

	/*
	 * To test branching option is available for 'YES NO' question and the drop
	 * down shows only question numbers after the present questions
	 */
	@Test
	public void WEB_1959()
	{
		System.out.print("WEB-1959" + "\n");
		Reporter.log("WEB-1959" + "<br>");
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		String surveyAdded = helperFunctions.addSurvey();

		By byAddQ = By.cssSelector("button.btn.newModelBtn");
		By byNewQ = By.id("questionText");
		By bySaveQbtn = By.cssSelector("button.btn.btn-primary.saveModelBtn");

		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		helperFunctions.typeText(	byNewQ,
									"AutoQuestion"
											+ helperFunctions.randomNum(1, 100));
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));

		// check all options are present
		String[] expectedAnsTypes = { "Select Answer Type", "Multiple Choice",
										"Multiple Selection", "Yes No",
										"5 Star Rating", "Free Form" };
		Select answerTypes = new Select(
										driver.findElement(By.id("questionType")));
		helperFunctions.compareLists(	expectedAnsTypes, answerTypes,
										"textContent");

		driver.findElement(By.xpath("//li[@data-val='LONGFORM']")).click();
		helperFunctions.click(bySaveQbtn);

		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		String yesnoQString = "AutoQuestion"
								+ helperFunctions.randomNum(1, 100);
		helperFunctions.typeText(byNewQ, yesnoQString);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='YESNO']")).click();
		By qType = By.xpath("//*[(@id='questionTypeSelectBoxItText') and (text()='Yes No')]");
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(qType,
																	read.getveryShortWaitTime(),
																	true));
		helperFunctions.click(bySaveQbtn);

		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		helperFunctions.typeText(	byNewQ,
									"AutoQuestion"
											+ helperFunctions.randomNum(1, 100));
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='LONGFORM']")).click();
		helperFunctions.click(bySaveQbtn);

		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		helperFunctions.typeText(	byNewQ,
									"AutoQuestion"
											+ helperFunctions.randomNum(1, 100));
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='LONGFORM']")).click();
		helperFunctions.click(bySaveQbtn);

		// now click on question2 (YES/NO) and see if branching to subsequent
		// questions has appeared
		By byNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
									+ "//div[(@class='mdMasterTableSecondaryLabel')"
									+ " and (contains(text(),'" + yesnoQString
									+ "'))]");
		helperFunctions.click(byNewQAdded);
		String[] expectedQuesBranches = { "Branch To", "Question 3",
											"Question 4" };
		helperFunctions.validateYNBranchToOptionsAgainst(	"Yes",
															expectedQuesBranches);
		helperFunctions.validateYNBranchToOptionsAgainst(	"No",
															expectedQuesBranches);

		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));
		helperFunctions.deleteSurvey(surveyAdded);

	}

	/*
	 * Test to questions in the Active survey can be removed
	 */
	@Test
	public void WEB_1963__WEB_1969__WEB_1970__WEB_1948__WEB_1990__WEB_1991()
	{
		System.out.print("WEB-1963, WEB-1969, WEB-1970, WEB-1948, WEB-1990, WEB-1991"
							+ "\n");
		Reporter.log("WEB-1963, WEB-1969, WEB-1970, WEB-1948, WEB-1990, WEB-1991"
						+ "<br>");
		// helperFunctions.login("user1");
		// accOwner.login();
		admin.login();
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		String surveyName = helperFunctions.addSurvey();

		By byAddQ = By.cssSelector("button.btn.newModelBtn");
		By byNewQ = By.id("questionText");
		By bySaveQbtn = By.cssSelector("button.btn.btn-primary.saveModelBtn");

		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		String freeformString = "AutoQuestion"
								+ helperFunctions.randomNum(1, 100);
		helperFunctions.typeText(byNewQ, freeformString);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='LONGFORM']")).click();
		helperFunctions.click(bySaveQbtn);
		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		String yesnoQString = "AutoQuestion"
								+ helperFunctions.randomNum(1, 100);
		helperFunctions.typeText(byNewQ, yesnoQString);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='YESNO']")).click();
		helperFunctions.click(bySaveQbtn);
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));

		// check survey in draft status can only be changed to Active status
		// here
		String surveyStatusXpath = "//a[(@class='surveyClass') and (contains(text(),'"
									+ surveyName
									+ "'))]/ancestor::tr[1]//select[@name='status']";
		By bySurveyStatus = By.xpath(surveyStatusXpath);
		Select initStatusSelect = new Select(driver.findElement(bySurveyStatus));
		String[] expectedInitStatuses = { "Draft", "Active" };
		helperFunctions.compareLists(	expectedInitStatuses, initStatusSelect,
										"textContent");

		// change status from draft to active
		helperFunctions.changeSurveyStatus(surveyName, "Draft", "Active");
		By byConfirmModal = By.cssSelector("button.btn.btn-large.modalConfirmBtn");
		// check message in the pop-up
		helperFunctions.waitForElementToBeVisible(	byConfirmModal,
													read.getveryShortWaitTime(),
													true);
		Assert.assertEquals(helperFunctions.getAttributeValue(	By.xpath("//span[@name='bodyText']"),
																"textContent"),
							"If survey is activated, existing questions cannot be changed, only deleted or new questions may be added.");

		helperFunctions.click(byConfirmModal);
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveBtn"));
		String currentSurveyStatus = helperFunctions.checkSurveyStatus(surveyName);
		Assert.assertEquals(currentSurveyStatus.toUpperCase(),
							"Active".toUpperCase());
		// click onsurvey
		By bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
								+ surveyName + "'))]");
		helperFunctions.checkSurveyExists(bySurvey);
		helperFunctions.click(bySurvey);
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
																				+ "(contains(text(),'Survey Settings'))]"),
																	read.getshortWaitTime(),
																	true));
		// delete question
		helperFunctions.deleteSurveyQues(yesnoQString);

		// WEB-1969
		/*
		 * Test to questions within "Active" survey cannot be changed
		 */
		Reporter.log("...testing WEB-1969" + "<br>");
		By byfreeformNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
											+ "//div[(@class='mdMasterTableSecondaryLabel')"
											+ " and (contains(text(),'"
											+ freeformString + "'))]");
		helperFunctions.click(byfreeformNewQAdded);
		Assert.assertEquals(helperFunctions.getAttributeValue(	By.id("questionText"),
																"disabled"),
							"true");
		helperFunctions.click(bySaveQbtn);
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));

		// WEB-1970
		/*
		 * Test to copy existing survey and check copied survey is in Draft mode
		 */
		Reporter.log("...testing WEB-1970" + "<br>");
		// copy existing survey
		helperFunctions.click(By.cssSelector("button.btn.addProductBtn"));
		helperFunctions.click(By.xpath("//input[@value='COPY_SURVEY']"));
		helperFunctions.click(By.id("COPY_SURVEYSelectBoxItText"));

		driver.findElement(	By.xpath("//li/a[contains(text(),' - " + surveyName
										+ "')]")).click();
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveBtn"));
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
																				+ "(contains(text(),'Survey Settings'))]"),
																	read.getshortWaitTime(),
																	true));

		Assert.assertEquals(driver.findElement(	By.cssSelector("span.selectboxit-text"))
									.getText(), "Draft",
							"Survey's initial status is not 'Draft'");
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));

		// WEB-1948
		/*
		 * To test In-Active surveys cannot be changed
		 */
		Reporter.log("...testing WEB-1948" + "<br>");
		helperFunctions.changeSurveyStatus(surveyName, "Active", "Inactive");

		By byModalConfirm = By.cssSelector("button.btn.btn-large.modalConfirmBtn");
		if (helperFunctions.waitForElementToBeVisible(	byModalConfirm,
														read.getveryShortWaitTime(),
														true))
			helperFunctions.click(byModalConfirm);
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveBtn"));
		String currentInactiveSurveyStatus = helperFunctions.checkSurveyStatus(surveyName);
		Assert.assertEquals(currentInactiveSurveyStatus.toUpperCase(),
							"Inactive".toUpperCase());
		// click on survey
		bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
							+ surveyName + "'))]");
		helperFunctions.checkSurveyExists(bySurvey);
		helperFunctions.click(bySurvey);
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
																				+ "(contains(text(),'Survey Settings'))]"),
																	read.getshortWaitTime(),
																	true));
		// add Q
		helperFunctions.click(byAddQ);
		helperFunctions.click(byNewQ);
		yesnoQString = "AutoQuestion" + helperFunctions.randomNum(1, 100);
		helperFunctions.typeText(byNewQ, yesnoQString);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='YESNO']")).click();
		By qType = By.xpath("//*[(@id='questionTypeSelectBoxItText') and (text()='Yes No')]");
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(qType,
																	read.getveryShortWaitTime(),
																	true));
		helperFunctions.click(bySaveQbtn);
		By byNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
									+ "//div[(@class='mdMasterTableSecondaryLabel')"
									+ " and (contains(text(),'" + yesnoQString
									+ "'))]");
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(byNewQAdded,
																	read.getveryShortWaitTime(),
																	true));
		// delete Q
		helperFunctions.deleteSurveyQues(yesnoQString);

		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));
		helperFunctions.deleteSurvey(surveyName);
	}

	/*
	 * Test answers can be removed from 'Multiple Choice' Question
	 */
	@Test
	public void WEB_1989__WEB_1994()
	{
		System.out.print("WEB-1989, WEB-1994" + "\n");
		Reporter.log("WEB-1989, WEB-1994" + "<br>");
		accOwner.login();
		helperFunctions.clickOnProductOnMainScreen("Check Point");
		String surveyAdded = helperFunctions.addSurvey();
		helperFunctions.click(By.cssSelector("button.btn.newModelBtn"));
		By byNewQ = By.id("questionText");
		helperFunctions.click(byNewQ);
		String qString = "MCQ" + helperFunctions.randomNum(1, 10000);
		helperFunctions.typeText(byNewQ, qString);
		helperFunctions.click(By.id("questionTypeSelectBoxItText"));
		driver.findElement(By.xpath("//li[@data-val='MULTIPLECHOICE']"))
				.click();
		By qType = By.xpath("//*[(@id='questionTypeSelectBoxItText') and (text()='Multiple Choice')]");
		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(qType,
																	read.getveryShortWaitTime(),
																	true));

		// check empty answer is not allowed
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
		By byErrorAnsField = By.cssSelector("input.input-block-level.invalid.error.errorBox");
		Assert.assertTrue(helperFunctions.waitForElementToExist(byErrorAnsField,
																read.getveryShortWaitTime(),
																true));

		driver.findElement(byErrorAnsField).clear();

		// check 10 answers can be added
		int answerIndex = 1;
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.id("answer-"
																			+ answerIndex),
																	read.getveryShortWaitTime(),
																	true),
							"New Answer Not Added!");
		helperFunctions.typeText(	By.id("answer-" + answerIndex),
									"ans-" + answerIndex);
		for (int newAnsIndex = answerIndex + 1; newAnsIndex < 12; newAnsIndex++)
		{
			helperFunctions.click(By.cssSelector("a.addAnswer"));

			if (newAnsIndex > 10)
			{
				Assert.assertTrue(	helperFunctions.waitForElementToBeVisible(	By.xpath("//div[contains(text(),'You may only add up to 10 answers')]"),
																				read.getveryShortWaitTime(),
																				true),
									"Error message did not appear on adding 11th answer!");
				Assert.assertTrue(	!helperFunctions.waitForElementToExist(	By.id("answer-"
																					+ newAnsIndex),
																			read.getveryShortWaitTime(),
																			true),
									"11th Answer Was Added!");
			}
			else
			{
				Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.id("answer-"
																					+ newAnsIndex),
																			read.getveryShortWaitTime(),
																			true),
									"New Answer Not Added!");
				helperFunctions.typeText(	By.id("answer-" + newAnsIndex),
											"ans-" + newAnsIndex);
			}
		}
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
		// should now be visible in the left pane
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	By.xpath("//*[@id='mdMasterListItems']"
																				+ "//div[(@class='mdMasterTablePrimaryLabel')"
																				+ " and (contains(text(),'Question'))]"),
																	read.getshortWaitTime(),
																	true),
							"Question" + " not visible in left pane!");
		By byNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
									+ "//div[(@class='mdMasterTableSecondaryLabel')"
									+ " and (contains(text(),'" + qString
									+ "'))]");
		Assert.assertTrue(	helperFunctions.waitForElementToExist(	byNewQAdded,
																	read.getshortWaitTime(),
																	true),
							"Question" + " not visible in left pane!");

		// check answer can be removed
		helperFunctions.click(byNewQAdded);
		By byRemoveLink = By.xpath("//*[@id='answer-3']/ancestor::tr[1]//a[text()='Remove']");
		helperFunctions.click(byRemoveLink);
		helperFunctions.click(By.name("confirmButtonLabel"));
		Assert.assertTrue(!helperFunctions.waitForElementToExist(	By.id("answer-3"),
																	read.getveryShortWaitTime(),
																	true));

		for (int newAnsIndex = 1; newAnsIndex < 11; newAnsIndex++)
			if (newAnsIndex != 3)
				Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("answer-"
																				+ newAnsIndex),
																		read.getveryShortWaitTime(),
																		true));
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));

		helperFunctions.click(By.cssSelector("button.btn.btn-primary.btnDone"));
		helperFunctions.deleteSurvey(surveyAdded);

	}

	/*
	 * WEB_1281: To test that logged in user should be able to reset password
	 */
	@Test
	public void WEB_1281()
	{
		System.out.print("WEB-1281" + "\n");
		Reporter.log("WEB-1281" + "<br>");
		admin.login();
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.verifyAllProductsInSequence();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("mdSearchWidgetIcon"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.click(By.xpath("//*[@id='mdMasterListItems']/div[1]/div"));
		helperFunctions.waitForAjaxRefresh();
		// Clicking on Reset Password
		helperFunctions.click(By.cssSelector("button.btn.changeUserPassBtn"));
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='resetConfirmDialog']"),
																read.getveryShortWaitTime(),
																true));
		String PasswordText = driver.findElement(	By.xpath("//*[@id='resetConfirmDialog']/div[2]"))
									.getText();
		Reporter.log("Text of Pop-Up" + PasswordText + "<br>");
		Assert.assertEquals(PasswordText,
							"An email has been to sent to the Account Owner with reset password instructions.");
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.click(By.xpath("//button[@data-dismiss='modal']"));
		// For Check Point
		helperFunctions.switchProductThroughTopMenu("Check Point");
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("users"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.click(By.id("users"));
		// Clicking on Reset Password
		helperFunctions.click(By.cssSelector("button.btn.changeUserPassBtn"));
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='resetConfirmDialog']"),
																read.getveryShortWaitTime(),
																true));
		String PasswordText1 = driver.findElement(	By.xpath("//*[@id='resetConfirmDialog']/div[2]"))
										.getText();
		Reporter.log("Text of Pop-Up" + PasswordText + "<br>");
		Assert.assertEquals(PasswordText1,
							"An email has been to sent to the Account Owner with reset password instructions.");
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.click(By.xpath("//button[@data-dismiss='modal']"));
		// For On Cue Restaurant
		helperFunctions.switchProductThroughTopMenu("On Cue For Restaurants");
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("users"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.click(By.id("users"));
		// Clicking on Reset Password
		helperFunctions.click(By.cssSelector("button.btn.changeUserPassBtn"));
		helperFunctions.waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='resetConfirmDialog']"),
																read.getveryShortWaitTime(),
																true));
		String PasswordText2 = driver.findElement(	By.xpath("//*[@id='resetConfirmDialog']/div[2]"))
										.getText();
		Reporter.log("Text of Pop-Up" + PasswordText + "<br>");
		Assert.assertEquals(PasswordText2,
							"An email has been to sent to the Account Owner with reset password instructions.");
		helperFunctions.waitForAjaxRefresh();
		helperFunctions.click(By.xpath("//button[@data-dismiss='modal']"));

	}

	/*
	 * WEB_1282: To test that user is able to switch between the products for
	 * which he has access
	 */
	@Test
	public void WEB_1282()
	{
		System.out.print("WEB-1282" + "\n");
		Reporter.log("WEB-1282" + "<br>");
		admin.login();
		helperFunctions.clickOnProductOnMainScreen("On Cue");
		helperFunctions.waitForAjaxRefresh();
		// Show OverView , SMS Usage, Table Management

		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.linkText("Overview"),
																	read.getshortWaitTime(),
																	true));

		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.linkText("SMS Usage"),
																	read.getveryShortWaitTime(),
																	true));

		Assert.assertTrue(helperFunctions.waitForElementToBeVisible(By.linkText("Table Management"),
																	read.getveryShortWaitTime(),
																	true));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.switchProductThroughTopMenu("Check Point");

		// Show Surveys, Notifications and Devices
		Assert.assertEquals(driver.findElement(By.xpath("//a[@data-item='1']"))
									.getText(), "Surveys");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@data-item='2']"))
									.getText(), "Notifications");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@data-item='3']"))
									.getText(), "Devices");

		helperFunctions.switchProductThroughTopMenu("Table Tracker");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertEquals(driver.findElement(	By.cssSelector("span.headerSubTitleLarge"))
									.getText(),
							driver.findElement(	By.xpath("//*[@id='mdMasterListItems']"
															+ "//div[@class='listItem selected']"
															+ "//div[@class='mdMasterTablePrimaryLabel']"))
									.getText());
		// Verify button to add New Device
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("a.addProductBtn"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.switchProductThroughTopMenu("Go to My Products");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[(@class='productName') and (contains(text(),'On Cue'))]"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[(@class='productName') and (contains(text(),'Table Tracker'))]"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[(@class='productName') and (contains(text(),'Check Point'))]"),
																read.getveryShortWaitTime(),
																true));
	}

	/*
	 * WEB_1655: To test login to LRSconnect without entering Email id and
	 * Password field
	 */
	@Test
	public void WEB_1655__WEB_1656()
	{
		System.out.print("WEB-1655, WEB-1656" + "\n");
		Reporter.log("WEB-1655, WEB-1656" + "<br>");
		driver.get(read.getURL());
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("div.loginLogo"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("j_username"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("j_password"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("forgotPassword"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("btnLoginSubmit"),
																read.getveryShortWaitTime(),
																true));
		helperFunctions.typeText(By.id("j_username"), "");
		helperFunctions.typeText(By.id("j_password"), "");
		helperFunctions.click(By.id("btnLoginSubmit"));
		helperFunctions.waitForAjaxRefresh();
		String email = driver.findElement(By.id("j_username_error"))
								.getAttribute("value");
		Assert.assertEquals(email, "Field Email is required");
		String password = driver.findElement(By.id("j_password_error"))
								.getAttribute("value");
		Assert.assertEquals(password, "Field Password is required");
		String loginMessage = driver.findElement(	By.cssSelector("h4.form-signin-heading"))
									.getText();
		Assert.assertEquals(loginMessage, "Login to LRS Connect");

		/*
		 * WEB - 1656: To test login to LRSconnect, Only entering one of the
		 * field either email id or "Password" or blank in both the fields
		 */

		// Passing only UserName for Login
		helperFunctions.click(By.id("j_username_error"));
		helperFunctions.typeText(By.id("j_username"), "a@lrs.com");
		helperFunctions.click(By.id("btnLoginSubmit"));
		Assert.assertEquals(password, "Field Password is required");
		Assert.assertEquals(loginMessage, "Login to LRS Connect");
		driver.findElement(By.id("j_username")).clear();
		helperFunctions.click(By.id("j_password_error"));
		helperFunctions.typeText(By.id("j_password"), "aaaaa");
		helperFunctions.click(By.id("btnLoginSubmit"));
		Assert.assertEquals(email, "Field Email is required");
		Assert.assertEquals(loginMessage, "Login to LRS Connect");
	}

	/*
	 * WEB-1287: To test that user with 'Manager' role should be able to add
	 * users for assigned locations
	 */
	@Test
	public void WEB_1287()
	{
		System.out.print("WEB-1287" + "\n");
		Reporter.log("WEB-1287" + "<br>");
		// admin.login();
		helperFunctions.login("user1");

		helperFunctions.clickOnProductOnMainScreen("Table Tracker");
		helperFunctions.addAndDeleteUser();

		helperFunctions.switchProductThroughTopMenu("Check Point");
		helperFunctions.addAndDeleteUser();

		helperFunctions.switchProductThroughTopMenu("On Cue");
		helperFunctions.addAndDeleteUser();
	}

	/*
	 * WEB-1292 :To test that while adding user without entering user email,
	 * user should not get saved
	 */
	@Test
	public void WEB_1292()
	{
		System.out.print("WEB-1292" + "\n");
		Reporter.log("WEB-1292" + "<br>");
		admin.login();

		helperFunctions.clickOnProductOnMainScreen("Table Tracker");
		helperFunctions.tryAddingUserWithBlankEmail();

		helperFunctions.switchProductThroughTopMenu("Check Point");
		helperFunctions.click(By.name("cancelLabelButton"));
		helperFunctions.tryAddingUserWithBlankEmail();

		helperFunctions.switchProductThroughTopMenu("On Cue");
		helperFunctions.click(By.name("cancelLabelButton"));
		helperFunctions.tryAddingUserWithBlankEmail();

	}

	/*
	 * WEB-1371 :To test SuperAdmin or Customer Support User should be able to
	 * create new Account #WEB-1383 : To test user for new account creation user
	 * must have at least one product selected #WEB-1390 : To test that user
	 * should allow to assign one or more products to a account
	 */
	@Test
	public void WEB_1371__WEB_1383__WEB_1390()
	{
		System.out.print("WEB-1371, WEB-1383, WEB-1390" + "\n");
		Reporter.log("WEB-1371, WEB-1383, WEB-1390" + "<br>");
		superadmin.login();
		helperFunctions.verifyAndAddUserWithProduct();
		helperFunctions.signOut();
		// Login by Customer Support User
		csu.login();
		helperFunctions.verifyAndAddUserWithProduct();

	}

	/*
	 * To test there should be only one user as a account owner/admin for one
	 * account.
	 */
	@Test
	public void WEB_1396()
	{
		System.out.print("WEB-1396" + "\n");
		Reporter.log("WEB-1396" + "<br>");
		superadmin.login();
		helperFunctions.click(By.id("lrsadminAccounts"));
		helperFunctions.click(By.id("addAccount"));

		// Entering details for creation of User Account
		helperFunctions.typeText(	By.cssSelector("input.input-block-level.textBox"),
									"AutomationName"
											+ helperFunctions.randomNum(1,
																		1000000));

		// For Email Address
		helperFunctions.typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
									"Automationmail"
											+ helperFunctions.randomNum(1,
																		100000)
											+ "@gmail.com");

		helperFunctions.click(By.cssSelector("div.selectric"));
		helperFunctions.waitForElementToExist(	By.cssSelector("div.selectricItems"),
												read.getmediumWaitTime(), true);
		// Selected Active
		helperFunctions.click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));
		// Clicking on Save button
		helperFunctions.click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));

		String TextForAccountVerify = driver.findElement(	By.xpath("//td[@class='lrsTd middle']/div/table/tbody/tr/td[1]"))
											.getText();
		// Click on Add User
		// helperFunctions.click(By.id("addEntity"));
		Reporter.log("LRS ADMIN Account" + "<br>");
		helperFunctions.JSmouseOver(By.id("lrsadminAccounts"));
		Reporter.log("LRS" + "<br>");
		helperFunctions.click(By.id("addAccount"));
		helperFunctions.typeText(	By.cssSelector("input.input-block-level.textBox"),
									"AutomationName"
											+ helperFunctions.randomNum(1,
																		1000000));
		helperFunctions.typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
									TextForAccountVerify);
		helperFunctions.click(By.cssSelector("div.selectric"));
		helperFunctions.waitForElementToExist(	By.cssSelector("div.selectricItems"),
												read.getmediumWaitTime(), true);
		// Selected Active
		helperFunctions.click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));
		// Clicking on Save tab
		helperFunctions.click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));
		String errorMessage = driver.findElement(	By.cssSelector("input.input-block-level.textBox.invalid.error.errorBox"))
									.getAttribute("value");
		Assert.assertEquals(errorMessage,
							"This user has already been added to this product");

		// LogOut
		helperFunctions.signOut();
		helperFunctions.click(By.cssSelector("button.btn.modalCancelBtn"));

		// Login from Customer Support User
		csu.login();
		helperFunctions.click(By.id("lrsadminAccounts"));
		helperFunctions.click(By.id("addAccount"));

		// Entering details for creation of User Account
		helperFunctions.typeText(	By.cssSelector("input.input-block-level.textBox"),
									"AutomationName"
											+ helperFunctions.randomNum(1,
																		1000000));

		helperFunctions.typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
									"Automationmail"
											+ helperFunctions.randomNum(1,
																		100000)
											+ "@gmail.com");

		helperFunctions.click(By.cssSelector("div.selectric"));
		helperFunctions.waitForElementToExist(	By.cssSelector("div.selectricItems"),
												read.getmediumWaitTime(), true);
		// Selected Active
		helperFunctions.click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));
		// Clicking on Save
		helperFunctions.click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));
		String TextForAccountVerify1 = driver.findElement(	By.xpath("//td[@class='lrsTd middle']/div/table/tbody/tr/td[1]"))
												.getText();
		// Click on Add User
		// helperFunctions.click(By.id("addEntity"));
		Reporter.log("LRS ADMIN Account" + "<br>");
		helperFunctions.JSmouseOver(By.id("lrsadminAccounts"));
		Reporter.log("LRS" + "<br>");
		helperFunctions.click(By.id("addAccount"));
		helperFunctions.typeText(	By.cssSelector("input.input-block-level.textBox"),
									"AutomationName"
											+ helperFunctions.randomNum(1,
																		1000000));
		helperFunctions.typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
									TextForAccountVerify1);
		helperFunctions.click(By.cssSelector("div.selectric"));
		helperFunctions.waitForElementToExist(	By.cssSelector("div.selectricItems"),
												read.getmediumWaitTime(), true);
		// Selected Active
		helperFunctions.click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));
		// Clicking on Save tab
		helperFunctions.click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));
		String errorMessage1 = driver.findElement(	By.cssSelector("input.input-block-level.textBox.invalid.error.errorBox"))
										.getAttribute("value");
		Assert.assertEquals(errorMessage1,
							"This user has already been added to this product");

	}

	/*
	 * WEB-2164 : Test to create 'Customer Support' user
	 */

	@Test
	public void WEB_2164()
	{
		System.out.print("WEB-2164" + "\n");
		Reporter.log("WEB-2164" + "<br>");
		superadmin.login();
		// Clicking on Customer Support User
		helperFunctions.click(By.id("lrsadminUsers"));
		helperFunctions.waitForAjaxRefresh();

		// helperFunctions.scrollToBottom();
		List<WebElement> allAccountsList;
		allAccountsList = driver.findElements(By.id("mdMasterListItems"));

		for (int i = 1; i <= allAccountsList.size(); i++)
		{

			helperFunctions.click(By.xpath("//*[@id='mdMasterListItems']/div["
											+ i + "]/div"));
			String UserNameHeading = driver.findElement(By.cssSelector("div.mdDetailHeaderLabel.name"))
											.getText();
			String UserNameLeftPanel = driver.findElement(	By.xpath("//*[@id='mdMasterListItems']/div["
																		+ i
																		+ "]/div/div[1]"))
												.getText();
			Assert.assertEquals(UserNameHeading, UserNameLeftPanel);
		}

		// Clicking on Customer Support Users

		helperFunctions.click(By.id("lrsadminUsers"));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("addCustomerSupportUser"),
																read.getveryShortWaitTime(),
																true));

		// Clicking on +Add User

		helperFunctions.click(By.id("addCustomerSupportUser"));
		Reporter.log("Total Number Of Users.."
						+ driver.findElement(By.cssSelector("div.pull-left"))
								.getAttribute("value") + "<br>");
		String NewUserPageText = driver.findElement(By.cssSelector("div.mdDetailHeaderLabel.name"))
										.getText();
		Assert.assertEquals(NewUserPageText, "New User");

		// Clicking on Save button

		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
		String EmailRequiredText = driver.findElement(	By.cssSelector("input.input-block-level.textBox.invalid.error.errorBox"))
											.getAttribute("value");

		Reporter.log(EmailRequiredText + "<br>");

		Assert.assertEquals(EmailRequiredText, "Email Address is required");

		// helperFunctions.click(By.cssSelector("td.mdDetailInputValue.error"));
		driver.findElement(	By.cssSelector("input.input-block-level.textBox.invalid.error.errorBox"))
				.clear();

		helperFunctions.typeText(	By.xpath("//input[@class= 'input-block-level' and @placeholder='First Name']"),
									"AutomationFirstName"
											+ helperFunctions.randomNum(1,
																		100000));
		helperFunctions.typeText(	By.xpath("//input[@class= 'input-block-level' and @placeholder='Last Name']"),
									"Last");
		helperFunctions.typeText(	By.xpath("//tr[@id='emailRow']/td[@class='mdDetailInputValue']"
												+ "/input[@placeholder='Email']"),
									"AutomationEmail"
											+ helperFunctions.randomNum(1,
																		100000)
											+ "@gmail.com");
		helperFunctions.typeText(	By.xpath("//input[@class= 'input-block-level' and @placeholder='Password']"),
									"Password");
		helperFunctions.click(By.cssSelector("button.btn.btn-primary.saveModelBtn"));
	}

	/*
	 * To test user should able to select product plans in one of 4plans
	 */
	@Test
	public void WEB_1401()
	{
		Reporter.log("WEB-1401" + "<br>");
		System.out.println("WEB-1401");
		Reporter.log("Login by Superadmin credentials" + "<br>");
		superadmin.login();
		helperFunctions.click(By.linkText("Accounts"));
		helperFunctions.click(By.id("addAccount"));
		// Entering details for creation of User Account
		helperFunctions.typeText(	By.cssSelector("input.input-block-level.textBox"),
									"AutomationName"
											+ helperFunctions.randomNum(1,
																		1000000));

		// For Email Address
		helperFunctions.typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
									"Automationmail"
											+ helperFunctions.randomNum(1,
																		100000)
											+ "@gmail.com");

		Reporter.log("Selecting Active state for all products" + "<br>");
		helperFunctions.click(By.xpath("//label[text()='On Cue Restaurants']//ancestor::tr//div[@class='selectric']"));
		helperFunctions.click(By.xpath("//label[text()='On Cue Restaurants']//ancestor::tr//*[@class='selectricItems']/ul/li[@class='last']"));
		helperFunctions.click(By.xpath("//label[text()='Table Tracker']//ancestor::tr//div[@class='selectric']"));
		helperFunctions.click(By.xpath("//label[text()='Table Tracker']//ancestor::tr//*[@class='selectricItems']/ul/li[@class='last']"));
		helperFunctions.click(By.xpath("//label[text()='Check Point']//ancestor::tr//div[@class='selectric']"));
		helperFunctions.click(By.xpath("//label[text()='Check Point']//ancestor::tr//*[@class='selectricItems']/ul/li[@class='last']"));

		// Checking for List Items of PricingItems for On Cue Restaurants
		WebElement onCueElement = driver.findElement(By.xpath("//label[text()='On Cue Restaurants']//ancestor::tr//li[text()='Free']/ancestor::td//div[@class='selectricItems']//ul"));
		List<WebElement> onCueElementsValue = onCueElement.findElements(By.tagName("li"));
		System.out.println("Size of List Items--" + onCueElementsValue.size());
		List<String> answerTypesOnCue = new ArrayList<String>();
		Iterator<WebElement> itrOnCue = onCueElementsValue.iterator();
		while (itrOnCue.hasNext())
		{
			WebElement onCueEleme = itrOnCue.next();
			answerTypesOnCue.add(onCueEleme.getAttribute("textContent"));

		}

		System.out.println(answerTypesOnCue);
		List expectedAnsTypesOnCue = Arrays.asList(new String[] { "Basic",
																	"Advanced",
																	"Pro",
																	"Free" });
		Assert.assertEquals(answerTypesOnCue, expectedAnsTypesOnCue);

		// Checking for List Items of PricingItems for Table Tracker
		WebElement TableTracker = driver.findElement(By.xpath("//label[text()='Table Tracker']//ancestor::tr//td[3]//div[@class='selectricItems']//ul"));
		List<WebElement> TableTrackerElementsValue = TableTracker.findElements(By.tagName("li"));
		List<String> answerTypesTableTracker = new ArrayList<String>();
		Iterator<WebElement> itrTableTracker = TableTrackerElementsValue.iterator();
		while (itrTableTracker.hasNext())
		{
			WebElement TableTrackerEleme = itrTableTracker.next();
			answerTypesTableTracker.add(TableTrackerEleme.getAttribute("textContent"));
		}
		List<String> expectedAnsTypesTableTracker = Arrays.asList(new String[] { "Standard" });
		Assert.assertEquals(answerTypesTableTracker,
							expectedAnsTypesTableTracker);

		// Checking for List Items of PricingItems for CheckPoint
		WebElement CheckPoint = driver.findElement(By.xpath("//label[text()='Check Point']//ancestor::tr//li[text()='Free']/ancestor::td//div[@class='selectricItems']//ul"));
		List<WebElement> CheckPointElementsValue = CheckPoint.findElements(By.tagName("li"));
		List<String> answerTypesCheckPoint = new ArrayList<String>();
		Iterator<WebElement> itrCheckPoint = CheckPointElementsValue.iterator();
		while (itrCheckPoint.hasNext())
		{
			WebElement CheckPointEleme = itrCheckPoint.next();
			answerTypesCheckPoint.add(CheckPointEleme.getAttribute("textContent"));
		}
		List expectedAnsTypesCheckPoint = Arrays.asList(new String[] {
																		"Basic",
																		"Advanced",
																		"Pro",
																		"Free" });
		Assert.assertEquals(answerTypesCheckPoint, expectedAnsTypesCheckPoint);

		// Clicking on Save button
		Reporter.log("Clicking on Save button" + "<br>");
		helperFunctions.click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));
		String accName = driver.findElement(By.id("accountNameLbl")).getText();
		helperFunctions.scrollToBottom();
		helperFunctions.typeText(By.id("mdSearchBox"), accName);
		helperFunctions.click(By.id("mdSearchWidgetIcon"));
		String actualPending = driver.findElement(	By.xpath("//div[text()='"
																+ accName
																+ "']//ancestor::div[2]//div[@class='mdMasterTableSecondaryLabel']"))
										.getText();
		Assert.assertEquals(actualPending, "Pending");
	}

	@AfterMethod
	public void cleanupTest(ITestResult result)
	{
		// not printed in report
		// Reporter.log("<br><br>-----<br>inside @AfterMethod" + "<br>");

		System.out.println("inside @AfterMethod");

		try
		{
			helperFunctions.signOut();
		}
		catch (Exception e)
		{
			// Reporter.log("Exception caught while signing out at testCleanUp, "
			// + "closing Browser now." + "<br>");

			System.out.println("Exception caught while signing out at testCleanUp "
								+ "\n");
		}
		// driver.close();
		// driver.get(read.getURL());

		if (result.getStatus() == ITestResult.FAILURE)
			System.out.println("Status: FAILED");
		else if (result.getStatus() == ITestResult.SKIP)
			System.out.println("Status: SKIPPED");
		else
			System.out.println("Status: PASSED");

		System.out.println("---------------------------------------------------------------------------------\n");

		// Reporter.log("---------------------------------------------------------------------------------"
		// + "<br>");
	}

	@AfterSuite
	public void cleanSuite()
	{
		// Reporter.log("inside @AfterSuite" + "<br>");
		driver.close();
		driver.quit();
		driver = null;
		// Reporter.log("---------------------------------------------------------------------------------"
		// + "<br>");
	}
}
