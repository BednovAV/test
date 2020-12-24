package ru.bednov;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.bednov.Pages.MainPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void Setup()
    {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        mainPage = new MainPage(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void loginTest() throws InterruptedException
    {
        // первое нажатие войти
        mainPage.FirstLoginBtn();
        //ввод логина
        mainPage.inputLogin(ConfProperties.getProperty("login"));
        mainPage.inputPass(ConfProperties.getProperty("password"));
        //mainPage.clickCaptha();
        Thread.sleep(10000);
        mainPage.SecondLoginBtn();
        String user = mainPage.getUserName();
        Assert.assertEquals(ConfProperties.getProperty("name"), user);
       }

    @AfterClass
    public static void end()
    {
        mainPage.logout();
        driver.close();

    }

}
