package model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final static By NAME_FOR_REGISTRATION_INPUT = By.xpath(".//label[text() = 'Имя']");
    private final static By INPUT_NAME = By.xpath(".//div[@class = 'input pr-6 pl-6 input_type_text input_size_default input_status_active']/input");

    private final static By EMAIL_FOR_REGISTRATION_INPUT = By.xpath(".//label[text() = 'Email']");
    private final static By INPUT_EMAIL = By.xpath(".//div[@class = 'input pr-6 pl-6 input_type_text input_size_default input_status_active']/input");
    private final static By PASSWORD_FOR_REGISTRATION_INPUT = By.xpath(".//label[text() = 'Пароль']");
    private final static By INPUT_PASSWORD = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6']/div/div/input[@type='password']");
    private final static By REGISTRATION_BUTTON = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final static By SMALL_PASSWORD_ERROR_TEXT = By.xpath(".//p[@class = 'input__error text_type_main-default']");
    private final static String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final static String XPATH_LOGIN_FROM_REGISTRATION_PAGE_BUTTON = ".//a[@class = 'Auth_link__1fOlj']";
    private WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public static String getLoginFromRegistrationPage() {
       return REGISTRATION_PAGE_URL;
    }
    public static String getXpathLoginFromRegistrationPageButton() {
        return XPATH_LOGIN_FROM_REGISTRATION_PAGE_BUTTON;
    }
    public String getSmallPasswordErrorText(WebDriver driver){
        String textError = driver.findElement(SMALL_PASSWORD_ERROR_TEXT).getText();
        return textError;
    }
    public void waitLoadSmallTextError() {
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(SMALL_PASSWORD_ERROR_TEXT));
    }
    public void inputRegistrationDataAndPressButton(WebDriver driver,String userName,String userEmail,String userPassword ){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(NAME_FOR_REGISTRATION_INPUT));
        driver.findElement(NAME_FOR_REGISTRATION_INPUT).click();
        driver.findElement(INPUT_NAME).sendKeys(userName);
        driver.findElement(EMAIL_FOR_REGISTRATION_INPUT).click();
        driver.findElement(INPUT_EMAIL).sendKeys(userEmail);
        driver.findElement(PASSWORD_FOR_REGISTRATION_INPUT).click();
        driver.findElement(INPUT_PASSWORD).sendKeys(userPassword);
        driver.findElement(REGISTRATION_BUTTON).click();
    }
}
