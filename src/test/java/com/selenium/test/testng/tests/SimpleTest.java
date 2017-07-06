package com.selenium.test.testng.tests;

import com.selenium.test.pages.CurrencyConverter;
import com.selenium.test.webtestsbase.WebBrowser;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by Railven on 04.07.2017.
 */

public class SimpleTest {

    @BeforeTest
    public void beforeTest() {
            WebBrowser.startBrowser();
    }

    @Test
    /*
     * - Смотрим, что по дефолту выбраны EUR, кликаем на USD и убеждаемся в том, что теперь выбраны они
     */
    public void testChooseCurrencyFromPopularList() {

        CurrencyConverter converter = CurrencyConverter.openCurrencyPage();
        WebElement firstPopularItem = converter.getFirstPopularItemSelector();
        String selectedCurrency = converter.getSelectedItemSelector().getText();
        Assert.assertEquals(selectedCurrency,
                "EUR",
                "По дефолту не выбрана валюта " + selectedCurrency);

        firstPopularItem.click();
        String selectedCurrencyAfterClickUSD = converter.getSelectedPopularItemSelector().getText();
        Assert.assertEquals(selectedCurrencyAfterClickUSD,
                "USD",
                "После клика на " + selectedCurrencyAfterClickUSD + ", данная валюта не помечена как выбранная");
    }

    @Test
    /*
     * - Выбираем первую валюту из главного списка и проверяем, что она выделилась
     */
    public void testChooseCurrencyFromMainList() {
        CurrencyConverter converter = CurrencyConverter.openCurrencyPage();
        WebElement firstItem = converter.getFirstItemSelector();
        firstItem.click();
        String selectedCurrency = converter.getSelectedItemSelector().getText();
        Assert.assertEquals(selectedCurrency,
                "AED",
                "После клика на " + selectedCurrency + ", данная валюта не помечена как выбранная");

    }

    @Test
    /*
     * - Проверяем, что при выборе валюты подставляются в соответствующие вкладки
     */
    public void testCurrencySubstitutionToTabs() {
        CurrencyConverter converter = CurrencyConverter.openCurrencyPage();
        String firstTabDefaultCurrency = converter.getFirstTabCurrencySelector().getText();
        Assert.assertEquals(firstTabDefaultCurrency,
                "EUR",
                "По дефолту не выбрана валюта " + firstTabDefaultCurrency);

        WebElement firstItem = converter.getFirstItemSelector();
        firstItem.click();
        String firstTabSelectedCurrency = converter.getFirstTabCurrencySelector().getText();
        Assert.assertEquals(firstTabSelectedCurrency,
                "AED",
                "После клика на " + firstTabSelectedCurrency + ", данная валюта не отобразилась около поля");

        WebElement secondTabSelector = converter.getSecondTabCurrencySelector();
        String secondTabDefaultCurrency = secondTabSelector.getText();
        Assert.assertEquals(secondTabDefaultCurrency,
                "USD",
                "По дефолту не выбрана валюта " + secondTabDefaultCurrency);

        secondTabSelector.click();
        firstItem.click();
        String secondTabSelectedCurrency = secondTabSelector.getText();
        Assert.assertEquals(secondTabSelectedCurrency,
                "AED",
                "После клика на " + secondTabSelectedCurrency + ", данная валюта не отобразилась около поля");
    }

    @Test
    public void testCleanTabs() {
        CurrencyConverter converter = CurrencyConverter.openCurrencyPage();
        WebElement firstInput = converter.getFirstInputSelector();
        firstInput.sendKeys("42");
        WebElement secondInput = converter.getSecondInputSelector();
        secondInput.sendKeys("24");
        WebElement cleanButton = converter.getCleanButtonSelector();
        cleanButton.click();
        String firstValue = converter.getFirstInputSelector().getAttribute("value");
        String secondValue = converter.getSecondInputSelector().getAttribute("value");
        Assert.assertEquals(firstValue,
                "0",
                "После нажатия на кнопку Клиринг, введенное значение осталось в первом поле" );

        Assert.assertEquals(secondValue,
                "0",
                "После нажатия на кнопку Клиринг, введенное значение осталось во втором поле" );
    }

    @AfterTest
    public void afterTest() {
            WebBrowser.finishBrowser();
    }
}
