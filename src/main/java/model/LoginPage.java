package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private final static By REGISTRATION_FROM_LOGIN_PAGE = By.className("Auth_link__1fOlj");
    private final static By INPUT_BUTTON = By.xpath(".//button[text() = 'Войти']");
    private final static By EMAIL_FOR_LOGIN_INPUT = By.xpath(".//label[text() = 'Email']");
    private final static By INPUT_EMAIL = By.xpath(".//div[@class = 'input pr-6 pl-6 input_type_text input_size_default input_status_active']/input");
    private final static By PASSWORD_FOR_LOGIN_INPUT = By.xpath(".//label[text() = 'Пароль']");
    private final static By INPUT_PASSWORD = By.xpath(".//fieldset[@class = 'Auth_fieldset__1QzWN mb-6']/div/div/input[@type='password']");
    private final static By LOGIN_BUTTON = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private final static By LOGIN_TEXT = By.xpath(".//h2[text()='Вход']");



    private WebDriver driver;
    public void clickRegistration(WebDriver driver) {
        driver.findElement(REGISTRATION_FROM_LOGIN_PAGE).click();
    }
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public By getLoginText(){
        return LOGIN_TEXT;
    }
    public String getLoginTextButton(WebDriver driver) {
        String textButton = driver.findElement(INPUT_BUTTON).getText();
        return textButton;
    }
    public void openAuthorizationPage(){
        driver.get(LOGIN_PAGE_URL);
    }

    public void waitLoadInputButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(INPUT_BUTTON));
    }
    public void inputLoginDataAndPressButton(WebDriver driver,String userEmail,String userPassword ){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FOR_LOGIN_INPUT));
        driver.findElement(EMAIL_FOR_LOGIN_INPUT).click();
        driver.findElement(INPUT_EMAIL).sendKeys(userEmail);
        driver.findElement(PASSWORD_FOR_LOGIN_INPUT).click();
        driver.findElement(INPUT_PASSWORD).sendKeys(userPassword);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
