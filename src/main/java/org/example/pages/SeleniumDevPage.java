package org.example.pages;

import org.example.base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SeleniumDevPage extends BaseDriver {


    SoftAssert softAssert = new SoftAssert();

    //region elements by id
    static final String messageId = "message";
    //endregion

    //region elements by Name
    static final String textBoxName = "my-text";
    static final String disabledInputName = "my-disabled";
    static final String dropdownName = "my-select";
    static final String fileSelectName = "my-file";
    static final String passwordFieldName = "my-password";
    //endregion

    //region elements by CSS Selector
    static final String submitButtonCssSelector = "button";
    static final String textAreaCssSelector = "textarea";
    //endregion

    //region elements by XPath
    static final String dropdownInputXpath = "//input[contains(@placeholder,\"Type to search...\")]";
    //endregion

    //region other elements
    static final String fileLocation = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\doge.gif";
    //endregion

    public void verifyMainPageTitle(){
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        String title = driver.getTitle();

        softAssert.assertEquals(title,"Web form");
    }

    public void fillTextBox(){
        WebElement textBox = driver.findElement(By.name(textBoxName));
        WebElement textArea = driver.findElement(By.cssSelector(textAreaCssSelector));

        textBox.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                " incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        textArea.sendKeys("Some sample text to be put in the textarea");
    }

    public void fillPassword(){
        WebElement passwordField = driver.findElement(By.name(passwordFieldName));

        passwordField.sendKeys("secretPa55w0rd");
        softAssert.assertEquals(passwordField.getText(),"secretPa55w0rd");
    }

    public void checkDisabledInput(){
        WebElement disabledInput = driver.findElement(By.name(disabledInputName));

        assertEquals(disabledInput.getText(),"");
    }

    public void checkNumbersDropdown(){
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.name(dropdownName)));
        Select drp = new Select(driver.findElement(By.name(dropdownName)));

        drp.selectByValue("2");
        softAssert.assertEquals(drp.getAllSelectedOptions(),"2");
        drp.selectByIndex(1);
        softAssert.assertEquals(drp.getAllSelectedOptions(),"1");
        String option = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/label[1]/select/option[2]")).getText() ;
        assertEquals(option,"One");
    }

    public void selectCityDropdown(){
        WebElement dropdownInput = driver.findElement(By.xpath(dropdownInputXpath));
        WebElement dropdownOptions = driver.findElement(By.xpath("//datalist/option[contains(@value,\"New York\")]"));

        dropdownInput.sendKeys("New York");
        assertEquals(dropdownOptions.getAttribute("value"),"New York");
    }

    public void chooseFile(){
        WebElement fileSelector = driver.findElement(By.name(fileSelectName));

        fileSelector.sendKeys(fileLocation);
        assertEquals(fileSelector.getText(), fileLocation);
    }

    public void submitForm(){
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        submitButton.click();
    }

    public void checkFormResult(){
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id(messageId)));

        assertEquals(message.getText(),"Received!");
    }

}