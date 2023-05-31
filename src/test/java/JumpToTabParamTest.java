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

import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class JumpToTabParamTest {
    private WebDriver driver;

    private final By sectionConstructorTab;
    private final By neighboringSection;
    private final String expectedName;
    private final By activeTab;


    public JumpToTabParamTest(By sectionConstructorTab, String expectedName, By neighboringSection, By activeTab) {
        this.sectionConstructorTab = sectionConstructorTab;
        this.neighboringSection = neighboringSection;
        this.expectedName = expectedName;
        this.activeTab = activeTab;
    }


    @Parameterized.Parameters
    public static Object[][] getTestParameters(){
        return new Object[][]{
                {HomePage.getXpathTabBuns(),"Булки",HomePage.getXpathTabSauces(),HomePage.getActiveTabBuns()},
                {HomePage.getXpathTabSauces(),"Соусы",HomePage.getXpathTabFillings(),HomePage.getActiveTabSauce()},
                {HomePage.getXpathTabFillings(),"Начинки",HomePage.getXpathTabSauces(),HomePage.getActiveTabFillings()}
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
        //в данном тесте можно проверить только активность табы, все элементы из списка у нас поумполчанию visibillity = true
        driver.findElement(neighboringSection).click();
        driver.findElement(sectionConstructorTab).click();
        String actualName = driver.findElement(activeTab).getText();
        Assert.assertEquals(expectedName, actualName);
    }
}
