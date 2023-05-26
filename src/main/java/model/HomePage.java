package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final static String HOME_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private final static By LOGIN_HOME_PAGE_BUTTON = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final static String XPATH_LOGIN_HOME_PAGE_BUTTON = ".//button[text() = 'Войти в аккаунт']";
    private final static String XPATH_PERSONAL_AREA_BUTTON = ".//nav/a/p";
    private final static By XPATH_CHECKOUT_BUTTON = By.xpath(".//button[text() = 'Оформить заказ']");
    private final static By XPATH_TEXT_HOME_PAGE = By.xpath(".//h1[text()= 'Соберите бургер']");
    private final static By XPATH_TAB_BUNS = By.xpath(".//span[text()='Булки']");
    private final static By XPATH_TAB_SAUCES = By.xpath(".//span[text()='Соусы']");
    private final static By XPATH_TAB_FILLINGS = By.xpath(".//span[text() = 'Начинки']");
    private final static By XPATH_SECTION_BUNS = By.xpath(".//h2[text()='Булки']");
    private final static By XPATH_SECTION_SAUCES = By.xpath(".//h2[text()='Соусы']");
    private final static By XPATH_SECTION_FILLINGS = By.xpath(".//h2[text()='Начинки']");
    public static By getXpathTabBuns(){
        return XPATH_TAB_BUNS;
    }
    public static By getXpathTabSauces(){
        return XPATH_TAB_SAUCES;
    }
    public static By getXpathTabFillings(){
        return XPATH_TAB_FILLINGS;
    }
    public static By getXpathSectionBuns(){
        return XPATH_SECTION_BUNS;
    }
    public static By getXpathSectionSauces(){
        return XPATH_SECTION_SAUCES;
    }
    public static By getXpathSectionFillings(){
        return XPATH_SECTION_FILLINGS;
    }


    public static By getXpathCheckoutButtonText(WebDriver driver){
       return XPATH_CHECKOUT_BUTTON;
    }
    public static By getXpathTextHomePage(){
        return XPATH_TEXT_HOME_PAGE;
    }

    private static WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public static String getHomePageUrl(){
        return HOME_PAGE_URL;
    }
    public static String getXpathLoginHomePageButton(){
        return XPATH_LOGIN_HOME_PAGE_BUTTON;
    }
    public static String getXpathPersonalAreaButton(){
        return XPATH_PERSONAL_AREA_BUTTON;
    }


    public static void clickLogin(WebDriver driver) {
        driver.findElement(LOGIN_HOME_PAGE_BUTTON).click();
    }
    public void openHomePage() {
        driver.get(HOME_PAGE_URL);
    }

}
