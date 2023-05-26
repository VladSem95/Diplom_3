import api.UserClient;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import model.LoginPage;
import model.HomePage;
import model.PersonalAreaPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutUserTest {
    private WebDriver driver;
    private UserClient userClient;
    private Faker faker;
    private String userEmail;
    private String userPassword;
    private LoginPage loginPage;
    private HomePage homePage;
    private PersonalAreaPage personalAreaPage;
    @Before
    public void setUp(){
        faker = new Faker();
        String userName = (faker.name().firstName() + faker.name().lastName());
        userEmail = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        userPassword = RandomStringUtils.randomAlphanumeric(6);

        userClient = new UserClient();
        userClient.create(userEmail,userPassword, userName);
        //Настройка для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\vssemenov\\Desktop\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Настройка для Яндекс браузера
        //options.setBinary("C:\\Users\\vssemenov\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        personalAreaPage = new PersonalAreaPage(driver);
        homePage = new HomePage(driver);
    }
    @After
    public void cleanUp() {
        ValidatableResponse login = userClient.login(userEmail, userPassword);
        String bearerToken = login.extract().path("accessToken");
        userClient.delete(bearerToken);
        driver.quit();
    }
    @Test
    public void checkLogoutUserTest(){
        driver.manage().window().maximize();
        loginPage.openAuthorizationPage();
        loginPage.inputLoginDataAndPressButton(driver,userEmail,userPassword);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(homePage.getXpathCheckoutButtonText(driver)));
        driver.findElement(By.xpath(homePage.getXpathPersonalAreaButton())).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(personalAreaPage.getXpathAccountText()));
        driver.findElement(By.xpath(personalAreaPage.getXpathLogoutUser())).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(loginPage.getLoginText()));
        String loginText = driver.findElement(loginPage.getLoginText()).getText();
        Assert.assertEquals("Вход",loginText);
    }
}
