package org.example.tests;

import org.example.base.BaseDriver;
import org.example.pages.SeleniumDevPage;
import org.testng.annotations.*;


public class ChromeTests extends BaseDriver {

    @BeforeMethod
    public void launchChrome() {
        setupChromedriver();
    }

    @Test
    public void fillTextboxAndPasswordTest(){
        SeleniumDevPage seleniumDevPage = new SeleniumDevPage();

        seleniumDevPage.verifyMainPageTitle();
        seleniumDevPage.fillTextBox();
        seleniumDevPage.fillPassword();
    }

    @Test
    public void dropdownsTest(){
        SeleniumDevPage seleniumDevPage = new SeleniumDevPage();

        seleniumDevPage.verifyMainPageTitle();
        seleniumDevPage.checkNumbersDropdown();
        seleniumDevPage.selectCityDropdown();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
