package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjective.AccountPage;
import pageobjective.LandingPage;
import pageobjective.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	public WebDriver driver;
	Logger log;

	@Test(dataProvider = "getLogindetails")
	public void login(String email, String password, String Expectedresult) throws IOException {

		LandingPage landingpage = new LandingPage(driver);
		landingpage.myAccountDropdown().click();
		log.debug("my account dropdown open");
		landingpage.loginoption().click();

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailaddressfield().sendKeys(email);
		log.debug("mail id entered");
		loginpage.passwordfield().sendKeys(password);
		log.debug("password entered");
		loginpage.loginbutton().click();
		log.debug("log in button click");

		AccountPage accountpage = new AccountPage(driver);
		log.debug("account page open");

		String Actualresult = null;

		try {
			if (accountpage.Edityouraccountinformation().isDisplayed()) {

				Actualresult = "Successfull";
			}
		} catch (Exception e) {
			Actualresult = "failure";
		}
		Assert.assertEquals(Actualresult, Expectedresult);

	}

	@BeforeMethod
	public void openapplicationurl() throws IOException {

		log = LogManager.getLogger(LoginTest.class.getName());
		driver = intializeDriver();
		log.debug("browser launched");
		driver.get(prop.getProperty("url"));
		log.debug("url launched");

	}

	@AfterMethod

	public void clouser() {

		driver.close();
		log.debug("browser got closed");
	}

	@DataProvider

	public Object[][] getLogindetails() {

		Object[][] data = { { "arun.selenium@gmail.com", "Second@123", "Successfull" },
				{ "dummy@gmail.com", "Second@15", "failure" } };
		return data;

	}

}
