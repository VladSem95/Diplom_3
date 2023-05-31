package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryFormPage {
    private final static String RECOVERY_FORM_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final static String XPATH_LOGIN_FROM_RECOVERY_PAGE_BUTTON = ".//a[@class = 'Auth_link__1fOlj']";
    private WebDriver driver;

    public RecoveryFormPage(WebDriver driver) {
        this.driver = driver;
    }
    public static String getLoginFromRecoveryPage(){
        return RECOVERY_FORM_PAGE;
    }
    public static String getXpathLoginFromRecoveryPageButton(){
        return XPATH_LOGIN_FROM_RECOVERY_PAGE_BUTTON;
    }
}
