package ru.bednov;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    @FindBy(xpath = "//*[@id=\"layout\"]/header/div/div[2]/div/div[3]/div/span/span")
    private WebElement usernameField;

    @FindBy(xpath = "//*[@id=\"layout\"]/header/div/div[2]/div/div[3]/div/div/div/ul/li[7]/a/span[1]")
    private  WebElement outBtn;


    public WebDriver driver;
    public MainPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getUsername()
    {
        return usernameField.getText();
    }


    public void logout()
    {
        usernameField.click();
        outBtn.click();

    }
}
