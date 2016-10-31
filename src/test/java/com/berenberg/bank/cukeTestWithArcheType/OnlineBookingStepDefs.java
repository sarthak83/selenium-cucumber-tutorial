package com.berenberg.bank.cukeTestWithArcheType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.Assert;


public class OnlineBookingStepDefs {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@Before("@OnlineBooking")
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/sarthakdayanand/Documents/Automation/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://better.legendonlineservices.co.uk/enterprise/account/login");
		driver.manage().window().maximize();
		}
	
	
	@Given("^when I am on online bookings home page$")
	public void when_I_am_on_online_bookings_home_page() throws Throwable {
	    
	}

	@When("^I login and navigate to manage my bookings$")
	public void i_login_and_navigate_to_manage_my_bookings() throws Throwable {
		
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_Email")));
		driver.findElement(By.id("login_Email")).sendKeys("sarthak.kc@gmail.com");
		driver.findElement(By.id("login_Password")).sendKeys("Badmoboys2016");
		driver.findElement(By.id("login")).click();
	}

	@When("^I select the desired date and time$")
	public void i_select_the_desired_date_and_time() throws Throwable {
		//System.out.println("i_select_the_desired_date_and_time");
		wait = new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Make a Booking")));
	
		//LinkText
		//driver.findElement(By.linkText("Make a Booking")).click();
		//Partial Link Text
		driver.findElement(By.partialLinkText("Make a")).click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='club']")));
		List<WebElement> e = driver.findElements(By.xpath("//input[@name='club']"));
		Thread.sleep(2000);
		for(WebElement element:e) {
			//System.out.println(element.isSelected());
			if(element.isSelected()==true)  //If any of the checkbox is selected, de select it. (eg, ironmonger lane is checked by default
				element.click();
		}
		driver.findElement(By.xpath(".//input[@value='300']")).click();  //Click checkbox for Finsbury FC
			//System.out.println(element.getText().toString());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='373']"))); //Click radio button "Other Activities"
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//input[@value='373']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='468']"))); //Select Badminton
		driver.findElement(By.xpath(".//input[@value='468']")).click();
		
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("bottomsubmit")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='bottomsubmit']")));
		driver.findElement(By.id("bottomsubmit")).click();
		//Thread.sleep(5000);
		
		//switch to frame
		driver.switchTo().frame("TB_iframeContent");
		Thread.sleep(1000);
		//System.out.println(driver.getTitle());
		//Assert.assertTrue(driver.getTitle() == "Online Member Services");
		
		//Scroll to the bottom of the page using JavascriptExecutor
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		String day = simpleDateformat.format(new Date());
	    System.out.println("DAY:::"+day);
		int count = 0;
		WebElement anchor = null;
		List<WebElement> days = driver.findElements(By.xpath(".//*[@class='TextMembers']"));
		for(WebElement d:days) {
			//System.out.println(d.getAttribute("innerText"));
			String s = d.getAttribute("innerText");
			
			
			
			
			if(s.contains(day)) {
				System.out.println(s);
				count++;
			}
			if(count == 2) {
				System.out.println("Second occurance"+"     "+s);
				anchor = d;
				break;
			}
			
			
	
		}
		System.out.println("--------------Day to be booked----------------");
		
		//System.out.println(anchor.getTagName());
		System.out.println(anchor.getAttribute("innerText"));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='slot207022']")));
		//WebElement inner = driver.findElement(By.xpath(".//*[@id='slot203153']"));
		
		
		List<WebElement> linner = anchor.findElements(By.xpath("..//following-sibling::*"));
		for(WebElement ll:linner) {
			if(ll.getAttribute("innerText").contains("13:00")) {
			//System.out.println("--------------Slot -----------------");
			List<WebElement> av = ll.findElements(By.xpath("child::*"));
			for(WebElement avail:av){
				//System.out.println("Childs tagName::"+avail.getTagName());
				//System.out.println(avail.findElement(By.xpath(".//following-sibling::a[2]")).getText());
				System.out.println("----------Available---------");
				if(avail.findElements(By.xpath(".//following-sibling::a[2]")).size()!=0) {
					//avail.findElement(By.xpath(".//following-sibling::a[2]"));
				//if(avail.findElement(By.xpath("..//following-sibling::a[1]")) != null)
					//System.out.println(avail.getAttribute("innerText"));
				    System.out.println(avail.findElement(By.xpath(".//following-sibling::a[2]")).getText());
				    Thread.sleep(1000);
				    avail.findElement(By.xpath(".//following-sibling::a[2]")).click();
				    break;
				}
			}
		
			/*ll.findElements(By.xpath("child::*"));
			if(ll.getAttribute("innerText").contains("18:00")) { 
				System.out.println(ll.getAttribute("innerText"));
				ll.findElement(By.xpath("following::a[contains(@id,'slot')]"));
				System.out.println("--------------Available-----------------");
				//System.out.println(ll.getAttribute("innerText"));
				System.out.println(ll.getTagName());
			}*/
			break;
			}
			//available.click();
		}
		
		Thread.sleep(1000);
		//iframe to select court and say "Add to Basket"
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='selectResourceLocation']/div/div/fieldset/div[1]/a[1]")));
		driver.findElement(By.xpath(".//*[@id='selectResourceLocation']/div/div/fieldset/div[1]/a[1]")).click();
		
		Thread.sleep(2000);
		/* 
		 Choose another or OK  -- OK will continue to checkout. 
		 html/body/div[2]/div/div/fieldset/div/a[1]  -- Choose another
		 html/body/div[2]/div/div/fieldset/div/a[2]  -- OK. 
		 */
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath(".//*[@id='TB_window']/iframe")));
		//System.out.println(driver.getPageSource());
		System.out.println("--------iFrame------------");
		//System.out.println(driver.findElement(By.tagName("iframe")).toString());
		//System.out.println(driver.getCurrentUrl());
		WebElement frame = driver.findElement(By.tagName("iframe"));
		System.out.println("--------iFrame page source------------");
		System.out.println(frame.getAttribute("innerHTML"));
		System.out.println("--------iFrame page source------------");
	
		Thread.sleep(2000);
		//WebElement frame = driver.findElement(By.xpath("/html/body/div[2]/div/div/fieldset/p[1]"));
	
		System.out.println(frame.getTagName());
		System.out.println("SourceAtribute::"+frame.getAttribute("src"));
		List<WebElement> buttons = frame.findElements(By.xpath("/html/body/div"));
		for(WebElement div: buttons) {
			System.out.println("--------innerHTML of div----------");
		System.out.println("title innerHTML(web bookings)::"+div.getAttribute("innerHTML"));
		}
		
		
		WebElement nodes = frame.findElement(By.xpath("/html/body/div[2]"));
			System.out.println("----------------");
			System.out.println(nodes.getTagName());
			System.out.println(nodes.getAttribute("id"));
			//System.out.println(nodes.getAttribute("innerText"));
			//System.out.println(nodes.getAttribute("innerHTML"));
			//System.out.println(nodes.getClass());
			
	
		
		
		//System.out.println(driver.getPageSource());
		
		//driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		//driver.switchTo().frame("TB_iframeContent");
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body/div[2]/div/div/fieldset/div/a[2]")));
		//driver.findElement(By.xpath("/html/body/div[2]/div/div/fieldset/div/a[2]")).click();
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='formSubmit']/a[2]")));
		//driver.findElement(By.xpath(".//*[@class='formSubmit']/a[2]")).click();
		
		
		
		
		
		
		
		
		//WebElement inner = anchor.findElement(By.xpath("..//following-sibling::div[last()]"));
		//System.out.println("-------------inner------------------");
		//System.out.println(inner.getAttribute("innerText"));
		//System.out.println(inner.getTagName());
		
		
		
	}

	@When("^give credit card details and checkout$")
	public void give_credit_card_details_and_checkout() throws Throwable {
		
		//We are on basket summary page now. 
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnPayNow")));
		driver.findElement(By.id("btnPayNow")).click();
		
		//we are on payment page now. Where we give CC details. 
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		driver.findElement(By.id("panInput")).sendKeys("");
	
		Select month = new Select(driver.findElement(By.id("ExpiryDateMonth")));
		month.selectByValue("03");
		
		Select year = new Select(driver.findElement(By.id("ExpiryDateYear")));
		year.selectByVisibleText("2017");
		
		//CVV code
		driver.findElement(By.id("csc")).sendKeys("");
		driver.findElement(By.id("cardholdername")).sendKeys("");
		
		driver.findElement(By.id("btnPayNow")).click();
		
	}

	@Then("^I should get a confirmation page saying \"([^\"]*)\"$")
	public void i_should_get_a_confirmation_page_saying(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@After("@OnlineBooking")
	public void tearDown() {
		
		//driver.close();
	}

	

}
