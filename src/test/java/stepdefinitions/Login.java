package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjective.AccountPage;
import pageobjective.LandingPage;
import pageobjective.LoginPage;
import resources.Base;

public class Login extends Base {
	WebDriver driver;
	LandingPage landingpage;
	LoginPage loginpage;
	AccountPage accountpage;

	
	@Given("^Open any Browser$")
    public void open_any_browser() throws IOException  {
		
	 driver = intializeDriver();
		
		
	}
	 @And("^Navigate to Login page$")
	    public void navigate_to_login_page() throws InterruptedException {
		 driver.get(prop.getProperty("url"));
		  landingpage = new LandingPage(driver);
			landingpage.myAccountDropdown().click();
			landingpage.loginoption().click();
			Thread.sleep(3000);
				
	    }
	 

    @When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
    public void user_enters_username_as_something_and_password_as_something_into_the_fields(String strArg1, String strArg2, CharSequence email, CharSequence password) {
    	 loginpage = new LoginPage(driver);
		loginpage.emailaddressfield().sendKeys(email);
		loginpage.passwordfield().sendKeys(password);
    
    }
    
    @And("^User clicks on Login button$")
    public void user_clicks_on_login_button() {
    	loginpage.loginbutton().click();
    	
    	
    }

    @Then("^Verify user is able to successfully login$")
    public void verify_user_is_able_to_successfully_login() {
    	
    	 accountpage = new AccountPage(driver);
    	
    	Assert.assertTrue(accountpage.Edityouraccountinformation().isDisplayed());
    	
    	
    }

    @After
    
    public void closebrowser() {
    	
    	driver.close();
    }
   

   

	

}
