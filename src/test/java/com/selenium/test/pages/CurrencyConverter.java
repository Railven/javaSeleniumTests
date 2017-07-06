package com.selenium.test.pages;

import com.selenium.test.webtestsbase.WebBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Railven on 04.07.2017.
 */

public class CurrencyConverter {

    private static WebDriver getDriver() {
        return WebBrowser.getDriver();
    }

    public static CurrencyConverter openCurrencyPage() {
        CurrencyConverter converter = new CurrencyConverter();
        getDriver().get("https://www.*******.com/tools/converter/ ");
        converter.waitForLoad();
        return converter;
    }

    private void waitForLoad() {
        WebElement dynamicElement = (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ui-sectionsInTabContent")));
    }

    //Методы получения селекторов для блока Популярных валют
    public WebElement getFirstPopularItemSelector() {
        return getDriver().findElement(By.cssSelector(".converter-popularList > .converter-popularItem"));
    }

    public WebElement getSelectedPopularItemSelector() {
        return getDriver().findElement(By.cssSelector(".converter-popularList > .converter-popularItem__selected"));
    }

    //Методы получения селекторов для основного блока валют
    public WebElement getFirstItemSelector() {
        return getDriver().findElement(By.cssSelector(".converter-currencies > .converter-currenciesItem"));
    }

    public WebElement getSelectedItemSelector()
    {
        return getDriver().findElement(
                By.cssSelector(".converter-currencies > .converter-currenciesItem__selected > .converter-currenciesSymbol"));
    }

    //Методы получения селекторов для работы с вкладками "Из - В"
    public WebElement getFirstTabCurrencySelector()
    {
        return getDriver().findElement(By.cssSelector(".converter-tabItem > .converter-tabCurrencyName"));
    }

    public WebElement getSecondTabCurrencySelector()
    {
        return getDriver().findElement(
                By.xpath("//*[@class='converter-tab']/div[2]/span"));
    }

    public WebElement getFirstInputSelector()
    {
        return getDriver().findElement(
                By.xpath("//*[@class='converter-tab']/div[1]/input"));
    }

    public WebElement getSecondInputSelector()
    {
        return getDriver().findElement(
                By.xpath("//*[@class='converter-tab']/div[2]/input"));
    }

    public WebElement getCleanButtonSelector()
    {
        return getDriver().findElement(
                By.xpath("//*[@class='converter-tabBtns']/div"));
    }
}
