package ru.bednov;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.bednov.Pages.CityPage;
import ru.bednov.Pages.MainPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(value = Parameterized.class)
public class CityTest {
    private String city;
    public static MainPage mainPage;
    public static CityPage cityPage;
    public static WebDriver driver;

    public CityTest(String city)
    {
        this.city = city;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> GetParams()
    {
        return Arrays.asList(new Object[][]{
                {"Казань"},
                {"Адлер"},
                {"Абакан"}
        });
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        cityPage = new CityPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("loginpage"));
    }

    @Test
    public void changeCityTest() throws InterruptedException {
        // меняем город
        cityPage.clickChangeCity();
        cityPage.clickCity(city);
        // проверяем, что он изменился
        String onPageCity = cityPage.getCityName();
        Assert.assertEquals(city, onPageCity);
        // входим в профиль
        mainPage.FirstLoginBtn();
        mainPage.inputLogin(ConfProperties.getProperty("login"));
        mainPage.inputPass(ConfProperties.getProperty("password"));
        Thread.sleep(10000);
        mainPage.SecondLoginBtn();
        mainPage.clickMenu();
        mainPage.clickProfile();
        // проверяем город доставки
        String cityInAddress = cityPage.getDeliveryAddress();
        try{
            Assert.assertEquals(onPageCity, cityInAddress);
        }
        catch (ComparisonFailure cat){
            cat.getStackTrace();
        }
        finally {
            mainPage.logout();
        }
    }

    @AfterClass
    public static void ExitDriver()
    {
        driver.quit();
    }

}
