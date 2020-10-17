package ru.bednov;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Tests {

    public static LoginPage loginPage;
    public static WebDriver driver;

    @BeforeClass
    public static void Setup()
    {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void loginTest()
    {
        //значение login/password берутся из файла настроек по аналогии с chromedriver
//и loginpage
//вводим логин
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //вводим пароль
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
       }

}
