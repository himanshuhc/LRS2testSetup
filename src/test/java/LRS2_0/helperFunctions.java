package LRS2_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class helperFunctions
{
	static WebDriver driver;
	static long implicitTimeout;
	public static Reader read;

	static String newAutomationUser = "";
	static String xpath_programManager_fieldToCheck = "//tr[@class='mdDetailInput']//td[contains(@class,'error')]"
														+ "/TAG_TYPE[@id='FIELD_TO_CHECK']/following-sibling::input[contains(@class,'error')]";

	static String browserUsed = "";

	public helperFunctions()
	{
		implicitTimeout = 10;
		driver = runTests.driver;
		read = runTests.read;
		browserUsed = read.getBrowser();
	}

	public static void scrollToBottom()
	{
		waitForAjaxRefresh();
		// not needed to scroll anymore

		// Reporter.log("Scrolling to Bottom" + "<br>");
		// int countOfListItems;
		// String lastItemTitle = "";
		// String currLastItemTitle;
		// List<WebElement> allItemsList;
		//
		// By byAllListItems = By.xpath("//div[@id='mdMasterListItems']"
		// + "/div[starts-with(@class,'listItem')]");
		// try
		// {
		// allItemsList = driver.findElements(byAllListItems);
		// }
		// catch (NoSuchElementException e)
		// {
		// Reporter.log("NoSuchElementException caught here, Trying again.."
		// + "<br>");
		// waitForAjaxRefresh();
		// try
		// {
		// Thread.sleep(3000);
		// }
		// catch (InterruptedException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// waitForAjaxRefresh();
		// allItemsList = driver.findElements(byAllListItems);
		// }
		//
		// countOfListItems = allItemsList.size();
		// Reporter.log("List Items Found here: " + countOfListItems + "<br>");
		//
		// By byPrimaryLabel = By.className("mdMasterTablePrimaryLabel");
		//
		// try
		// {
		// currLastItemTitle = allItemsList.get(countOfListItems - 1)
		// .findElement(byPrimaryLabel)
		// .getAttribute("textContent").trim();
		// }
		// catch (NoSuchElementException e)
		// {
		// Reporter.log("NoSuchElementException caught here, Trying again..."
		// + "<br>");
		// waitForAjaxRefresh();
		// try
		// {
		// Thread.sleep(1000);
		// }
		// catch (InterruptedException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// waitForAjaxRefresh();
		// currLastItemTitle = allItemsList.get(countOfListItems - 1)
		// .getAttribute("textContent").trim();
		// }
		//
		// while (!currLastItemTitle.equals(lastItemTitle))
		// {
		// click(allItemsList.get(countOfListItems - 1));
		//
		// waitForAjaxRefresh();
		// typeText(allItemsList.get(countOfListItems - 1), Keys.END);
		//
		// waitForAjaxRefresh();
		// lastItemTitle = currLastItemTitle;
		// try
		// {
		// allItemsList = driver.findElements(byAllListItems);
		// }
		// catch (NoSuchElementException e)
		// {
		// Reporter.log("NoSuchElementException caught here, Trying again...."
		// + "<br>");
		// waitForAjaxRefresh();
		// try
		// {
		// Thread.sleep(1000);
		// }
		// catch (InterruptedException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// waitForAjaxRefresh();
		// allItemsList = driver.findElements(byAllListItems);
		// }
		//
		// countOfListItems = allItemsList.size();
		// Reporter.log("List Items Found here-> " + countOfListItems + "<br>");
		//
		// try
		// {
		// currLastItemTitle = allItemsList.get(countOfListItems - 1)
		// .findElement(byPrimaryLabel)
		// .getAttribute("textContent")
		// .trim();
		// }
		// catch (NoSuchElementException e)
		// {
		// Reporter.log("NoSuchElementException caught here, Trying again....."
		// + "<br>");
		// waitForAjaxRefresh();
		// try
		// {
		// Thread.sleep(1000);
		// }
		// catch (InterruptedException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// waitForAjaxRefresh();
		// currLastItemTitle = allItemsList.get(countOfListItems - 1)
		// .getAttribute("textContent")
		// .trim();
		// }
		// }
		//
		// Reporter.log("Last Item Found in Scroll List:" + lastItemTitle +
		// "<br>");

	}

	public static void checkListIsAlphabeticalAndFirstItemSelected()
	{
		int countOfListItems;
		String lastItemTitle = "";
		String currLastItemTitle;
		List<WebElement> listAllItems;

		listAllItems = driver.findElements(By.xpath("//div[@id='mdMasterListItems']/div[starts-with(@class,'listItem')]"));

		// check first item is selected
		Assert.assertTrue(	(getAttributeValue(listAllItems.get(0), "class")).equals("listItem selected"),
							"First Item in List was not selected by default");

		// check Alphabetical
		ArrayList<String> listItemTitles = new ArrayList<String>();

		countOfListItems = listAllItems.size();
		String itemTitle = "";
		for (int titleIndex = 0; titleIndex < countOfListItems; titleIndex++)
		{
			// itemTitle = getAttributeValue(listAllItems.get(titleIndex),
			// "title");
			itemTitle = listAllItems.get(titleIndex).getAttribute("title");
			if (!itemTitle.equals(""))
				listItemTitles.add(itemTitle);
		}
		currLastItemTitle = itemTitle;

		while (!currLastItemTitle.equals(lastItemTitle))
		{
			click(listAllItems.get(countOfListItems - 1));

			waitForAjaxRefresh();
			typeText(listAllItems.get(countOfListItems - 1), Keys.END);

			waitForAjaxRefresh();
			lastItemTitle = currLastItemTitle;
			listAllItems = driver.findElements(By.xpath("//div[@id='mdMasterListItems']/div[starts-with(@class,'listItem')]"));
			countOfListItems = listAllItems.size();
			for (int titleIndex = 0; titleIndex < countOfListItems; titleIndex++)
			{
				// itemTitle = getAttributeValue( listAllItems.get(titleIndex),
				// "title");
				itemTitle = listAllItems.get(titleIndex).getAttribute("title");
				if (!itemTitle.equals(""))
					listItemTitles.add(itemTitle);
			}
			currLastItemTitle = itemTitle;
		}

		// uncomment this once aiphabetical sorting is correcting implemented,
		// if ever

		// Assert.assertTrue(
		// checkArrayListIsAlphabeticallyArranged(listItemTitles),
		// "List Items are not alphabetically sorted");

	}

	private static boolean checkArrayListIsAlphabeticallyArranged(	ArrayList<String> arrayList)
	{
		Reporter.log("Checking if arrayList is alphabetically sorted" + "<br>");
		String previous = "";

		for (final String current : arrayList)
		{
			if (current.compareTo(previous) < 0)
			{
				Reporter.log("Error: Alphabetical order of '" + current
								+ "' should be before '" + previous
								+ "' and not after it!" + "<br>");
				return false;
			}
			previous = current;
		}

		return true;
	}

	/**
	 * Works on the assumption that jQuery Ajax libraries are present on the
	 * page, and if they are, waits for calls to them to complete
	 */
	public static void waitForAjaxRefresh()
	{
		Reporter.log("Waiting for Ajax Refresh" + "<br>");
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			final JavascriptExecutor javascript = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
					: null);

			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver d)
				{
					boolean outcome = Boolean.parseBoolean(javascript.executeScript("return jQuery.active == 0")
																		.toString());
					return outcome;
				}
			});
		}
		catch (TimeoutException ex)
		{
			throw new TimeoutException(
										"Timed out after "
												+ "60"
												+ " seconds while waiting for Ajax to complete.");
		}
		catch (WebDriverException e)
		{
			Reporter.log("JQuery libraries are not present on page "
							+ driver.getCurrentUrl() + " - "
							+ driver.getTitle() + "<br>");
		}
	}

	public static void click(WebElement elem)
	{
		waitForAjaxRefresh();
		// Reporter.log("Going to click on element: " +
		// elemBy.toString());
		try
		{
			// just to check if StaleElementReferenceException is thrown
			boolean checkExist = elem.isDisplayed();
		}
		catch (StaleElementReferenceException e)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("StaleElementReference Exception Caught, Trying Again.."
							+ "<br>");

		}
		catch (NoSuchElementException ne)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("NoSuchElement Exception Caught, Trying Again.."
							+ "<br>");
		}
		finally
		{
			// clicking using JS
			clickUsingJS(elem);
		}
		// Reporter.log("Clicked on element.");
		waitForAjaxRefresh();

	}

	public static void click(By elemBy)
	{
		waitForAjaxRefresh();
		// Reporter.log("Going to click on element: " +
		// elemBy.toString());
		try
		{
			// just to check if StaleElementReferenceException is thrown
			boolean checkExist = driver.findElement(elemBy).isDisplayed();
		}
		catch (StaleElementReferenceException e)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("StaleElementReference Exception Caught, Trying Again.."
							+ "<br>");

		}
		catch (NoSuchElementException ne)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("NoSuchElement Exception Caught, Trying Again.."
							+ "<br>");
		}
		finally
		{
			// clicking using JS
			waitForAjaxRefresh();
			clickUsingJS(elemBy);
		}
		// Reporter.log("Clicked on element: " + elemBy.toString());
		waitForAjaxRefresh();

	}

	public static boolean waitForElementToExist(final By byElementToFind,
												long waitTime,
												Boolean waitForAjax)
	{

		try
		{
			Reporter.log("Waiting for element " + byElementToFind.toString()
							+ " to exist" + "<br>");
			if (waitForAjax)
			{
				waitForAjaxRefresh();
			}
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			Boolean foundElement = wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver)
				{
					return elementExists(byElementToFind);
				}
			});

			driver.manage().timeouts()
					.implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
			Reporter.log("Found Element: " + byElementToFind + "<br>");
			return foundElement;
		}
		catch (TimeoutException ex)
		{
			Reporter.log("Failed to find element '" + byElementToFind
							+ "' after " + waitTime + " seconds." + "<br>");
			return false;
		}
	}

	private static boolean elementExists(By by)
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Boolean elementFound = false;

		try
		{
			@SuppressWarnings("unused")
			WebElement toFind = driver.findElement(by);
			elementFound = true;
		}
		catch (NoSuchElementException ex)
		{
			elementFound = false;
		}

		driver.manage().timeouts()
				.implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
		Reporter.log("Found Element " + by.toString() + ": " + elementFound
						+ "<br>");
		return elementFound;
	}

	public static String randomNum(int lowerLimit, int upperLimit)
	{
		Random r = new Random();
		return Integer.toString(r.nextInt(upperLimit - lowerLimit) + lowerLimit);
	}

	public static void clickOnProductOnMainScreen(String prodName)
	{
		String prodXpath = "//div[(@class='productName') and (contains(text(),'"
							+ prodName + "'))]";
		click(By.xpath(prodXpath));
		waitForAjaxRefresh();
		// waitForElementToExist(By.cssSelector("img#searchImage"),
		// read.getlongWaitTime(), true);
	}

	public static boolean verifyProductExistsOnMainScreen(String prodName)
	{
		String prodXpath = "//div[(@class='productName') and (contains(text(),'"
							+ prodName + "'))]";
		return waitForElementToExist(	By.xpath(prodXpath),
										read.getveryShortWaitTime(), true);
	}

	public static String addLocation()
	{
		String newAutomationLocName = "AutomationLoc" + randomNum(1, 1000000);
		waitForElementToBeVisible(	By.id("mdSearchBox"),
									read.getlongWaitTime(),
									true);
		waitForElementToBeVisible(By.id("home"), read.getlongWaitTime(), true);
		JSmouseOver(By.id("home"));
		By byAddLoc = By.id("addLocation");
		click(byAddLoc);

		By byLocationName = By.xpath("//input[@placeholder='Location Name']");
		WebElement elemLocName = driver.findElement(byLocationName);
		if (elementIsStale(elemLocName, read.getveryShortWaitTime()))
		{
			waitForAjaxRefresh();
			JSmouseOver(By.id("home"));
			click(byAddLoc);
		}

		typeText(byLocationName, newAutomationLocName);
		typeText(	By.xpath("//input[@placeholder='Address Line']"),
					"AutomationAddress" + randomNum(1, 1000000));
		typeText(By.xpath("//input[@placeholder='City']"), "Test City");
		typeText(By.xpath("//input[@placeholder='State']"), "TX");
		typeText(By.xpath("//input[@placeholder='Zip']"), "11011");
		typeText(By.xpath("//input[@name='phoneNo']"), "1234567890");
		click(By.xpath("//button[(contains(@style,'inline-block')) and (text()='Save')]"));

		click(By.id("home"));
		searchForItemInLeftPane(newAutomationLocName);

		By headingTitle = By.xpath("//div[@class='mdDetailsTable']//span[@class='headerSubTitleLarge']");
		waitForElementToExist(headingTitle, read.getlongWaitTime(), true);
		String locNameVisible = getText(headingTitle);
		Assert.assertEquals(locNameVisible, newAutomationLocName,
							"Location Title not same as added location");
		return newAutomationLocName;
	}

	public static void deleteLocation(String locName)
	{
		click(By.className("icon-pencil"));
		click(By.cssSelector("button.btn.deleteModelBtn"));
		click(By.cssSelector("button.btn.btn-primary.btn-large.modalConfirmBtn"));
		// scrollToBottom();
		By bySearchBox = By.id("mdSearchBox");
		// typeText(bySearchBox, locName);
		typeText(driver.findElement(bySearchBox), Keys.ENTER);
		Assert.assertTrue(waitForElementToExist(By.xpath("//div[contains(text(),'No Locations found')]"),
												read.getshortWaitTime(), true));
	}

	public static void searchForItemInLeftPane(String itemToSearch)
	{
		// Check if item is present in the left pane
		By bySearchBox = By.id("mdSearchBox");
		scrollToBottom();
		waitForElementToBeVisible(bySearchBox, read.getshortWaitTime(), true);
		typeText(bySearchBox, itemToSearch);
		typeText(driver.findElement(bySearchBox), Keys.ENTER);
		// click(By.xpath("//div[@class='mdMasterNode']//div[starts-with(text(),'"
		// + itemToSearch + "')]"));
		waitForAjaxRefresh();
	}

	public static String getText(By elemBy)
	{
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(513);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String textFetched = driver.findElement(elemBy).getText();
		Reporter.log("Text retrieved from element: " + elemBy.toString()
						+ " is: " + textFetched + "<br>");
		return textFetched;
	}

	public static String getAttributeValue(By elemBy, String attributeToFetch)
	{
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(513);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String valFetched = driver.findElement(elemBy)
									.getAttribute(attributeToFetch);
		Reporter.log("Value of '" + attributeToFetch
						+ "' attribute retrieved from element: "
						+ elemBy.toString() + " is: " + valFetched + "<br>");
		return valFetched;
	}

	public static String getAttributeValue(WebElement elem,
											String attributeToFetch)
	{
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(513);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String valFetched = elem.getAttribute(attributeToFetch);
		Reporter.log("Value of '" + attributeToFetch
						+ "' attribute retrieved from element is: "
						+ valFetched + "<br>");
		return valFetched;
	}

	private static void typeText(WebElement elem, Keys keyboardKEY)
	{
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(1313);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Attempting to send Keyboard Key" + "<br>");
		try
		{
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);
			if (!browserUsed.equals("Chrome"))
				elem.sendKeys(keyboardKEY);
			else
				actionClass_SendKeys(elem, keyboardKEY);

		}
		catch (StaleElementReferenceException e)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("StaleElementReference Exception Caught, Trying Again.."
							+ "<br>");
			// forcing sleep as IE not loading page inspite of completion of
			// waitForAjaxRefresh()
			try
			{
				Thread.sleep(1313);
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);
			if (!browserUsed.equals("Chrome"))
				elem.sendKeys(keyboardKEY);
			else
				actionClass_SendKeys(elem, keyboardKEY);
		}
		waitForAjaxRefresh();
		Reporter.log("Successfully sent Keyboard Key" + "<br>");
	}

	public static void typeText(WebElement elem, String inputText)
	{
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(1313);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Attempting to type:" + inputText + "<br>");
		try
		{
			waitForElementToBeVisible(elem, read.getshortWaitTime(), true);
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);
			if (!browserUsed.equals("Chrome"))
				elem.sendKeys(inputText);
			else
				actionClass_SendKeys(elem, inputText);
		}
		catch (StaleElementReferenceException e)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("StaleElementReference Exception Caught, Trying Again.."
							+ "<br>");
			// forcing sleep as IE not loading page inspite of completion of
			// waitForAjaxRefresh()
			try
			{
				Thread.sleep(1313);
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			waitForElementToBeVisible(elem, read.getveryShortWaitTime(), true);
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);
			if (!browserUsed.equals("Chrome"))
				elem.sendKeys(inputText);
			else
				actionClass_SendKeys(elem, inputText);
		}
		catch (NoSuchElementException ne)
		{
			// retry in a moment
			waitForAjaxRefresh();
			try
			{
				Thread.sleep(3313);
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Reporter.log("NoSuchElement Exception Caught, Trying Again.."
							+ "<br>");
			if (!browserUsed.equals("Chrome"))
				elem.sendKeys(inputText);
			else
				actionClass_SendKeys(elem, inputText);
		}
		waitForAjaxRefresh();
		// check if value was typed successfully or not. If not, try one more
		// time using selenium's sendkeys
		if (!elem.getAttribute("value").equals(inputText))
			elem.sendKeys(inputText);
		Reporter.log("Successfully typed:" + inputText + "<br>");

	}

	public static void typeText(By elemBy, String inputText)
	{
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(1313);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Attempting to type:" + inputText + " inside "
						+ elemBy.toString() + "<br>");
		try
		{
			waitForElementToBeVisible(elemBy, read.getshortWaitTime(), true);
			// bring to focus
			jExecutor.executeScript("arguments[0].focus();",
									driver.findElement(elemBy));
			if (!browserUsed.equals("Chrome"))
				driver.findElement(elemBy).sendKeys(inputText);
			else
				actionClass_SendKeys(elemBy, inputText);
		}
		catch (StaleElementReferenceException e)
		{
			// retry in a moment
			waitForAjaxRefresh();
			Reporter.log("StaleElementReference Exception Caught, Trying Again.."
							+ "<br>");
			// forcing sleep as IE not loading page inspite of completion of
			// waitForAjaxRefresh()
			try
			{
				Thread.sleep(1313);
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// bring to focus first

			waitForElementToBeVisible(elemBy, read.getveryShortWaitTime(), true);
			jExecutor.executeScript("arguments[0].focus();",
									driver.findElement(elemBy));
			if (!browserUsed.equals("Chrome"))
				driver.findElement(elemBy).sendKeys(inputText);
			else
				actionClass_SendKeys(elemBy, inputText);
		}
		catch (NoSuchElementException ne)
		{
			// retry in a moment
			waitForAjaxRefresh();
			try
			{
				Thread.sleep(3313);
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Reporter.log("NoSuchElement Exception Caught, Trying Again.."
							+ "<br>");
			if (!browserUsed.equals("Chrome"))
				driver.findElement(elemBy).sendKeys(inputText);
			else
				actionClass_SendKeys(elemBy, inputText);
		}
		waitForAjaxRefresh();
		// check if value was typed successfully or not. If not, try one more
		// time using selenium's sendkeys
		if (!driver.findElement(elemBy).getAttribute("value").equals(inputText))
			driver.findElement(elemBy).sendKeys(inputText);
		Reporter.log("Successfully typed:" + inputText + " inside "
						+ elemBy.toString() + "<br>");

	}

	public static void switchProductThroughTopMenu(String menuItem)
	{
		JSmouseOver(By.xpath("//div[@id='selectedProduct']/div[@class='productLinkDiv']"));
		clickUsingJS(By.xpath("//ul[@id='productMenuSelect']//div[contains(text(),'"
								+ menuItem + "')]"));
		waitForAjaxRefresh();
		// forcing sleep
		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForAjaxRefresh();

	}

	public static void JSmouseOver(By elemBy)
	{
		Reporter.log("Moving mouse over: " + elemBy.toString() + "<br>");
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		String hoverScript = "var evObj = document.createEvent('MouseEvents');"
								+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
								+ "arguments[0].dispatchEvent(evObj);";
		jExecutor.executeScript(hoverScript, driver.findElement(elemBy));
	}

	public static void clickUsingJS(WebElement elem)
	{
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(1313);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Going to click on element using JS" + "<br>");
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		try
		{
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);

			jExecutor.executeScript("arguments[0].click();", elem);
		}
		catch (StaleElementReferenceException se)
		{
			Reporter.log("StaleElementReferenceException caught inside JSclick(WebElement elem). Trying again..."
							+ "<br>");
			waitForAjaxRefresh();
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();", elem);

			jExecutor.executeScript("arguments[0].click();", elem);
		}
		Reporter.log("Clicked on element using JS" + "<br>");
		waitForAjaxRefresh();
	}

	public static void clickUsingJS(By elemBy)
	{
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(1313);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Going to click on element using JS: " + elemBy.toString()
						+ "<br>");
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		try
		{
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();",
									driver.findElement(elemBy));

			jExecutor.executeScript("arguments[0].click();",
									driver.findElement(elemBy));
		}
		catch (StaleElementReferenceException se)
		{
			Reporter.log("StaleElementReferenceException caught inside JSclick(By elemBy). Trying again..."
							+ "<br>");
			waitForAjaxRefresh();
			// bring to focus first
			jExecutor.executeScript("arguments[0].focus();",
									driver.findElement(elemBy));

			jExecutor.executeScript("arguments[0].click();",
									driver.findElement(elemBy));
		}
		Reporter.log("Clicked on element using JS: " + elemBy.toString()
						+ "<br>");
		waitForAjaxRefresh();
	}

	public static void signOut()
	{
		click(By.cssSelector("a.dropdown-toggle"));
		// driver.findElement(By.id("logoutLink")).click();
		click(By.xpath("//ul[@class='dropdown-menu']//li/a[contains(text(),' Out')]"));
		Reporter.log("Signed Out" + "<br>");
	}

	public static void login(String userName)
	{
		Reporter.log("Logging in as " + userName + " on " + read.getBrowser()
						+ " on " + read.getPlatform() + "<br>");
		driver.get(read.getURL());

		// helperFunctions.waitForElementToExist( By.id("btnLoginSubmit"),
		// read.getlongWaitTime(), true);
		helperFunctions.waitForLoginPage();
		helperFunctions.typeText(	By.cssSelector("*[id='j_username']"),
									read.getGeneralUserName(userName));
		helperFunctions.typeText(	By.id("j_password"),
									read.getGeneralUserPassword(userName));

		helperFunctions.click(driver.findElement(By.id("btnLoginSubmit")));
		waitForAjaxRefresh();
		helperFunctions.waitForElementToBeVisible(	By.xpath("//ul[@id='main-menu-right']"
																+ "//a[@class='dropdown-toggle']"),
													read.getmediumWaitTime(),
													true);
	}

	/**
	 * Fetch the text of a pop-up, then either accept it (true) or dismiss it
	 * (false)
	 */
	public static String handlePopup(boolean acceptDialog)
	{
		// if ((SeleniumDriverWrapper.Safari))
		// {
		// throw new NotImplementedException(
		// "Safari cannot handle pop-up dialogs: See https://code.google.com/p/selenium/issues/detail?id=3862");
		// //may have to use JS
		// }

		WebDriverWait wait = new WebDriverWait(driver,
												read.getveryShortWaitTime());

		Alert alert = wait.until(new ExpectedCondition<Alert>() {
			@Override
			public Alert apply(WebDriver driver)
			{
				try
				{
					return driver.switchTo().alert();
				}
				catch (NoAlertPresentException nap)
				{
					return null;
				}
			}
		});

		String alertText = alert.getText();
		Reporter.log("Alert present with text: " + alertText + ". We are "
						+ (acceptDialog ? "accepting" : "dismissing")
						+ " this dialog" + "<br>");

		if (acceptDialog)
		{
			alert.accept();
		}
		else
		{
			alert.dismiss();
		}

		return alertText.trim();
	}

	public static void tryAddingUserWithBlankEmail()
	{
		goToUsersTab();

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[contains(text(),'Reset Password')]"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("mdSearchBox"),
																read.getveryShortWaitTime(),
																true));
		JSmouseOver(By.id("users"));
		click(By.id("addUser"));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[text()='Cancel']"),
																read.getmediumWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[text()='Save']"),
																read.getveryShortWaitTime(),
																true));

		newAutomationUser = "";
		helperFunctions.typeText(By.id("emailTxt"), newAutomationUser);

		String[] expectedOptionsArray = { "Manager", "Reporting", "None" };

		List<WebElement> locPermissionDropDown;
		locPermissionDropDown = driver.findElements(By.cssSelector("select.permission"));
		Select permListSelect;
		permListSelect = new Select(locPermissionDropDown.get(0));

		compareLists(expectedOptionsArray, permListSelect);

		// permListSelect.selectByVisibleText("Manager");
		selectValueInDropDownByVisibleText(permListSelect, "Manager");

		permListSelect = new Select(locPermissionDropDown.get(1));
		// permListSelect.selectByVisibleText("Reporting");
		selectValueInDropDownByVisibleText(permListSelect, "Reporting");

		clickUsingJS(By.xpath("//button[@class='btn btn-primary saveModelBtn']"));

		Assert.assertEquals(driver.findElement(	By.cssSelector("input.input-block-level."
																+ "textBox.invalid.error.errorBox"))
									.getAttribute("value"),
							"Email Address is required");

	}

	public static String addUser()
	{
		goToUsersTab();

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[contains(text(),'Reset Password')]"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("mdSearchBox"),
																read.getveryShortWaitTime(),
																true));
		JSmouseOver(By.id("users"));
		click(By.id("addUser"));

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[text()='Cancel']"),
																read.getmediumWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[text()='Save']"),
																read.getveryShortWaitTime(),
																true));

		newAutomationUser = "AutomationUser"
							+ helperFunctions.randomNum(1, 1000000)
							+ "@gmail.com";
		helperFunctions.typeText(By.id("emailTxt"), newAutomationUser);

		String[] expectedOptionsArray = { "Manager", "Reporting", "None" };

		List<WebElement> locPermissionDropDown;
		locPermissionDropDown = driver.findElements(By.cssSelector("select.permission"));
		Select permListSelect;
		permListSelect = new Select(locPermissionDropDown.get(0));

		compareLists(expectedOptionsArray, permListSelect);

		// permListSelect.selectByVisibleText("Manager");
		selectValueInDropDownByVisibleText(permListSelect, "Manager");

		permListSelect = new Select(locPermissionDropDown.get(1));
		// permListSelect.selectByVisibleText("Reporting");
		selectValueInDropDownByVisibleText(permListSelect, "Reporting");

		clickUsingJS(By.xpath("//button[@class='btn btn-primary saveModelBtn']"));
		waitForElementToExist(	By.xpath("//button[contains(text(),'Delete User')]"),
								read.getmediumWaitTime(), true);

		// verify user is auto selected with pending status in left pane
		String selectedUser = fetchSelectedItemInLeftPane();
		Assert.assertEquals(selectedUser, newAutomationUser);
		String secondaryLabel = fetchSelectedItem_SecondaryLabel_fromLeftPane();
		Assert.assertEquals(secondaryLabel, "Invitation Pending");

		// verify email in right pane
		Assert.assertEquals(getAttributeValue(By.name("email"), "value"),
							newAutomationUser);

		click(By.cssSelector("a#users"));
		return newAutomationUser;
	}

	public static void deleteUser(String userToSearch)
	{
		By byUserSearchBox = By.xpath("//input[@placeholder='Type to search users']");
		waitForElementToBeVisible(	byUserSearchBox, read.getshortWaitTime(),
									true);
		searchForItemInLeftPane(userToSearch);

		click(By.xpath("//button[contains(text(),'Delete User')]"));
		click(By.cssSelector("button.btn.modalConfirmBtn"));
		click(By.cssSelector("a#users"));
		// scrollToBottom();
		// click(By.id("searchImage"));
		By bySearchBox = By.id("mdSearchBox");
		// driver.findElement(bySearchBox).clear();
		By byClearSearch = By.id("mdClearSearchIcon");
		// if (waitForElementToBeVisible( byClearSearch,
		// read.getveryShortWaitTime(), true))
		// click(byClearSearch);
		typeText(bySearchBox, newAutomationUser);
		// click(By.id("mdSearchWidgetIcon"));
		typeText(driver.findElement(bySearchBox), Keys.ENTER);
		Assert.assertTrue(waitForElementToExist(By.xpath("//div[contains(text(),'No Users found')]"),
												read.getshortWaitTime(), true));
	}

	public static void addAndDeleteUser()
	{
		String newlyAddedUser = addUser();
		deleteUser(newlyAddedUser);
	}

	public static void goToUsersTab()
	{
		helperFunctions.click(By.cssSelector("a#users"));
		By byUserSearchBox = By.xpath("//input[@placeholder='Type to search users']");
		waitForElementToBeVisible(	byUserSearchBox, read.getshortWaitTime(),
									true);
		if (!helperFunctions.waitForElementToExist(	By.xpath("//button[contains(text(),'Delete User')]"),
													read.getshortWaitTime(),
													true))
		{
			// try clicking once more on the users link
			helperFunctions.click(By.cssSelector("a#users"));
			waitForElementToBeVisible(	byUserSearchBox,
										read.getshortWaitTime(),
										true);

			click(driver.findElements(	By.xpath("//div[@id='mdMasterListItems']"
													+ "/div[starts-with(@class,'listItem')]"))
						.get(1));

			Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//button[contains(text(),'Delete User')]"),
																	read.getshortWaitTime(),
																	true));
		}
	}

	public static String fetchSelectedItem_SecondaryLabel_fromLeftPane()
	{
		String selectedItem = driver.findElement(	By.xpath("//div[@id='mdMasterListItems']/div[@class='listItem selected']//a"))
									.getText().trim();
		Reporter.log("Selected Item in Left Pane:" + selectedItem
						+ " <br> and," + "<br>");

		String secondaryLabel = driver.findElement(	By.xpath("//div[@id='mdMasterListItems'	]/div[@class='listItem selected']"
																+ "//a/ancestor::div[1]/following-sibling::div[@class='mdMasterTableSecondaryLabel']"))
										.getText().trim();
		Reporter.log("Secondary Label Of Selected Item in Left Pane:"
						+ secondaryLabel + "<br>");
		return secondaryLabel;
	}

	public static String fetchSelectedItemInLeftPane()
	{
		String selectedItem = driver.findElement(	By.xpath("//div[@id='mdMasterListItems']/div[@class='listItem selected']//a"))
									.getText().trim();
		Reporter.log("Selected Item in Left Pane:" + selectedItem + "<br>");
		return selectedItem;
	}

	public static void compareLists(String[] expectedOptionsArray,
									Select permListSelect)
	{
		Reporter.log("Comparing contents of dropDown. Expected contents:"
						+ Arrays.toString(expectedOptionsArray) + "<br>");
		List<WebElement> actualOptionsPresent = permListSelect.getOptions();

		String[] actualOptionsPresentArray = new String[actualOptionsPresent.size()];
		for (int checkPerm = 0; checkPerm < actualOptionsPresent.size(); checkPerm++)
		{
			actualOptionsPresentArray[checkPerm] = actualOptionsPresent.get(checkPerm)
																		.getText()
																		.trim();
			Reporter.log("Option Present: "
							+ actualOptionsPresentArray[checkPerm] + "<br>");
		}
		Assert.assertEqualsNoOrder(	actualOptionsPresentArray,
									expectedOptionsArray);
	}

	public static void compareLists(String[] expectedOptionsArray,
									Select permListSelect,
									String optionAttribute)
	{
		Reporter.log("Comparing contents of dropDown. Expected contents:"
						+ Arrays.toString(expectedOptionsArray) + "<br>");
		List<WebElement> actualOptionsPresent = permListSelect.getOptions();

		String[] actualOptionsPresentArray = new String[actualOptionsPresent.size()];
		for (int checkPerm = 0; checkPerm < actualOptionsPresent.size(); checkPerm++)
		{
			actualOptionsPresentArray[checkPerm] = actualOptionsPresent.get(checkPerm)
																		.getAttribute(	optionAttribute)
																		.trim();
			Reporter.log("Option Present: "
							+ actualOptionsPresentArray[checkPerm] + "<br>");
		}
		Assert.assertEqualsNoOrder(	actualOptionsPresentArray,
									expectedOptionsArray);
	}

	public static void editUserPermissionsForLocation()
	{
		goToUsersTab();
		searchForItemInLeftPane(read.getGeneralUserCompanyName("user2"));

		List<WebElement> locPermissionDropDown;
		locPermissionDropDown = driver.findElements(By.cssSelector("select.permission"));
		Select permListSelect;
		permListSelect = new Select(locPermissionDropDown.get(0));
		String selectedOption = permListSelect.getFirstSelectedOption()
												.getText();
		Reporter.log("Default Selected Option:" + selectedOption + "<br>");
		String newOption = "";
		if (!selectedOption.equals("Manager"))
			newOption = "Manager";
		else
			newOption = "Reporting";

		selectValueInDropDownByVisibleText(permListSelect, newOption);
		clickUsingJS(By.xpath("//button[text()='Save']"));

		// page refreshes
		locPermissionDropDown = driver.findElements(By.cssSelector("select.permission"));
		permListSelect = new Select(locPermissionDropDown.get(0));

		Assert.assertEquals(permListSelect.getFirstSelectedOption().getText(),
							newOption);

		selectValueInDropDownByVisibleText(permListSelect, selectedOption);
		clickUsingJS(By.xpath("//button[text()='Save']"));

		// page refreshes
		locPermissionDropDown = driver.findElements(By.cssSelector("select.permission"));
		permListSelect = new Select(locPermissionDropDown.get(0));

		Assert.assertEquals(permListSelect.getFirstSelectedOption().getText(),
							selectedOption);
	}

	public static void selectValueInDropDownByVisibleText(	Select listSelect,
															String optionToSelect)
	{
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(513);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Current Selection in drop down is:"
						+ listSelect.getFirstSelectedOption().getText()
						+ "<br>");
		Reporter.log("Attempting to select:" + optionToSelect + "<br>");
		listSelect.selectByVisibleText(optionToSelect);
		waitForAjaxRefresh();
		Reporter.log("Selected:" + optionToSelect + "<br>");
	}

	public static void selectValueInDropDownByPartOfVisibleText(Select listSelect,
																String optionToSelect)
	{
		waitForAjaxRefresh();
		// forcing sleep as IE not loading page inspite of completion of
		// waitForAjaxRefresh()
		try
		{
			Thread.sleep(513);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reporter.log("Current Selection in drop down is:"
						+ listSelect.getFirstSelectedOption()
									.getAttribute("textContent") + "<br>");
		Reporter.log("Attempting to select based on partial text: "
						+ optionToSelect + "<br>");

		List<WebElement> allOpts = listSelect.getOptions();
		int flag = 0;
		for (int i = 0; i < allOpts.size(); i++)
		{
			String currOpt = allOpts.get(i).getAttribute("textContent");
			if (currOpt.contains(optionToSelect))
			{
				listSelect.selectByVisibleText(currOpt);
				flag = 1;
				break;
			}

		}
		if (flag == 1)
		{
			waitForAjaxRefresh();
			Reporter.log("Selected:" + optionToSelect + "<br>");
		}
		else
			Reporter.log("Option with partial text: " + optionToSelect
							+ " not found!" + "<br>");
	}

	public static void addManagerWithoutPopulatingValues()
	{
		click(By.xpath("//a[text()='Notifications']"));
		click(By.cssSelector("button.btn.addProductBtn"));
		click(By.id("modalSave"));

		// check error messages
		String fieldToCheck = "";

		fieldToCheck = xpath_programManager_fieldToCheck.replace(	"FIELD_TO_CHECK",
																	"name");
		fieldToCheck = fieldToCheck.replace("TAG_TYPE", "input");
		String nameError = getAttributeValue(By.xpath(fieldToCheck), "value");

		fieldToCheck = xpath_programManager_fieldToCheck.replace(	"FIELD_TO_CHECK",
																	"phoneNo");
		fieldToCheck = fieldToCheck.replace("TAG_TYPE", "input");
		String phoneError = getAttributeValue(By.xpath(fieldToCheck), "value");

		fieldToCheck = xpath_programManager_fieldToCheck.replace(	"FIELD_TO_CHECK",
																	"status");
		fieldToCheck = fieldToCheck.replace("TAG_TYPE", "select");
		String statusError = getAttributeValue(By.xpath(fieldToCheck), "value");

		click(By.id("modalCancel"));

		Assert.assertEquals(nameError, "Name is required.",
							"Error Msg in Name Field does not match expected value!");
		Assert.assertEquals(phoneError, "Phone number is required",
							"Error Msg in Phone Field does not match expected value!");
		Assert.assertEquals(statusError, "Status is required",
							"Error Msg in Status Field does not match expected value!");

	}

	public static void fillFieldsInPageManagerWindowAndValidatePhoneSyntaxAndSave()
	{
		click(By.cssSelector("button.btn.addProductBtn"));
		String fieldToFill = "//*[@id='name']";

		String automationProgramManagerName = "AutomationPM"
												+ randomNum(1, 1000000);
		click(By.xpath(fieldToFill));
		typeText(By.xpath(fieldToFill), automationProgramManagerName);

		Reporter.log("Changing status to ACTIVE" + "<br>");
		fieldToFill = "//*[@id='status']";
		if (browserUsed.equals("Safari"))
		{
			Select stat = new Select(driver.findElement(By.xpath(fieldToFill)));
			stat.selectByValue("ACTIVE");
		}
		else
		{
			click(By.xpath(fieldToFill));
			driver.findElement(By.xpath(fieldToFill)).sendKeys("ACTIVE");
		}
		fieldToFill = "//*[@id='phoneNo']";
		click(By.xpath(fieldToFill));
		String phonePart1 = randomNum(100, 999);
		String phonePart2 = randomNum(100, 999);
		String phonePart3 = randomNum(1000, 9999);
		String phoneNumberFinal = phonePart1 + "." + phonePart2 + "."
									+ phonePart3;

		typeText(By.xpath(fieldToFill), phoneNumberFinal.replace(".", "")
										+ "123");

		Assert.assertEquals(getAttributeValue(By.xpath(fieldToFill), "value"),
							phoneNumberFinal,
							"Phone Number not formatted as expected!");
		// save now
		click(By.id("modalSave"));
		Assert.assertTrue(	waitForElementToBeVisible(	By.xpath("//table[@class='mdDetailTable']"
																	+ "//input[@value='"
																	+ automationProgramManagerName
																	+ "']"),
														read.getveryShortWaitTime(),
														true),
							"New Program Manager Name Not Visible on Screen!");
		Assert.assertTrue(	waitForElementToBeVisible(	By.xpath("//table[@class='mdDetailTable']"
																	+ "//input[@value='"
																	+ phoneNumberFinal.replace(	".",
																								"")
																	+ "']"),
														read.getveryShortWaitTime(),
														true),
							"New Program Manager Phone Number Not Visible on Screen!");
	}

	public static void checkAccountOwnerAndManagerMenus(String fName,
														String lName)
	{

		String cssRightMainMenu = "ul#main-menu-right";
		String MenuItemTexts = "";

		String rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		String[] expectedRightMenuItems = { "Help", fName + " " + lName };
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		Assert.assertTrue(waitForElementToExist(By.xpath("//div[contains(text(),'Welcome to LRS Connect')]"),
												read.getveryShortWaitTime(),
												true));

		clickOnProductOnMainScreen("On Cue");
		waitForElementToExist(	By.xpath("//div[(contains(text(),'On Cue')) and (contains(@class,'productLinkDiv'))]"),
								read.getmediumWaitTime(), true);
		String cssLeftMainMenu = "ul#main-menu-left";
		String dropDownMenu = "ul.dropdown-menu";
		// Need to force sleep as the page sometimes does not load completely
		// even after ajaxrefresh
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			Reporter.log("Exception while waiting for top menu after clicking on On Cue"
							+ "<br>");
			e.printStackTrace();
		}

		// Reporting Link has attribute style="display: none;" to hide it, so it
		// still shows using getText
		List<WebElement> allLis = driver.findElements(By.cssSelector(cssLeftMainMenu
																		+ ">li"));
		for (int lis = 0; lis < allLis.size(); lis++)
		{

			String menuFound = allLis.get(lis).getText();
			Reporter.log("Menu Item Found:" + menuFound + "<br>");
			if (menuFound.equals("Reporting"))
				Assert.assertTrue(!(waitForElementToBeVisible(	allLis.get(lis),
																read.getveryShortWaitTime(),
																true)));
			else if (!menuFound.equals(""))
				Assert.assertTrue((waitForElementToBeVisible(	allLis.get(lis),
																read.getveryShortWaitTime(),
																true)));
		}

		rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		String subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		String[] expectedSubMenuItems = { "User Settings", "Sign Out" };
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);

		switchProductThroughTopMenu("Table Tracker");
		waitForElementToExist(	By.xpath("//div[(contains(text(),'Table Tracker')) and (contains(@class,'productLinkDiv'))]"),
								read.getmediumWaitTime(), true);
		MenuItemTexts = helperFunctions.getText(By.cssSelector(cssLeftMainMenu));
		String[] expectedMenuItemsTT = { "Locations", "Reporting", "Users" };
		Assert.assertTrue(	Arrays.asList(MenuItemTexts.split("\n"))
									.containsAll(	Arrays.asList(expectedMenuItemsTT)),
							"Menu Items did not match. Present menu items were"
									+ Arrays.toString(MenuItemTexts.split("\n")));
		// Assert.assertEqualsNoOrder( MenuItemTexts.split("\n"),
		// expectedMenuItemsTT);

		rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);

		switchProductThroughTopMenu("Check Point");
		waitForElementToExist(	By.xpath("//div[(contains(text(),'Check Point')) and (contains(@class,'productLinkDiv'))]"),
								read.getmediumWaitTime(), true);
		MenuItemTexts = helperFunctions.getText(By.cssSelector(cssLeftMainMenu));
		String[] expectedMenuItemsCP = { "Locations", "Reporting", "Users" };
		Assert.assertTrue(	Arrays.asList(MenuItemTexts.split("\n"))
									.containsAll(	Arrays.asList(expectedMenuItemsCP)),
							"Menu Items did not match. Present menu items were"
									+ Arrays.toString(MenuItemTexts.split("\n")));
		// Assert.assertEqualsNoOrder( MenuItemTexts.split("\n"),
		// expectedMenuItemsCP);

		rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);
	}

	public static boolean waitForElementToBeVisible(final By byElementToFind,
													long waitTime,
													Boolean waitForAjax)
	{

		try
		{
			Reporter.log("Waiting for element " + byElementToFind.toString()
							+ " to be visible" + "<br>");
			if (waitForAjax)
			{
				waitForAjaxRefresh();
			}
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			WebElement foundElement = wait.until(ExpectedConditions.visibilityOfElementLocated(byElementToFind));

			driver.manage().timeouts()
					.implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
			Reporter.log("Found visible Element: " + byElementToFind + "<br>");
			return true;
		}
		catch (TimeoutException ex)
		{
			Reporter.log("Failed to find visible element '" + byElementToFind
							+ "' after " + waitTime + " seconds." + "<br>");
			return false;
		}
	}

	public static boolean waitForElementToBeVisible(WebElement elementToFind,
													long waitTime,
													Boolean waitForAjax)
	{

		try
		{
			Reporter.log("Waiting for element to be visible" + "<br>");
			if (waitForAjax)
			{
				waitForAjaxRefresh();
			}
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			WebElement foundElement = wait.until(ExpectedConditions.visibilityOf(elementToFind));

			driver.manage().timeouts()
					.implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
			Reporter.log("Found visible Element" + "<br>");
			return true;
		}
		catch (TimeoutException ex)
		{
			Reporter.log("Failed to find visible element " + "" + "after "
							+ waitTime + " seconds." + "<br>");
			return false;
		}
	}

	public static boolean elementIsStale(WebElement elementToFind, long waitTime)
	{

		try
		{
			Reporter.log("Checking element is not stale" + "<br>");
			waitForAjaxRefresh();

			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			// returns false if the element is still attached to the DOM, true
			// otherwise.
			boolean foundElement = wait.until(ExpectedConditions.stalenessOf(elementToFind));

			driver.manage().timeouts()
					.implicitlyWait(implicitTimeout, TimeUnit.SECONDS);

			Reporter.log("Element is stale" + "<br>");
			return foundElement;
		}
		catch (TimeoutException ex)
		{
			Reporter.log("Element not stale " + "" + "after " + waitTime
							+ " seconds." + "<br>");
			return false;
		}
	}

	public static void checkReportingUserMenus(String generalUserCompanyName,
												String generalUserLastName)
	{
		// Note: Reporting Link is not visible to only 'Reporting' users in
		// On-Cue product
		String cssRightMainMenu = "ul#main-menu-right";
		String MenuItemTexts = "";

		String rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		String[] expectedRightMenuItems = {
											"Help",
											generalUserCompanyName + " "
													+ generalUserLastName };
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		Assert.assertTrue(waitForElementToExist(By.xpath("//div[contains(text(),'Welcome to LRS Connect')]"),
												read.getveryShortWaitTime(),
												true));

		clickOnProductOnMainScreen("Check Point");
		waitForElementToExist(	By.xpath("//div[(contains(text(),'On Cue')) and (contains(@class,'productLinkDiv'))]"),
								read.getmediumWaitTime(), true);
		String cssLeftMainMenu = "ul#main-menu-left";
		String dropDownMenu = "ul.dropdown-menu";
		// Need to force sleep as the page sometimes does not load completely
		// even after ajaxrefresh
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			Reporter.log("Exception while waiting for top menu after clicking on On Cue"
							+ "<br>");
			e.printStackTrace();
		}

		MenuItemTexts = helperFunctions.getText(By.cssSelector(cssLeftMainMenu));
		String[] expectedMenuItemsOC = { "Reporting" };
		try
		{
			Assert.assertEqualsNoOrder(	MenuItemTexts.split("\n"),
										expectedMenuItemsOC);
		}
		catch (AssertionError ex)
		{
			// 'Upgrade' option may also be visible in the left menu. Check if
			// the assertion error is due to that
			Reporter.log("Checking if Upgrade menu option is present along with Reporting option for On Cue"
							+ "<br>");
			String[] expectedMenuItemsOC_upgrade = { "Reporting", "Upgrade" };
			Assert.assertEqualsNoOrder(	MenuItemTexts.split("\n"),
										expectedMenuItemsOC_upgrade);
		}

		rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		String subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		String[] expectedSubMenuItems = { "User Settings", "Sign Out" };
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);

		switchProductThroughTopMenu("Table Tracker");
		waitForElementToExist(	By.xpath("//div[(contains(text(),'Table Tracker')) and (contains(@class,'productLinkDiv'))]"),
								read.getmediumWaitTime(), true);
		MenuItemTexts = helperFunctions.getText(By.cssSelector(cssLeftMainMenu));
		String[] expectedMenuItemsTT = { "Reporting" };
		// try
		// {
		Assert.assertEqualsNoOrder(	MenuItemTexts.split("\n"),
									expectedMenuItemsTT);
		// }
		// catch (AssertionError ex)
		// {
		// // 'Upgrade' option may also be visible in the left menu. Check if
		// // the assertion error is due to that
		// Reporter.log("Checking if Upgrade menu option is present along with Reporting option for Table Tracker"
		// + "\n");
		// String[] expectedMenuItemsTT_upgrade = { "Reporting", "Upgrade" };
		// Assert.assertEqualsNoOrder( MenuItemTexts.split("\n"),
		// expectedMenuItemsTT_upgrade);
		// }

		rightMenuItemTexts = helperFunctions.getText(By.cssSelector(cssRightMainMenu));
		Assert.assertEqualsNoOrder(	rightMenuItemTexts.split("\n"),
									expectedRightMenuItems);

		helperFunctions.click(By.cssSelector("a.dropdown-toggle"));
		subMenuItemTexts = helperFunctions.getText(By.cssSelector(dropDownMenu));
		Assert.assertEqualsNoOrder(	subMenuItemTexts.split("\n"),
									expectedSubMenuItems);

	}

	public static void actionClass_SendKeys(WebElement elem, Keys keyboardKEY)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(elem);
		actions.click();
		actions.sendKeys(keyboardKEY);
		actions.build().perform();
	}

	public static void actionClass_SendKeys(WebElement elem, String inputString)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(elem);
		actions.click();
		actions.sendKeys(inputString);
		actions.build().perform();
	}

	public static void actionClass_SendKeys(By elemBy, String inputString)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(elemBy));
		actions.click();
		actions.sendKeys(inputString);
		actions.build().perform();
	}

	public static String addSurvey()
	{
		click(By.cssSelector("button.btn.addProductBtn"));
		String automationSurveyName = "AutoSurvey" + randomNum(1, 10000);
		typeText(By.id("NEW_SURVEY"), automationSurveyName);
		click(By.cssSelector("button.btn.btn-primary.saveBtn"));
		Assert.assertTrue(waitForElementToBeVisible(By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
																+ "(contains(text(),'Survey Settings'))]"),
													read.getshortWaitTime(),
													true));

		Assert.assertEquals(driver.findElement(	By.cssSelector("span.selectboxit-text"))
									.getText(), "Draft",
							"Survey's initial status is not 'Draft'");
		return automationSurveyName;

	}

	public static void checkSurveyExists(By bySurvey)
	{
		Assert.assertTrue(waitForElementToBeVisible(bySurvey,
													read.getshortWaitTime(),
													true));

	}

	public static String editSurveyName(String surveyAdded)
	{
		By bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
								+ surveyAdded + "'))]");
		checkSurveyExists(bySurvey);
		click(bySurvey);
		Assert.assertTrue(waitForElementToBeVisible(By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
																+ "(contains(text(),'Survey Settings'))]"),
													read.getshortWaitTime(),
													true));
		By bySurveyNameInput = By.xpath("//input[@placeholder='Survey Name']");
		click(bySurveyNameInput);
		String surveyEdited = driver.findElement(bySurveyNameInput)
									.getAttribute("value") + "_edited";
		driver.findElement(bySurveyNameInput).clear();
		typeText(bySurveyNameInput, surveyEdited);
		click(By.cssSelector("button.btn.btn-primary.btnDone"));
		click(By.cssSelector("button.btn.modalConfirmBtn"));
		bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
							+ surveyEdited + "'))]");
		checkSurveyExists(bySurvey);
		return surveyEdited;
	}

	public static void changeSurveyStatus(String surveyAdded, String oldStatus,
											String newStatus)
	{
		String surveySelectXpath = "//a[(@class='surveyClass') and (contains(text(),'"
									+ surveyAdded
									+ "'))]/ancestor::tr[1]//span[@class='selectboxit-text']";

		helperFunctions.waitForElementToExist(	By.xpath(surveySelectXpath),
												read.getshortWaitTime(), true);

		helperFunctions.scrollTo(By.xpath(surveySelectXpath));

		String surveyStatusXpath = "//a[(@class='surveyClass') and (contains(text(),'"
									+ surveyAdded
									+ "'))]/ancestor::tr[1]//span[@data-val='"
									+ oldStatus.toUpperCase() + "']";
		By bySurveyStatus = By.xpath(surveyStatusXpath);
		click(bySurveyStatus);
		driver.findElement(	By.xpath(surveyStatusXpath
										+ "/ancestor::span[1]/following-sibling::ul[1]/li[@data-val='"
										+ newStatus.toUpperCase() + "']/a"))
				.click();
		Reporter.log("Changed Survey Status to: " + newStatus + "<br>");
	}

	public static String checkSurveyStatus(String surveyAdded)
	{
		By bySurveyStatus = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
										+ surveyAdded
										+ "'))]/ancestor::tr[1]//span[@class='selectboxit-text']");
		return getAttributeValue(bySurveyStatus, "data-val");
	}

	public static String checkSurveyQuesCharLimit(String surveyAdded)
	{
		By bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
								+ surveyAdded + "'))]");
		checkSurveyExists(bySurvey);
		click(bySurvey);
		waitForElementToBeVisible(	By.xpath("//*[(@class='mdDetailHeaderLabel name') and "
												+ "(contains(text(),'Survey Settings'))]"),
									read.getshortWaitTime(), true);

		click(By.cssSelector("button.btn.newModelBtn"));
		By byNewQ = By.id("questionText");
		click(byNewQ);
		String stringWith152Length = "";
		for (int i = 1; i <= 152; i++)
		{
			String newC = Character.toString((char) Integer.parseInt(randomNum(	65,
																				90)));
			stringWith152Length = stringWith152Length + newC;
		}
		String qString = stringWith152Length.substring(	0,
														stringWith152Length.length() - 2);

		if (read.getBrowser().equals("Safari"))
		{
			typeText(byNewQ, qString);
			// check maxlength attribute
			Assert.assertEquals(getAttributeValue(byNewQ, "maxlength"), "150");
		}
		else
		{
			typeText(byNewQ, stringWith152Length);
			Assert.assertEquals(getAttributeValue(byNewQ, "value"), qString);
		}
		return qString;
	}

	public static void validateYNBranchToOptionsAgainst(String validateAgainstText,
														String[] expectedBranches)
	{
		String branchXpath = "//span[(@id='label') and (text()='"
								+ validateAgainstText
								+ "')]/ancestor::tr[1]//select[@name='branchTo']";
		Select branchOptionsSelect = new Select(
												driver.findElement(By.xpath(branchXpath)));
		compareLists(expectedBranches, branchOptionsSelect, "textContent");

	}

	public static void deleteSurveyQues(String quesSecondaryLabel)
	{
		By byNewQAdded = By.xpath("//*[@id='mdMasterListItems']"
									+ "//div[(@class='mdMasterTableSecondaryLabel')"
									+ " and (contains(text(),'"
									+ quesSecondaryLabel + "'))]");
		click(byNewQAdded);
		click(By.cssSelector("button.btn.deleteModelBtn"));
		click(By.cssSelector("button.btn.btn-primary.btn-large.modalConfirmBtn"));
		Assert.assertTrue(	!waitForElementToBeVisible(	byNewQAdded,
														read.getveryShortWaitTime(),
														true),
							"Question Not Deleted!");

	}

	public static void verifyAllProductsInSequence()
	{

		clickOnProductOnMainScreen("On Cue");
		waitForAjaxRefresh();
		switchProductThroughTopMenu("Check Point");
		waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));
		switchProductThroughTopMenu("Table Tracker");
		waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));

		switchProductThroughTopMenu("Go to My Products");
		waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));
		switchProductThroughTopMenu("Explore LRS Products");
		waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//*[@id='selectedProduct']"),
																read.getveryShortWaitTime(),
																true));
		switchProductThroughTopMenu("Table Tracker");
		waitForAjaxRefresh();
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("users"),
																read.getveryShortWaitTime(),
																true));
		click(By.id("users"));

		// scrollToBottom();
	}

	public static void verifyAndAddUserWithProduct()
	{

		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("changeAcctOwnerLink"),
																read.getveryShortWaitTime(),
																true));
		click(By.id("lrsadminAccounts"));
		click(By.id("addAccount"));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.id("accountNameLbl"),
																read.getveryShortWaitTime(),
																true));
		// Verify Account Details
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.className("accountFieldset"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertEquals(driver.findElement(	By.cssSelector("legend.accountLegend.formFieldHeader"))
									.getText(), "Account Details");
		Assert.assertEquals(driver.findElement(	By.cssSelector("span.formElementName"))
									.getText(), "Account Name");
		Assert.assertEquals(driver.findElement(	By.xpath("//span[@class='formElementName' and text()='NetSuite ID']"))
									.getText(), "NetSuite ID");
		Assert.assertEquals(driver.findElement(	By.xpath("//table[@class='lrsTable']//div[text()='Account Owner']"))
									.getText(), "Account Owner");
		Assert.assertEquals(driver.findElement(	By.xpath("//span[@class='formElementName' and text()='Email Address']"))
									.getText(), "Email Address");
		Assert.assertEquals(driver.findElement(	By.cssSelector("div.padding-bottom-20.formFieldHeader"))
									.getText(), "Product Plans");
		Assert.assertEquals(driver.findElement(	By.cssSelector("#cellON_CUE_RESTAURANT"))
									.getText(), "On Cue Restaurants");
		Assert.assertEquals(driver.findElement(	By.cssSelector("#cellTABLE_TRACKER"))
									.getText(), "Table Tracker");
		Assert.assertEquals(driver.findElement(	By.cssSelector("#cellCHECK_POINT"))
									.getText(), "Check Point");
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.cssSelector("button.btn.cancelBtn"),
																read.getveryShortWaitTime(),
																true));
		Assert.assertTrue(helperFunctions.waitForElementToExist(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"),
																read.getveryShortWaitTime(),
																true));
		// Entering Account Name
		typeText(	By.cssSelector("input.input-block-level.textBox"),
					"AutomationName" + helperFunctions.randomNum(1, 1000000));

		// Entering Email Address
		typeText(	By.xpath("//input[@name ='email' and @placeholder='Email Address']"),
					"Automationmail" + helperFunctions.randomNum(1, 100000)
							+ "@gmail.com");

		// Checking for User should select At least One Product. Here No Product
		// is selected
		click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));
		String checkMessage = driver.findElement(By.xpath("//body/div[2]"))
									.getText();
		Assert.assertEquals(checkMessage, "Select At least one product");
		// Check for One Product completed above

		click(By.cssSelector("div.selectric"));
		waitForElementToExist(	By.cssSelector("div.selectricItems"),
								read.getmediumWaitTime(), true);
		// Selected Active
		click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));
		// WEB-1390 : Selecting Another Product (Table Tracker)

		click(By.xpath("//div[@class='selectric'][1]"));
		click(By.xpath("//*[@class='selectricItems']/ul/li[@class='last']"));

		// Clicking on Save tab
		click(By.xpath("//div[@class='mdDetailFooterRight']/button[@class='btn btn-primary saveModelBtn']"));

		// Verifying New Account Created

		String TextForAccountVerify = driver.findElement(	By.id("accountNameLbl"))
											.getText();
		Reporter.log("Value for header" + TextForAccountVerify + "<br>");
		// click(By.id("searchImage"));
		typeText(By.id("mdSearchBox"), TextForAccountVerify);
		// click(By.id("mdSearchWidgetIcon"));
		typeText(driver.findElement(By.id("mdSearchBox")), Keys.ENTER);
		String CheckForPending = driver.findElement(By.cssSelector("div.mdMasterTableSecondaryLabel"))
										.getText();
		Assert.assertEquals(CheckForPending, "Pending");

	}

	public static void scrollTo(By byNewStatus)
	{
		final JavascriptExecutor jExecutor = (JavascriptExecutor) (driver instanceof JavascriptExecutor ? driver
				: null);
		jExecutor.executeScript("arguments[0].scrollIntoView();",
								driver.findElement(byNewStatus));
		try
		{
			Thread.sleep(1500);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("scrolled to: " + byNewStatus.toString() + "<br>");
	}

	public static void deleteSurvey(String surveyAdded)
	{
		By bySurvey = By.xpath("//a[(@class='surveyClass') and (contains(text(),'"
								+ surveyAdded + "'))]");
		checkSurveyExists(bySurvey);
		click(By.xpath("//a[(@class='surveyClass') and (contains (text(), '"
						+ surveyAdded
						+ "'))]//ancestor::tr//a[(@class='surveyClassRemove') and (contains(text(), 'Remove'))]"));
		click(By.xpath("//span[(@name='confirmButtonLabel') and (contains(text(), 'Delete'))]"));
		waitForAjaxRefresh();
	}

	public static void waitForLoginPage()
	{
		By byLoginButton = By.id("btnLoginSubmit");
		boolean submitBtnExist = waitForElementToExist(	byLoginButton,
														read.getmediumWaitTime(),
														true);
		if (!submitBtnExist)
		{
			try
			{
				signOut();
			}
			catch (Exception e)
			{
				System.out.println("signOut threw exception..");
			}
			finally
			{
				driver.get(read.getURL());
				waitForElementToExist(	byLoginButton,
										read.getmediumWaitTime(),
										true);
			}
		}

	}

}
