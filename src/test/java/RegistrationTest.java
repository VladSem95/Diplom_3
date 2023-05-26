import api.UserClient;
import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import model.LoginPage;
import model.HomePage;
import model.RegistrationPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.Matchers.containsString;
public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private UserClient userClient;
    private String userName;
    private String userEmail;
    private String userPassword;


    @Before
    public void setUp(){
        Faker faker = new Faker();
        userName = faker.name().firstName() + faker.name().lastName();
        userEmail = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        userPassword = faker.toString();
        userClient = new UserClient();
        //Настройка для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\vssemenov\\Desktop\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Настройка для Яндекс браузера
        //options.setBinary("C:\\Users\\vssemenov\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        registrationPage = new RegistrationPage(driver);
    }
    @After
    public void cleanUp() {
            try {
                ValidatableResponse login = userClient.login(userEmail, userPassword);

                String clientBearerToken = login.extract().path("accessToken");
                clientBearerToken = clientBearerToken.replace("Bearer ", "");
                userClient.delete(clientBearerToken);
                driver.quit();
            }catch(NullPointerException Exception){
                driver.quit();
        }
    }
    @Test
    public void checkRegistrationSuccessTest() {

        LoginPage loginPage = new LoginPage(driver);

        driver.get(HomePage.openHomePage());
        HomePage.clickLogin(driver);
        loginPage.clickRegistration(driver);
        registrationPage.inputRegistrationDataAndPressButton(driver, userName, userEmail, userPassword);
        loginPage.waitLoadInputButton();
        MatcherAssert.assertThat(loginPage.getLoginTextButton(driver),containsString("Войти"));

    }
    @Test
    public void checkRegistrationWithIncorrectPassword() {
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);

        userPassword = RandomStringUtils.randomAlphanumeric(5);

        driver.get(HomePage.openHomePage());
        HomePage.clickLogin(driver);
        loginPage.clickRegistration(driver);
        registrationPage.inputRegistrationDataAndPressButton(driver, userName, userEmail, userPassword);
        registrationPage.waitLoadSmallTextError();
        MatcherAssert.assertThat(registrationPage.getSmallPasswordErrorText(driver),containsString("Некорректный пароль"));
    }
}

