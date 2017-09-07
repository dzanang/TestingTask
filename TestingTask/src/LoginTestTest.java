import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTestTest {

	private WebDriver driver;
	private String appUrl;

	@Before
	public void setUp() throws Exception {
		// Tried it both with firefox and chrome browser. Separate drivers need to be
		// downloaded for both of these browsers
		// since Selenium doesn't have native support for them.
		System.setProperty("webdriver.chrome.driver", "C:\\eclipse\\chromedriver.exe"); // Chrome WebDriver
		System.setProperty("webdriver.gecko.driver", "C:\\eclipse\\geckodriver.exe"); // Firefox WebDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		appUrl = "https://www.betx.sk";
	}

	@Test
	public void test() throws InterruptedException {

		// launch the firefox browser and open the application url
		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000); // sleep is required for the page to load so the script can move on with
							// executing further steps

		// login into the site
		driver.findElement(By.className("login-z-index")).click();
		driver.findElement(By.id("userName")).sendKeys("nisim22");
		driver.findElement(By.id("loginPassword")).sendKeys("Nisim1");
		driver.findElement(By.xpath(
				"/html/body/div/div/div[2]/div/div[2]/div/div/header[1]/div/nav[3]/li[1]/div/div/div[1]/div[1]/form/button"))
				.click();

		// create a ticket
		// needs several sleep timeouts, due to web-page loading slowly.
		Thread.sleep(5000);

		// finding pairs in American Football Category
		driver.findElement(By.linkText("Americký futbal")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#accordion > div:nth-child(7) > div.angular-animate.category-collapse.collapse.in > div > div:nth-child(2) > div.position-relative"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#accordion > div:nth-child(7) > div.angular-animate.category-collapse.collapse.in > div > div:nth-child(2) > div.category-sub > div > div"))
				.click();
		Thread.sleep(2000);

		// selecting pairs and clicking on odds and projected outcomes
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[6]/tbody/tr/td[2]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[12]/tbody/tr/td[2]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[13]/tbody/tr/td[2]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[10]/tbody/tr/td[3]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[4]/tbody/tr/td[2]"))
				.click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id=\"offerHolder\"]/div/div/div[1]/div[1]/table/tbody/tr/td/table[8]/tbody/tr/td[3]"))
				.click();
		Thread.sleep(2000);

		// Drop down menu for money investment
		driver.findElement(By.id("ticket-btn")).click();
		Thread.sleep(2000);

		// Selecting the amount of money to bet
		driver.findElement(By.xpath("//*[@id=\"payin-amount\"]/li[3]")).click();

		// Completing the ticket
		driver.findElement(By.cssSelector("span[title='Podať tiket']")).click();
		Thread.sleep(6000); // needs long sleep due to longer load time between ticket submit and
							// confirmation pop-up

		// Assigning a web element to a variable for easier usage
		WebElement optElm = driver.findElement(By.className("ticket-validation-container"));

		WebElement okBtn = driver.findElement(By.xpath("//*[@id=\"ticket-errors-container\"]/div[2]/div[2]/button"));

		// Checking if an element is displayed on the page. Depending on the outcome two
		// if statements execute
		if (optElm.isDisplayed()) { // Confirms the submitted ticket
			System.out.println("Yes Button found");
			driver.findElement(By
					.xpath("//*[@id=\"ticket-positioning-container\"]/div/div[2]/div[2]/div/div/div/div[2]/button[1]"))
					.click();
		} else if (okBtn.isDisplayed()) { // Clicks OK button after we've been notified that the same ticket already
											// exists
			System.out.println("OK Button found");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"ticket-errors-container\"]/div[2]/div[2]/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("ticket-remove-bets")).click(); // removes the ticked since it's a duplicate
		}

		// close the web browser
		// disabled this so the results of the test can remain visible after execution
		// driver.close();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test script executed successfully.");

		// terminate the program
		// System.exit(0);
	}

}
