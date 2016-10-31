/**
 * 
 */
package com.berenberg.bank.cukeTestWithArcheType;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author sarthakdayanand
 *
 */
public class StepDefs {
	
	WebDriver driver = null;
	
	@Before("@FaceBookTest")
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/sarthakdayanand/Documents/Automation/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		}
	
	
	@Given("^I am on facebook login page$")
	public void i_am_on_facebook_login_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//System.setProperty("webdriver.firefox.bin", "/Applications/IBM Firefox.app/Contents/MacOS/firefox-bin");
	    //WebDriver driver = new FirefoxDriver();
	    //System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
		
		
	    
	}

	@When("^I enter username as \"([^\"]*)\"$")
	public void i_enter_username_as(String userName) throws Throwable {
		System.out.println(userName);
		By userame = By.id("email");
		WebElement username = driver.findElement(userame);
	    username.sendKeys(userName);	    
	}

	@And("^I enter password as \"([^\"]*)\"$")
	public void i_enter_password_as(String pwd) throws Throwable {
		System.out.println(pwd);
		By password = By.xpath(".//*[@id='pass']");
		WebElement pass = driver.findElement(password);
		pass.sendKeys(pwd);
		driver.findElement(By.id("u_0_n")).click();
		
	}

	@Then("^I get an error page$")
	public void i_get_an_error_page() throws Throwable {
	    if(driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110"))
	    	System.out.println("test case 1: Pass");
	    else
	    	System.out.println("test case 1: Fail");
	}

	@But("^I am served with relogin page$")
	public void i_am_served_with_relogin_page() throws Throwable {
		if(driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/login.php?login_attempt=1&lwv=110"))
	    	System.out.println("Test case 2: Pass");
	    else
	    	System.out.println("Test case 2: Fail");
	}
	
	@Given("^the following values:$")
	public void the_following_values(DataTable table) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		List<List<String>> data = table.raw();
		System.out.print(data.get(0).get(0)  +  "        ");
		System.out.println(data.get(1).get(0));
		driver.findElement(By.id("u_0_1")).sendKeys(data.get(0).get(1));
		driver.findElement(By.id("u_0_3")).sendKeys(data.get(1).get(1));
		driver.findElement(By.id("u_0_5")).sendKeys(data.get(2).get(1));
		driver.findElement(By.id("u_0_8")).sendKeys(data.get(3).get(1));
		driver.findElement(By.id("u_0_a")).sendKeys(data.get(4).get(1));
		
		Select dropdown= new Select(driver.findElement(By.id("day")));
		dropdown.selectByValue(data.get(5).get(1));
		new Select (driver.findElement(By.id("month"))).selectByIndex(1);
		new Select (driver.findElement(By.id("year"))).selectByIndex(1);
		driver.findElement(By.id("u_0_e")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("u_0_i")).click();
		Thread.sleep(2000);
			
	}

	@Then("^user registration should be unsuccessful$")
	public void user_registration_should_be_unsuccessful() throws Throwable {
	    if(driver.getCurrentUrl().equalsIgnoreCase("https://www.facebook.com/"))
	    	System.out.println("Testcase PASS");
	    else
	    	System.out.println("Testcase FAIL");
	}
	
	@After("@FaceBookTest")
	public void tearDown() {
		driver.close();
	}

}
