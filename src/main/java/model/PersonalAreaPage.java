package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAreaPage {
    private final static String PERSONAL_AREA_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final static By XPATH_ACCOUNT_TEXT = By.xpath(".//p[@class = 'Account_text__fZAIn text text_type_main-default']");
    private final static String XPATH_CONSTRUCTOR_BUTTON = ".//p[text() = 'Конструктор']";
    private final static String XPATH_CONSTRUCTOR_IMAGE = ".//div[@class = 'AppHeader_header__logo__2D0X2']";
    private final static String XPATH_LOGOUT_USER = ".//button[text()='Выход']";

    private final static String PERSONAL_AREA_TEXT = "В этом разделе вы можете изменить свои персональные данные";
    WebDriver driver;
    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }
    public static By getXpathAccountText(){
        return XPATH_ACCOUNT_TEXT;
    }
    public static String getPersonalAreaText(){
        return PERSONAL_AREA_TEXT;
    }
    public static String getXpathLogoutUser(){
        return XPATH_LOGOUT_USER;
    }
    public void openPersonalAreaPage() {
        driver.get(PERSONAL_AREA_PAGE_URL);
    }
    public static String getXpathConstructorButton(){
        return XPATH_CONSTRUCTOR_BUTTON;
    }
    public static String getXpathConstructorImage(){
        return XPATH_CONSTRUCTOR_IMAGE;
    }
}
