package ru.bednov.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "pass")
    private WebElement passwdField;

    @FindBy(className = "AuthPopup")
    private WebElement firstLoginBtn;

    @FindBy(className = "SignIn__action")
    private WebElement secondLoginBtn;

    @FindBy(className = "HeaderUserName")
    private WebElement userMenu;

    @FindBy (partialLinkText = "Мой профиль")
    private WebElement myProfile;

    @FindBy (partialLinkText = "Выйти")
    private WebElement exit;

    public WebDriver driver;
    public MainPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void inputLogin(String login) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
        loginField.sendKeys(login);
    }
    public void inputPass(String passwd) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("pass")));
        passwdField.sendKeys(passwd);
    }
    public void FirstLoginBtn() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("AuthPopup")));
        firstLoginBtn.click();
    }
    public void SecondLoginBtn() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("SignIn__action")));
        secondLoginBtn.click();
    }
    public String getUserName() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("HeaderUserName")));
        return userMenu.getText();
    }
    public void clickMenu() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("HeaderUserName")));
        userMenu.click();
    }
    public void clickProfile() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Мой профиль")));
        myProfile.click();
    }
    public void logout(){
        userMenu.click();
        exit.click();
    }

}
