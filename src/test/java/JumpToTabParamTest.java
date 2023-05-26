import model.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class JumpToTabParamTest {
    private WebDriver driver;

    private final By sectionConstructorTab;
    private final By sectionName;
    private final By neighboringSection;
    private final String expectedName;


    public JumpToTabParamTest(By sectionConstructorTab, By sectionName, String expectedName, By neighboringSection) {
        this.sectionConstructorTab = sectionConstructorTab;
        this.sectionName = sectionName;
        this.neighboringSection = neighboringSection;
        this.expectedName = expectedName;
    }


    @Parameterized.Parameters
    public static Object[][] getTestParameters(){
        return new Object[][]{
                {HomePage.getXpathTabBuns(),HomePage.getXpathSectionBuns(),"Булки",HomePage.getXpathTabSauces()},
                {HomePage.getXpathTabSauces(),HomePage.getXpathSectionSauces(),"Соусы",HomePage.getXpathTabFillings()},
                {HomePage.getXpathTabFillings(),HomePage.getXpathSectionFillings(),"Начинки",HomePage.getXpathTabSauces()}
        };
    }
    @Before
    public void setUp(){
        //Настройка для Яндекс браузера
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\vssemenov\\Desktop\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //Настройка для Яндекс браузера
        //options.setBinary("C:\\Users\\vssemenov\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
    }
    @After
    public void cleanUp(){
         driver.quit();
    }
    @Test
    public void checkJumpToSectionTest(){
        driver.manage().window().maximize();
        driver.get(HomePage.openHomePage());
        //клик по соседнему элементу, чтобы учесть НЕкликабельность секции "Булки" при открытии стартовой страницы
        driver.findElement(neighboringSection).click();
        new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.findElement(sectionConstructorTab).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(sectionName));
        String actualName = driver.findElement(sectionName).getText();
        Assert.assertEquals(expectedName, actualName);

    }
}
