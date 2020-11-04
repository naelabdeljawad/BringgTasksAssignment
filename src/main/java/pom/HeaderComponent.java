package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HeaderComponent extends AbstractPage {

    @FindBy(id = "hub-header")
    @CacheLookup
    private WebElement pageContainer;

    @FindBy(className = "search-box")
    @CacheLookup
    private WebElement search;

    public HeaderComponent(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean clickSearch() {
        try {
            search.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hub-header"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
