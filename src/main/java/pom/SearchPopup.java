package pom;

import com.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.text.Utilities;

public class SearchPopup extends AbstractPage {

    @FindBy(className = "qdabY-SearchBox-Input")
    @CacheLookup
    private WebElement input;

    public SearchPopup(WebDriver chromeDriver) {
        super(chromeDriver);
        PageFactory.initElements(chromeDriver, this);
    }

    public boolean clickOnResultByIndex(int index) {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan((By.cssSelector(".SearchResults-list a")), 0)).get(index).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setTextToSearch(String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(input)).sendKeys(text);
            CommonUtils.sleep(2000);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("qdabY-SearchBox-Input"))).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
