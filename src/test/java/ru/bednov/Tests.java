package ru.bednov;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Tests {

    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static WebDriver driver;

    @BeforeClass
    public static void Setup()
    {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void loginTest() throws InterruptedException
    {
        //вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        //loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));

        // вводим капчу(вручную)
        loginPage.selectCaptcha();
        Thread.sleep(10000);

        //нажимаем кнопку входа
        loginPage.clickLoginBtn();

        String username = mainPage.getUsername();

        Assert.assertEquals(username, "Андрей");
       }

    @AfterClass
    public static void end()
    {
        mainPage.logout();
        driver.close();

    }

}
