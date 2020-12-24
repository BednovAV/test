package ru.bednov.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;

public class CityPage {
    public WebDriver driver;
    private WebElement newCity;
    public CityPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(className = "MainHeader__city")
    private  WebElement city;
    @FindBy(className = "delivery_address")
    private WebElement address;

    public void clickChangeCity()
    {

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("MainHeader__city")));
        city.click();
    }

    public String getCityName()
    {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("MainHeader__city")));
        String cityName = city.getText();
        return new String(cityName.getBytes(StandardCharsets.UTF_8));
    }

    public String getDeliveryAddress()
    {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("delivery_address")));
        return address.getText().split(",")[0];
    }

    public void clickCity(String city_name)
    {
        newCity = driver.findElement(By.partialLinkText(city_name));
        newCity.click();
    }
}
